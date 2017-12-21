package com.lrl.study.service;




import com.lrl.study.domain.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory findOne(Integer categoryid);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categorylist);

    ProductCategory save(ProductCategory productCategory);




}
