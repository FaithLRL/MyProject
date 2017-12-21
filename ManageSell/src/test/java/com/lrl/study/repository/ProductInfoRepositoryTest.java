package com.lrl.study.repository;

import com.lrl.study.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void save(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1357");
        productInfo.setProductName("麻婆豆腐");
        productInfo.setProductPrice(new BigDecimal(5.0));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("好辣！");
        productInfo.setProductIcon("1.jsp");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);
        Assert.assertNotEquals(0,this.productInfoRepository.save(productInfo));


    }

    @Test
    public void findOne(){
        Assert.assertNotEquals(0, this.productInfoRepository.findOne("12345"));
        System.out.println(productInfoRepository.findOne("12345"));
    }

    @Test
    public void findAll(){
        Assert.assertNotEquals(0, this.productInfoRepository.findAll());
        System.out.println(productInfoRepository.findAll());
    }



    @Test
    public void findByProductStatus() {

         List<ProductInfo> productInfoList = productInfoList = productInfoRepository.findByProductStatus(0);
        Assert.assertNotEquals(0, productInfoList.size());
    }
}