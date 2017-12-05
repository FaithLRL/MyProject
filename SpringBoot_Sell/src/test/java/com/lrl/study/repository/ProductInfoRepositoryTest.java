package com.lrl.study.repository;


import com.lrl.study.domain.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("125153");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal(2.5));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好吃！");
        productInfo.setProductIcon("http:www.baidu.com");
        productInfo.setCategoryType(1);
        productInfo.setProductStatus(0);
        Assert.assertNotNull(this.productInfoRepository.save(productInfo));
    }

    @Test
    public void findAll(){
        Assert.assertNotNull(this.productInfoRepository.findAll());
        System.out.println(this.productInfoRepository.findAll());
    }


    @Test
    public void findByProductStatus()throws Exception{

        List<ProductInfo> list = this.productInfoRepository.findByProductStatus(0);
        Assert.assertNotNull(list);
        System.out.println(this.productInfoRepository.findByProductStatus(0));

    }

}