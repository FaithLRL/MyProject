package com.lrl.study.service.Imp;

import com.lrl.study.DTO.OrderDTO;
import com.lrl.study.enums.ResultEnum;
import com.lrl.study.exception.SellException;
import com.lrl.study.service.BuyerService;
import com.lrl.study.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerServiceImp implements BuyerService {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private OrderService orderService;
    @Override
    public OrderDTO findOneOrDer(String openid, String orderid) {
        return checkOrderOwner(openid,orderid);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderid) {

       OrderDTO orderDTO = checkOrderOwner(openid,orderid);
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //2.调用cancel方法
            return orderService.cancel(orderDTO);

    }

    public OrderDTO checkOrderOwner(String openid, String orderid){
        //1.调用findone查找orderDTO对象
        OrderDTO orderDTO = orderService.findOne(orderid);
        if(orderDTO == null){
            return null;
        }
        //先判断Openid的对象是否是订单的从属者
        if(!openid.equals(orderDTO.getBuyerOpenid())){
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        //2.调用cancel方法
        orderService.cancel(orderDTO);
        return orderDTO;
    }
}
