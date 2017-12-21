package com.lrl.study.repository;

import com.lrl.study.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    @Transactional
    public void save(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1");
        orderDetail.setOrderId("1111");
        orderDetail.setPriductId("1357");
        orderDetail.setProductIcon("http://xxxx.jpg");
        orderDetail.setProductName("南瓜羹");
        orderDetail.setProductPrice(new BigDecimal(2.2));
        orderDetail.setProductQuantity(3);

        Assert.assertNotEquals(0, orderDetailRepository.save(orderDetail));
    }

    @Test
    public void findOne(){
        Assert.assertNotEquals(0, orderDetailRepository.findOne("1"));
        System.out.println(orderDetailRepository.findOne("1"));
    }
    @Test
    public void findAll(){
        Assert.assertNotEquals(0, orderDetailRepository.findAll());
        System.out.println(orderDetailRepository.findAll());
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId("1111");
        Assert.assertNotEquals(0, orderDetailList.size());
    }
}