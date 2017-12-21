package com.lrl.study.service;


import com.lrl.study.DTO.CartDTO;
import com.lrl.study.domain.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {

    ProductInfo findOne(String productId);//Id查询

    List<ProductInfo> findUpAll();//查询所有上架商品

    Page<ProductInfo> finfAll(Pageable pageable);//分页返回数据

    ProductInfo save(ProductInfo productInfo);//添加数据

    void decreaseStock(List<CartDTO> cartDTOList);//减库存

    void increaseStock(List<CartDTO> cartDTOList);//加库存

    ProductInfo onSale(String productId);//上架

    ProductInfo offSale(String productId);//下架




}
