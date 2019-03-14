package com.dy.mmall.bean;

import java.util.Date;

public class Cart {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_cart.id
     *
     * @mbg.generated Thu Mar 14 18:57:24 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_cart.user_id
     *
     * @mbg.generated Thu Mar 14 18:57:24 CST 2019
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_cart.product_id
     *
     * @mbg.generated Thu Mar 14 18:57:24 CST 2019
     */
    private Integer productId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_cart.quantity
     *
     * @mbg.generated Thu Mar 14 18:57:24 CST 2019
     */
    private Integer quantity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_cart.checked
     *
     * @mbg.generated Thu Mar 14 18:57:24 CST 2019
     */
    private Integer checked;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_cart.create_time
     *
     * @mbg.generated Thu Mar 14 18:57:24 CST 2019
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_cart.update_time
     *
     * @mbg.generated Thu Mar 14 18:57:24 CST 2019
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_cart.id
     *
     * @return the value of mmall_cart.id
     *
     * @mbg.generated Thu Mar 14 18:57:24 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_cart.id
     *
     * @param id the value for mmall_cart.id
     *
     * @mbg.generated Thu Mar 14 18:57:24 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_cart.user_id
     *
     * @return the value of mmall_cart.user_id
     *
     * @mbg.generated Thu Mar 14 18:57:24 CST 2019
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_cart.user_id
     *
     * @param userId the value for mmall_cart.user_id
     *
     * @mbg.generated Thu Mar 14 18:57:24 CST 2019
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_cart.product_id
     *
     * @return the value of mmall_cart.product_id
     *
     * @mbg.generated Thu Mar 14 18:57:24 CST 2019
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_cart.product_id
     *
     * @param productId the value for mmall_cart.product_id
     *
     * @mbg.generated Thu Mar 14 18:57:24 CST 2019
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_cart.quantity
     *
     * @return the value of mmall_cart.quantity
     *
     * @mbg.generated Thu Mar 14 18:57:24 CST 2019
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_cart.quantity
     *
     * @param quantity the value for mmall_cart.quantity
     *
     * @mbg.generated Thu Mar 14 18:57:24 CST 2019
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_cart.checked
     *
     * @return the value of mmall_cart.checked
     *
     * @mbg.generated Thu Mar 14 18:57:24 CST 2019
     */
    public Integer getChecked() {
        return checked;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_cart.checked
     *
     * @param checked the value for mmall_cart.checked
     *
     * @mbg.generated Thu Mar 14 18:57:24 CST 2019
     */
    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_cart.create_time
     *
     * @return the value of mmall_cart.create_time
     *
     * @mbg.generated Thu Mar 14 18:57:24 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_cart.create_time
     *
     * @param createTime the value for mmall_cart.create_time
     *
     * @mbg.generated Thu Mar 14 18:57:24 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_cart.update_time
     *
     * @return the value of mmall_cart.update_time
     *
     * @mbg.generated Thu Mar 14 18:57:24 CST 2019
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_cart.update_time
     *
     * @param updateTime the value for mmall_cart.update_time
     *
     * @mbg.generated Thu Mar 14 18:57:24 CST 2019
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}