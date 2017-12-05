package com.lrl.study.service.Imp;



import com.lrl.study.domain.ProductInfo;
import com.lrl.study.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImpTest {

    @Autowired
    private ProductInfoServiceImp productInfoServiceImp;

    /**
     * Id 查询测试类
     * @throws Exception
     */
    @Test
    public void findOne() throws Exception {
        Assert.assertNotNull(this.productInfoServiceImp.findOne("5214"));
        System.out.println(this.productInfoServiceImp.findOne("5214"));
    }

    /**
     * 全部查询测试类
     * @throws Exception
     */
    @Test
    public void findUpAll() throws Exception {
        Assert.assertNotNull(this.productInfoServiceImp.findUpAll());
        System.out.println(this.productInfoServiceImp.findUpAll());
    }

    /**
     * 分页功能测试类
     * @throws Exception
     */
    @Test
    public void finfAll() throws Exception {
        PageRequest pageRequest = new PageRequest(0,1);
//        Assert.assertNotEquals(0,this.productInfoServiceImp.finfAll(pageRequest));
        Page<ProductInfo> page= this.productInfoServiceImp.finfAll(pageRequest);
        Assert.assertNotEquals(0,page);
    }

    /**
     * 添加功能测试类
     * @throws Exception
     */
    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("980");
        productInfo.setProductName("糖醋排骨");
        productInfo.setProductPrice(new BigDecimal(2.5));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("好吃！");
        productInfo.setProductIcon("http:www.baidu.com");
        productInfo.setCategoryType(1);
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        Assert.assertNotNull(this.productInfoServiceImp.save(productInfo));

    }

}