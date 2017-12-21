package com.lrl.study.controller;

import com.lrl.study.DTO.OrderDTO;
import com.lrl.study.VO.ResultVO;
import com.lrl.study.converter.OrderForm2OrderDTOConverter;
import com.lrl.study.enums.ResultEnum;
import com.lrl.study.exception.SellException;
import com.lrl.study.form.OrderForm;
import com.lrl.study.service.BuyerService;
import com.lrl.study.service.OrderService;
import com.lrl.study.util.ResultVOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    /**
     * 创建订单
     * @param orderForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
           throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.crate(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOUtils.success(map);
    }
    /**
     * 订单列表
     * @param openid
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page",defaultValue = "1") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size){
        if (StringUtils.isEmpty(openid)) {
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest request = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);

        return ResultVOUtils.success(orderDTOPage.getContent());

    }

    /**
     * 订单详情
     * @p openid
     * @param orderid
     * @return
     */
    @PostMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderid") String orderid){

       OrderDTO orderDTO = buyerService.findOneOrDer(openid,orderid);
        //返回ResultVOUtil.success方法
        return ResultVOUtils.success(orderDTO);
    }

    /**
     * 取消订单
     * @param openid
     * @param orderid
     * @return
     */
    @PostMapping("/cancel")
    public  ResultVO cancel(@RequestParam("openid") String openid,
                            @RequestParam("orderid") String orderid){

        buyerService.cancelOrder(openid,orderid);
        //返回ResultVOUtil.success方法
        return ResultVOUtils.success();
    }
}
