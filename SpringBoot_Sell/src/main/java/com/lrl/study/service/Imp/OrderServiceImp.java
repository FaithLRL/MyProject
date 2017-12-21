package com.lrl.study.service.Imp;

import com.lrl.study.DTO.CartDTO;
import com.lrl.study.DTO.OrderDTO;
import com.lrl.study.converter.OrderMaster2OrderDTOConverter;
import com.lrl.study.domain.OrderDetail;
import com.lrl.study.domain.OrderMaster;
import com.lrl.study.domain.ProductInfo;
import com.lrl.study.enums.OrderStatusEnum;
import com.lrl.study.enums.PayStatusEnum;
import com.lrl.study.enums.ResultEnum;
import com.lrl.study.exception.SellException;
import com.lrl.study.repository.OrderDetailRepository;
import com.lrl.study.repository.OrderMasterRepository;
import com.lrl.study.service.OrderService;
import com.lrl.study.service.ProductInfoService;
import com.lrl.study.util.keyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp  implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    @Override
    /**
     * 事务回滚
     *  @Transactional
     */
    @Transactional
    public OrderDTO crate(OrderDTO orderDTO) {

        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        String orderId=keyUtil.genUniqueKey();

//        List<CartDTO> cartDTOList =new ArrayList<>();

        //1.查询商品(数量，价格)
        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算总价
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

            //订单详情入库

            orderDetail.setDetailId(keyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailRepository.save(orderDetail);


//            CartDTO cartDTO=new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
//            cartDTOList.add(cartDTO);
        }

        //3.写入订单数据库
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        //4.减库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);
            return orderDTO;

    }

    /**
     * 查询多个订单
     * @param orderId
     * @return
     */
    @Override
    public OrderDTO findOne(String orderId) {
        //1.根据orderId查找OrderMaster
        OrderMaster orderMaster=orderMasterRepository.findOne(orderId);
        if(orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //2.根据orderId查找OrderDetail
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        //3.返回OrderDTO,需要把OrderMaster和OrderDetail拼装并OrderDTO返回
        OrderDTO orderDTO = new OrderDTO();

        BeanUtils.copyProperties(orderMaster,orderDetailList);

        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    /**
     * 查询订单列表
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {

        //1.根据用户Id,查找所有次用户的OrderMaster
        Page<OrderMaster> orderMasterPage =orderMasterRepository.findByBuyerOpenid(buyerOpenid,pageable);

        //2.转换成OrderDTO
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        //3.以分页的形式返回
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());

        return orderDTOPage;
    }

    /**
     * 取消订单
     * @param orderDTO
     * @return
     */
    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        //1.判断订单状态（if orderDTO == Enum.new getCode）抛出异常
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2.修改订单状态为已取消
            //a:new OrderMaster
        OrderMaster orderMaster = new OrderMaster();
            //b:copy orderDTO OrderMaster
        BeanUtils.copyProperties(orderDTO,orderMaster);
            //c:update OderMaster.getOrderStatus()
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
            //d:save OderMaster
        OrderMaster uporderMaster = orderMasterRepository.save(orderMaster);
            // e:save返回是否 == null ,若== null,则抛出异常
        if(uporderMaster == null){
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //3.返回库存（加库存）
            //a:判断orderDetailList == null ,若== null 抛出异常
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
            //b:收集商品Id和数量放到List集合中
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());

            //c:将List传入productinfoservice中的加库存的方法(increaseStock)
        productInfoService.increaseStock(cartDTOList);

        return orderDTO;
    }

    /**
     * 完结订单
     * @param orderDTO
     * @return
     */
    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        //1.判断订单状态是否等于新订单
        //2.修改订单状态为完结
        //a:new OrderMaster
        //b:copy orderDTO OrderMaster
        //c:update OrderMaster.getOrderStatus
        //d:save OrderMaster
        //e:save 返回是否== null ,若 == null   抛出异常
        return null;
    }

    /**
     * 查询订单列表
     * @param pageable
     * @return
     */
    @Override
    public Page<OrderDTO> findList(Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        return new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }
}
