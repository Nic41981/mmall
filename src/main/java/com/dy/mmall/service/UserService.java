package com.dy.mmall.service;

import com.dy.mmall.bean.User;
import com.dy.mmall.common.ServerResponse;

/**
 * @author nic
 * @version 1.0
 */
public interface UserService {

    ServerResponse login(String username, String password);

    ServerResponse register(User user);

    ServerResponse checkValid(String str, String type);

    ServerResponse selectQuestion(String username);

    ServerResponse checkAnswer(String username, String question, String answer);

    ServerResponse forgetResetPassword(String username, String passwordNew, String forgetToken);

    ServerResponse resetPassword(String passwordOld, String passwordNew, User user);

    ServerResponse updateInformation(User user);

    ServerResponse getInformation(Integer userId);

    ServerResponse checkAdminRole(User user);

}
