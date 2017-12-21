package com.lrl.study.service.imp;

import com.lrl.study.DTO.CartDTO;
import com.lrl.study.dataobject.ProductInfo;
import com.lrl.study.enums.ProductStatusEnum;
import com.lrl.study.enums.ResultEnum;
import com.lrl.study.exception.SellException;
import com.lrl.study.repository.ProductInfoRepository;
import com.lrl.study.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述: <br>
 * 〈商品操作实现类〉
 *
 No such property: code for class: Script1
 * @return:
 * @since: 1.0.0
 * @Author:LRL
 * @Date: 2017/12/12 12:14
 */
@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String productId) {
        return this.productInfoRepository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return this.productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return this.productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return this.productInfoRepository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {

    }

    /**
     * 减库存
     * @param cartDTOList
     */
    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {

        for (CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(result<0){
                throw new SellException()
            }
        }

    }
}
