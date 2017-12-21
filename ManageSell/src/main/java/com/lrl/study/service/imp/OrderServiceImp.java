package com.lrl.study.service.imp;

import com.lrl.study.DTO.CartDTO;
import com.lrl.study.DTO.OrderDTO;
import com.lrl.study.dataobject.OrderDetail;
import com.lrl.study.dataobject.OrderMaster;
import com.lrl.study.dataobject.ProductInfo;
import com.lrl.study.enums.ResultEnum;
import com.lrl.study.exception.SellException;
import com.lrl.study.repository.OrderDetailRepository;
import com.lrl.study.repository.OrderMasterRepository;
import com.lrl.study.service.OrderService;
import com.lrl.study.service.ProductService;
import com.lrl.study.utils.KeyUtil;
import com.sun.java.swing.plaf.windows.WindowsTreeUI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述: <br>
 * 〈买家实现类〉
 *
 No such property: code for class: Script1
 * @return:
 * @since: 1.0.0
 * @Author:LRL
 * @Date: 2017/12/15 16:43
 */
@Service
public class OrderServiceImp implements OrderService {


    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        /*orderId:产生随机ID*/
        String orderId = KeyUtil.genUniqueKey();

        //1.查询商品（数量，价格）
        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()) {
           ProductInfo productInfo = productService.findOne(orderDetail.getPriductId());
           if (productInfo == null){
               throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
           }

            //2.计算总价
            orderAmount = orderDetail.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
           //3.订单详情入库
            orderDetail.setOrderId(KeyUtil.genUniqueKey());
            orderDetail.setDetailId(orderId);
            orderDetailRepository.save(orderDetail);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);

        }
        //3.写入订单数据库（orderMaster 和 orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMasterRepository.save(orderMaster);


        //4.扣库存
        List<CartDTO> cartDTOList = new ArrayList<>();
        orderDTO.getOrderDetailList().stream().map(e->
                new CartDTO(e.getPriductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
