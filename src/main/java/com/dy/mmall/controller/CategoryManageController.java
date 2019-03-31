package com.dy.mmall.controller;

import com.dy.mmall.bean.User;
import com.dy.mmall.common.Const;
import com.dy.mmall.common.ResponseCode;
import com.dy.mmall.common.ServerResponse;
import com.dy.mmall.service.CategoryService;
import com.dy.mmall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;

/**
 * @author nic
 * @version 1.0
 */

@RestController
@RequestMapping("/manage/category")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryManageController {
    private final UserService userService;
    private final CategoryService categoryService;

    @RequestMapping("add_category.do")
    public ServerResponse addCategory(HttpSession session, String categoryName, @RequestParam(value = "parentId",defaultValue = "0") int parentId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        //校验一下是否是管理员
        if(userService.checkAdminRole(user).isSuccess()){
            //是管理员
            //增加我们处理分类的逻辑
            return categoryService.addCategory(categoryName,parentId);

        }else{
            return ServerResponse.createByError("无权限操作,需要管理员权限");
        }
    }

    @RequestMapping("set_category_name.do")
    public ServerResponse setCategoryName(HttpSession session,Integer categoryId,String categoryName){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if(userService.checkAdminRole(user).isSuccess()){
            //更新categoryName
            return categoryService.updateCategoryName(categoryId,categoryName);
        }else{
            return ServerResponse.createByError("无权限操作,需要管理员权限");
        }
    }

    @RequestMapping("get_category.do")
    public ServerResponse getChildrenParallelCategory(HttpSession session,@RequestParam(value = "categoryId" ,defaultValue = "0") Integer categoryId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if(userService.checkAdminRole(user).isSuccess()){
            //查询子节点的category信息,并且不递归,保持平级
            return categoryService.getChildrenParallelCategory(categoryId);
        }else{
            return ServerResponse.createByError("无权限操作,需要管理员权限");
        }
    }

    @RequestMapping("get_deep_category.do")
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpSession session,@RequestParam(value = "categoryId" ,defaultValue = "0") Integer categoryId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if(userService.checkAdminRole(user).isSuccess()){
            //查询当前节点的id和递归子节点的id
            //0->10000->100000
            return categoryService.selectCategoryAndChildrenById(categoryId);

        }else{
            return ServerResponse.createByError("无权限操作,需要管理员权限");
        }
    }
}
