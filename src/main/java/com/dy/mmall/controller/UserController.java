package com.dy.mmall.controller;

import com.dy.mmall.bean.User;
import com.dy.mmall.common.Const;
import com.dy.mmall.common.ResponseCode;
import com.dy.mmall.common.ServerResponse;
import com.dy.mmall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;

/**
 * @author nic
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * 登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ServerResponse login(String username, String password, HttpSession session){
        ServerResponse response = userService.login(username,password);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    /**
     * 登出
     */
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public ServerResponse logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess("退出成功");
    }


    /**
     * 注册
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ServerResponse register(User user){
        return userService.register(user);
    }


    /**
     * 唯一性检查
     */
    @RequestMapping(value = "/valid",method = RequestMethod.GET)
    public ServerResponse checkValid(String str, String type){
        return userService.checkValid(str,type);
    }


    /**
     * 获取用户信息
     */
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public ServerResponse getUserInfo(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user != null){
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByError("用户未登录,无法获取当前用户的信息");
    }


    /**
     * 获取用户问题
     */
    @RequestMapping(value = "/forget/question",method = RequestMethod.GET)
    public ServerResponse forgetGetQuestion(String username){
        return userService.selectQuestion(username);
    }


    /**
     * 验证用户答案
     */
    @RequestMapping(value = "/forget/answer",method = RequestMethod.POST)
    public ServerResponse forgetCheckAnswer(String username,String question,String answer){
        return userService.checkAnswer(username,question,answer);
    }


    /**
     * 重置用户密码
     */
    @RequestMapping(value = "/forget/reset",method = RequestMethod.POST)
    public ServerResponse forgetRestPassword(String username,String passwordNew,String forgetToken){
        return userService.forgetResetPassword(username,passwordNew,forgetToken);
    }


    /**
     * 设置用户密码
     */
    @RequestMapping(value = "/reset_password",method = RequestMethod.POST)
    public ServerResponse resetPassword(HttpSession session,String passwordOld,String passwordNew){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByError("用户未登录");
        }
        return userService.resetPassword(passwordOld,passwordNew,user);
    }


    /**
     * 修改用户信息
     */
    @RequestMapping(value = "/info",method = RequestMethod.PUT)
    public ServerResponse update_information(HttpSession session, User user){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByError("用户未登录");
        }
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        ServerResponse response = userService.updateInformation(user);
        if(response.isSuccess()){
            ((User)response.getData()).setUsername(currentUser.getUsername());
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    /**
     * 更新用户信息
     */
    @RequestMapping(value = "/info/update",method = RequestMethod.GET)
    public ServerResponse get_information(HttpSession session){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(),"未登录,需要强制登录status=10");
        }
        return userService.getInformation(currentUser.getId());
    }
}
