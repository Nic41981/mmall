package com.dy.mmall.service;

import com.dy.mmall.common.ServerResponse;

import java.util.List;

/**
 * @author nic
 * @version 1.0
 */
public interface CategoryService {
    ServerResponse addCategory(String categoryName, Integer parentId);
    ServerResponse updateCategoryName(Integer categoryId,String categoryName);
    ServerResponse getChildrenParallelCategory(Integer categoryId);
    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);
}
