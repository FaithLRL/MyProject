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
}
