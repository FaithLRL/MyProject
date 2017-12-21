package com.lrl.study.service.imp;

import com.lrl.study.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImpTest {

    @Autowired
    private ProductServiceImp productServiceImp;

    @Test
    public void findOne() {

        Assert.assertNotEquals(0, this.productServiceImp.findOne("12345"));
        System.out.println(this.productServiceImp.findOne("12345"));
    }

    @Test
    public void findUpAll() {
       List<ProductInfo> productInfoList = productServiceImp.findUpAll();
       Assert.assertNotEquals(0, productInfoList.size());
        System.out.println(productInfoList.size());

    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<ProductInfo> productInfoPage = productServiceImp.findAll(pageRequest);
        Assert.assertNotEquals(0, productInfoPage.getTotalElements());
        System.out.println(productInfoPage.getTotalElements());
    }

    @Test
    @Transactional
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1357");
        productInfo.setProductName("麻婆豆腐");
        productInfo.setProductPrice(new BigDecimal(5.0));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("好辣！");
        productInfo.setProductIcon("1.jsp");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);
        Assert.assertNotEquals(0,this.productServiceImp.save(productInfo));
    }
}