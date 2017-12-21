package com.lrl.study.service.Imp;


import com.lrl.study.domain.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImpTest {

    @Autowired
    private ProductCategoryServiceImp productCategoryServiceImp;
    @Test
    public void findOne() throws Exception {
        Assert.assertNotNull(productCategoryServiceImp.findOne(3));
        System.out.println(productCategoryServiceImp.findOne(3));

    }

    @Test
    public void findAll() throws Exception {
        Assert.assertNotNull(productCategoryServiceImp.findAll());
        System.out.println(productCategoryServiceImp.findAll());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
//        List<Integer> list = Arrays.asList(2,4);
        List<Integer> list = Arrays.asList(3,5);
        Assert.assertNotNull(productCategoryServiceImp.findByCategoryTypeIn(list));
        System.out.println(productCategoryServiceImp.findByCategoryTypeIn(list));

    }

    @Test
    public void save() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("好评类");
        productCategory.setCategoryType(2);
        Assert.assertNotNull(productCategoryServiceImp.save(productCategory));
    }



}