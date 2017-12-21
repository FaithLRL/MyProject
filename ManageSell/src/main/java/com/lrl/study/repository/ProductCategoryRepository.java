package com.lrl.study.repository;

import com.lrl.study.dataobject.ProductCategory;
import com.lrl.study.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 功能描述: <br>
 * 〈类目表资源库〉
 *
 No such property: code for class: Script1
 * @return:
 * @since: 1.0.0
 * @Author:LRL
 * @Date: 2017/12/10 18:02
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    /**
     * 查询类目编号
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory>findByCategoryTypeIn(List<Integer> categoryTypeList);



}
