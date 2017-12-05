package com.lrl.study.service;

import com.lrl.study.DTO.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    /**
     * 创建订单
     */

    OrderDTO crate(OrderDTO orderDTO);

    /**
     * 查询多个订单
     */

    OrderDTO findOne(String orderId);

    /**
     * 查询订单列表
     */

    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /**
     * 取消订单
     */
    OrderDTO cancel(OrderDTO orderDTO);

    /**
     * 完结订单
     */
    OrderDTO paid(OrderDTO orderDTO);

    /**
     * 查询订单列表
     */

    Page<OrderDTO> findList(Pageable pageable);
}

