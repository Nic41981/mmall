package com.dy.mmall.dao;

import com.dy.mmall.bean.Product;

public interface ProductMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_product
     *
     * @mbg.generated Mon Mar 25 17:10:44 CST 2019
     */
    int insert(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_product
     *
     * @mbg.generated Mon Mar 25 17:10:44 CST 2019
     */
    int insertSelective(Product record);
}