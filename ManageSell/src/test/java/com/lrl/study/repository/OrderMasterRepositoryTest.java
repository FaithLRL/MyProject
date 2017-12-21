package com.lrl.study.repository;

import com.lrl.study.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    private final String OPENID = "110110";

    @Test
    public void save(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("2222");
        orderMaster.setBuyerName("二师兄");
        orderMaster.setBuyerPhone("123456789123");
        orderMaster.setBuyerAddress("大话西游");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        Assert.assertNotEquals(0, this.orderMasterRepository.save(orderMaster));
    }

    @Test
    public void findOne(){
        Assert.assertNotEquals(0, this.orderMasterRepository.findOne("1111"));
        System.out.println(orderMasterRepository.findOne("1111"));
    }


    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = new PageRequest(0,1);
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(OPENID, pageRequest);
        Assert.assertNotEquals(OPENID, orderMasterPage.getTotalElements());
        System.out.println(orderMasterPage.getTotalElements());
    }
}