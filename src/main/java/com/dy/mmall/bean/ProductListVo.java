package com.dy.mmall.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author nic
 * @version 1.0
 */
@Data
public class ProductListVo {
    private Integer id;
    private Integer categoryId;
    private String name;
    private String subtitle;
    private String mainImage;
    private BigDecimal price;
    private Integer status;
    private String imageHost;
}
