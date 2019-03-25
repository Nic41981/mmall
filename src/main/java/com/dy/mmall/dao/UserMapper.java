package com.dy.mmall.dao;

import com.dy.mmall.bean.User;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_user
     *
     * @mbg.generated Mon Mar 18 16:37:36 CST 2019
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_user
     *
     * @mbg.generated Mon Mar 18 16:37:36 CST 2019
     */
    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    User selectLogin(String userName);

    int checkUserName(String userName);

    int checkEmail(String email);

    String selectQuestionByUsername(String username);

    int checkAnswer(@Param("username")String username,@Param("question")String question,@Param("answer")String answer);

    int updatePasswordByUsername(@Param("username")String username,@Param("passwordNew")String passwordNew);

    int checkEmailByUserId(@Param(value="email")String email,@Param(value="userId")Integer userId);
}