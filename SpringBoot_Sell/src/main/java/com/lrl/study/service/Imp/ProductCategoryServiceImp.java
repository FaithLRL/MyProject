package com.lrl.study.service.Imp;


import com.lrl.study.domain.ProductCategory;
import com.lrl.study.repository.ProductCategoryRepository;
import com.lrl.study.service.PorductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实现类
 */
@Service
public class ProductCategoryServiceImp implements PorductCategoryService {

   @Autowired
   private ProductCategoryRepository productCategoryRepository;


   //通过Id查询
   @Override
    public ProductCategory findOne(Integer categoryid){
        return this.productCategoryRepository.findOne(categoryid);
    }

    //全部查询
    @Override
    public List<ProductCategory> findAll() {
        return this.productCategoryRepository.findAll();
    }

    //通过Id和Type查询
    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categorylist) {
        return this.productCategoryRepository.findByCategoryTypeIn(categorylist);
    }

    //增加
    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return this.productCategoryRepository.save(productCategory);
    }


}
