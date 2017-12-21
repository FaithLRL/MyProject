package com.lrl.study.service.imp;

import com.lrl.study.dataobject.ProductCategory;
import com.lrl.study.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImpTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findOne() {
       ProductCategory productCategory = categoryService.findOne(1);
        Assert.assertNotEquals(0, productCategory);
        System.out.println(productCategory);
    }

    @Test
    public void findAll() {
        Assert.assertNotEquals(0, this.categoryService.findAll());
        System.out.println(this.categoryService.findAll());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list = Arrays.asList(1,2,3);
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, productCategoryList.size());
    }

    @Test
    @Transactional
    public void save() {
        ProductCategory productCategory = new ProductCategory("黑森林",5);
        Assert.assertNotEquals(0,productCategory);
    }
}