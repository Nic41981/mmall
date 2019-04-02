package com.dy.mmall.controller;

import com.dy.mmall.common.ServerResponse;
import com.dy.mmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nic
 * @version 1.0
 */
@RestController
@RequestMapping("/product/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {
    private final ProductService productService;

    @RequestMapping("detail.do")
    public ServerResponse detail(Integer productId){
        return productService.getProductDetail(productId);
    }

    @RequestMapping("list.do")
    public ServerResponse list(@RequestParam(value = "keyword",required = false)String keyword,
                               @RequestParam(value = "categoryId",required = false)Integer categoryId,
                               @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                               @RequestParam(value = "orderBy",defaultValue = "") String orderBy){
        return productService.getProductByKeywordCategory(keyword,categoryId,pageNum,pageSize,orderBy);
    }
}
