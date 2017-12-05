package com.lrl.study.converter;

import com.lrl.study.DTO.OrderDTO;
import com.lrl.study.domain.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMaster2OrderDTOConverter {
    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasters){
        List<OrderDTO> orderDTOList = orderMasters.stream().map(e->convert(e))
                .collect(Collectors.toList());
        return orderDTOList;
    }
}
