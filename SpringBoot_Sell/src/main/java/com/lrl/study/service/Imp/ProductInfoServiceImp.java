package com.lrl.study.service.Imp;


import com.lrl.study.DTO.CartDTO;
import com.lrl.study.domain.ProductInfo;
import com.lrl.study.enums.ProductStatusEnum;
import com.lrl.study.enums.ResultEnum;
import com.lrl.study.exception.SellException;
import com.lrl.study.repository.ProductInfoRepository;
import com.lrl.study.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class ProductInfoServiceImp implements ProductInfoService {

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
    public Page<ProductInfo> finfAll(Pageable pageable) {
        return this.productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {

        return this.productInfoRepository.save(productInfo);
    }


    /**
     * 减库存
     * @param cartDTOList
     */
    @Override
    /**
     * 事务回滚
     *  @Transactional
     */
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        //遍历(for循环 cartDTOList)
        for (CartDTO cartDTO:cartDTOList){
            //查询商品 （商品对象（详情））(productInfoService.findOne)
            ProductInfo productInfo=productInfoRepository.findOne(cartDTO.getProductId());
            //商品是否存在（if productInfo）
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //productInfo -- cartDTOList.getProductQuantity
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            //商品库存(if productIfo < 0)
            if(result < 0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            //更新商品库存数
            productInfo.setProductStock(result);

            //save
            productInfoRepository.save(productInfo);
        }

    }


    /**
     * 加库存
     * @param cartDTOList
     */
    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {

        //遍历(for循环 cartDTOList)
        for (CartDTO cartDTO:cartDTOList){

            //查询商品 （商品对象（详情））(productInfoService.findOne)
            ProductInfo productInfo=productInfoRepository.findOne(cartDTO.getProductId());

            //商品是否存在（if productInfo）
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //productInfo -- cartDTOList.getProductQuantity
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();

            //更新商品库存数
            productInfo.setProductStock(result);

            //save
            productInfoRepository.save(productInfo);
        }
    }

    /**
     * 上架
     * @param productId
     * @return
     */
    @Override
    public ProductInfo onSale(String productId) {
        //1.根据productId --> findOne查找商品对象
        ProductInfo productInfo = productInfoRepository.findOne(productId);
        //2.if(判断商品对象是否存在) 不存在抛出异常
        if (productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        //3.if(判断商品对象是否存在Status状态是否为UP(上架状态)）状态不对，抛出异常
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);

        }
        //4.修改商品对象的productStatus为UP（下架状态）
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        //5.sve并 return
        return productInfoRepository.save(productInfo);
    }

    /**
     * 下架
     * @param productId
     * @return
     */
    @Override
    public ProductInfo offSale(String productId) {
        //1.根据productId --> findOne查找商品对象
        ProductInfo productInfo = productInfoRepository.findOne(productId);
        //2.if(判断商品对象是否存在) 不存在抛出异常
        if (productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        //3.if(判断商品对象是否存在Status状态是否为DOWN(下架状态)）状态不对，抛出异常
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //4.修改商品对象的productStatus为DOWN（上架状态）
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        //5.sve并 return
        return productInfoRepository.save(productInfo);
    }
}
