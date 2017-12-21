package com.lrl.study.service;


import com.lrl.study.dataobject.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述: <br>
 * 〈〉
 *
 No such property: code for class: Script1
 * @return:
 * @since: 1.0.0
 * @Author:LRL
 * @Date: 2017/12/11 21:57
 */
public interface CategoryService {

    /**
     * 根据Id查询类目
     * @param catedoryId
     * @return
     */
    ProductCategory findOne(Integer catedoryId);

    /**
     * 查询所有类目
     * @return
     */
    List<ProductCategory> findAll();

    /**
     *分支
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 添加类目
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);
}
