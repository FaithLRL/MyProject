package com.lrl.study.service.Imp;

import com.lrl.study.DTO.OrderDTO;
import com.lrl.study.domain.OrderDetail;
import com.lrl.study.domain.ProductInfo;
import com.lrl.study.enums.OrderStatusEnum;
import com.lrl.study.exception.SellException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImpTest {

    @Autowired
    private OrderServiceImp orderServiceImp;
    @Test
    public void crate() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("庄三");
        orderDTO.setBuyerAddress("温职");
        orderDTO.setBuyerPhone("34324234242");
        orderDTO.setBuyerOpenid("222");

        List<OrderDetail> orderDetailList =new ArrayList<>();

        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setProductId("112");
        orderDetail.setProductQuantity(5);

        OrderDetail orderDetail1 =new OrderDetail();
        orderDetail1.setProductId("110");
        orderDetail1.setProductQuantity(20);

        orderDetailList.add(orderDetail);
        orderDetailList.add(orderDetail1);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderServiceImp.crate(orderDTO);
        Assert.assertNotNull(result);

    }

    @Test
    public void findOne() throws Exception {
        OrderDTO orderDTO = this.orderServiceImp.findOne("1512366557822198193");
        Assert.assertNotNull(orderDTO);
        System.out.println(orderDTO);


    }

    @Test
    public void findList() throws Exception {
        PageRequest pageRequest = new PageRequest(0,1);
        Page<OrderDTO> page= this.orderServiceImp.findList(pageRequest);
        Assert.assertNotEquals(0,page);
    }

    @Test
    public void cancel() throws Exception {
        //第一步：查找一个orderDTO
        OrderDTO orderDTO = orderServiceImp.findOne("1512977998919460278");
        //第二步：执行orderServiceImp里面的cancel方法
        OrderDTO result =orderServiceImp.cancel(orderDTO);
        //第三步：第二步返回对象中的订单状态码是否等于enum里的取消状态码
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() throws Exception {
    }

    @Test
    public void findList1() throws Exception {
    }

}