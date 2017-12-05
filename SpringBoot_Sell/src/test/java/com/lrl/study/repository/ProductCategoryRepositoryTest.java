package com.lrl.study.repository;



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
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void save(){
        ProductCategory productCategory= new ProductCategory();
        productCategory.setCategoryName("头条类");
        productCategory.setCategoryType(8);
        Assert.assertNotNull(this.productCategoryRepository.save(productCategory));
    }

    @Test
    public void findtest(){
        System.out.println(this.productCategoryRepository.findOne(1));
//        Assert.assertNotNull(this.productCategoryRepository.findOne(1));
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<Integer> list = Arrays.asList(2,4);
        Assert.assertNotEquals(0, this.productCategoryRepository.findByCategoryTypeIn(list));
    }

    @Test
    public void updateTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(2);
        productCategory.setCategoryName("测试类");
        productCategory.setCategoryType(4);
        Assert.assertNotNull(this.productCategoryRepository.save(productCategory));
    }



}