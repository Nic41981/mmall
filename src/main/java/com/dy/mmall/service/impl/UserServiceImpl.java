package com.dy.mmall.service.impl;

import com.dy.mmall.bean.User;
import com.dy.mmall.common.Const;
import com.dy.mmall.common.ServerResponse;
import com.dy.mmall.common.TokenCache;
import com.dy.mmall.dao.UserMapper;
import com.dy.mmall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author nic
 * @version 1.0
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public ServerResponse login(String username, String password) {
        User user = userMapper.selectLogin(username);
        if (user != null && BCrypt.checkpw(password,user.getPassword())){
            System.out.println(user);
            return ServerResponse.createBySuccess("登陆成功",user);
        }
        return ServerResponse.createByError("用户名不存在或密码错误");
    }

    @Override
    public ServerResponse register(User user) {
        ServerResponse validResponse = this.checkValid(user.getUsername(),Const.USERNAME);
        if(!validResponse.isSuccess()){
            return validResponse;
        }
        validResponse = this.checkValid(user.getEmail(),Const.EMAIL);
        if(!validResponse.isSuccess()){
            return validResponse;
        }
        user.setRole(Const.Role.ROLE_CUSTOMER);
        //BCrypt加密
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
        int resultCount = userMapper.insert(user);
        if(resultCount == 0){
            return ServerResponse.createByError("注册失败");
        }
        return ServerResponse.createBySuccessMsg("注册成功");
    }

    @Override
    public ServerResponse checkValid(String str, String type) {
        str = str == null ? "" : str;
        switch (type) {
            case Const.USERNAME: {
                int result = userMapper.checkUserName(str);
                if (result > 0) {
                    return ServerResponse.createByError("用户名已存在");
                }
                break;
            }
            case Const.EMAIL: {
                int result = userMapper.checkEmail(str);
                if (result > 0) {
                    return ServerResponse.createByError("密码已存在");
                }
                break;
            }
            default: {
                return ServerResponse.createByError("参数错误");
            }
        }
        return ServerResponse.createBySuccessMsg("校验成功");
    }

    @Override
    public ServerResponse selectQuestion(String username) {
        ServerResponse validResponse = this.checkValid(username,Const.USERNAME);
        if(validResponse.isSuccess()){
            //用户不存在
            return ServerResponse.createByError("用户不存在");
        }
        String question = userMapper.selectQuestionByUsername(username);
        if(question != null && question.length() > 0){
            return ServerResponse.createBySuccess(question);
        }
        return ServerResponse.createByError("找回密码的问题是空的");
    }

    @Override
    public ServerResponse checkAnswer(String username, String question, String answer) {
        int resultCount = userMapper.checkAnswer(username,question,answer);
        if(resultCount>0){
            //说明问题及问题答案是这个用户的,并且是正确的
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+username,forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByError("问题的答案错误");
    }

    @Override
    public ServerResponse forgetResetPassword(String username, String passwordNew, String forgetToken) {
        if(forgetToken == null || forgetToken.length() == 0){
            return ServerResponse.createByError("参数错误,token需要传递");
        }
        ServerResponse validResponse = this.checkValid(username,Const.USERNAME);
        if(validResponse.isSuccess()){
            //用户不存在
            return ServerResponse.createByError("用户不存在");
        }
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX+username);
        if(token == null || token.length() == 0){
            return ServerResponse.createByError("token无效或者过期");
        }

        if(forgetToken.equals(token)){
            String md5Password  = BCrypt.hashpw(passwordNew,BCrypt.gensalt());
            int rowCount = userMapper.updatePasswordByUsername(username,md5Password);

            if(rowCount > 0){
                return ServerResponse.createBySuccessMsg("修改密码成功");
            }
        }else{
            return ServerResponse.createByError("token错误,请重新获取重置密码的token");
        }
        return ServerResponse.createBySuccessMsg("修改密码失败");
    }

    @Override
    public ServerResponse resetPassword(String passwordOld, String passwordNew, User user) {
        User dbUser = userMapper.selectLogin(user.getUsername());
        if(BCrypt.checkpw(passwordOld,dbUser.getPassword())){
            return ServerResponse.createByError("旧密码错误");
        }

        user.setPassword(BCrypt.hashpw(passwordNew,BCrypt.gensalt()));
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if(updateCount > 0){
            return ServerResponse.createBySuccessMsg("密码更新成功");
        }
        return ServerResponse.createByError("密码更新失败");
    }

    @Override
    public ServerResponse updateInformation(User user) {
        int resultCount = userMapper.checkEmailByUserId(user.getEmail(),user.getId());
        if(resultCount > 0){
            return ServerResponse.createByError("email已存在,请更换email再尝试更新");
        }
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());

        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if(updateCount > 0){
            return ServerResponse.createBySuccess("更新个人信息成功",updateUser);
        }
        return ServerResponse.createByError("更新个人信息失败");
    }

    @Override
    public ServerResponse getInformation(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if(user == null){
            return ServerResponse.createByError("找不到当前用户");
        }
        user.setPassword("");
        return ServerResponse.createBySuccess(user);
    }

    @Override
    public ServerResponse checkAdminRole(User user) {
        if(user != null && user.getRole() == Const.Role.ROLE_ADMIN){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }
}
