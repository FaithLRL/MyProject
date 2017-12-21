package com.lrl.study.controller;

import com.lrl.study.DTO.OrderDTO;
import com.lrl.study.enums.ResultEnum;
import com.lrl.study.exception.SellException;
import com.lrl.study.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述: <br>
 * 〈〉
 *
 No such property: code for class: Script1
 * @return:
 * @since: 1.0.0
 * @Author:LRL
 * @Date: 2017/12/13 11:05
 */
@RestController
@RequestMapping("/seller/order")
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.OPTIONS})
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     * @param page 第几页, 从1页开始
     * @param size 一页有多少条数据
     * @return
     */

    @GetMapping("/list")
    public Map list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                    @RequestParam(value = "size", defaultValue = "2") Integer size) {

        PageRequest request = new PageRequest(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        Map map=new HashMap();
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);
        return map;
    }

    /**
     * 取消订单
     */
    @PostMapping("/cancel")
    public Map cancel(@RequestParam("orderId") String orderid){

        Map map = new HashMap();

        try {
            //1.根据orderid -》 findOne找到订单DTO
            OrderDTO orderDTO = orderService.findOne(orderid);
            //2.将返回orderDTO传入OrderService的cancel方法
            orderService.cancel(orderDTO);
        } catch (SellException e) {
             map.put("msg", e.getMessage());

        }
        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
        return map;
    }


    /**
     *订单详情
     */
    @GetMapping("detail")
    public Map detail(@RequestParam("orderId") String orderid){

        Map map = new HashMap();
        OrderDTO orderDTO = new OrderDTO();

        try {
            //使用OrderService中的findOne方法
            orderDTO = orderService.findOne(orderid);


        }catch (SellException e){
            //返回异常提示--》map
            map.put("msg", e.getMessage());

        }
        map.put("msg", orderDTO);

        return map;
    }



}
