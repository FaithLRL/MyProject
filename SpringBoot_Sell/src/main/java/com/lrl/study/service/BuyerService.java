package com.lrl.study.service;

import com.lrl.study.DTO.OrderDTO;

public interface BuyerService {

    //查询一个订单
    OrderDTO findOneOrDer(String openid, String orderid);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderid);
}
