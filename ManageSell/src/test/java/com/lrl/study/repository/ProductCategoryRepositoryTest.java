package com.lrl.study.repository;

import com.lrl.study.dataobject.ProductCategory;
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
/**
 * 功能描述: <br>
 * 〈类目表资源库测试类〉
 *
 No such property: code for class: Script1
 * @return:
 * @since: 1.0.0
 * @Author:LRL
 * @Date: 2017/12/11 21:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;


    /**
     * 添加类目
     */
    @Test
    /*回滚 */
    @Transactional
    public void save(){
        ProductCategory productCategory = new ProductCategory("我的最爱",4);
        Assert.assertNotEquals(0,this.productCategoryRepository.save(productCategory));
        System.out.println();
    }

    @Test
            /*回滚 */
    @Transactional
    public void update(){
        ProductCategory productCategory = new ProductCategory();
        /*修改和添加的区别在于是否有Id*/
        productCategory.setCategoryId(1);
        productCategory.setCategoryName("热销");
        productCategory.setCategoryType(3);
        Assert.assertNotEquals(0,this.productCategoryRepository.save(productCategory));
    }

    /**
     * 根据Id查询类目
     */
    @Test
    public void findOne(){
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        Assert.assertNotEquals(0, productCategory);
        System.out.println(productCategory);
    }

    /**
     * 查询所有类目
     */
    @Test
    public void findAll(){

        Assert.assertNotEquals(0,this.productCategoryRepository.findAll());
        System.out.println(this.productCategoryRepository.findAll());

    }

    /**
     *
     */
    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list = Arrays.asList(1,2,3);
        List<ProductCategory> productCategoryList = productCategoryRepository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, productCategoryList.size());
        System.out.println(productCategoryList.size());
    }
}