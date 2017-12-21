package com.lrl.study.contorller;


import com.lrl.study.VO.ProductInfoVO;
import com.lrl.study.VO.ProductVO;
import com.lrl.study.VO.ResultVO;
import com.lrl.study.dataobject.ProductCategory;
import com.lrl.study.dataobject.ProductInfo;
import com.lrl.study.service.CategoryService;
import com.lrl.study.service.ProductService;
import com.lrl.study.utils.ResultVOUtil;
import com.sun.java.swing.plaf.windows.WindowsTreeUI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述: <br>
 * 〈买家商品〉
 *
 No such property: code for class: Script1
 * @return:
 * @since: 1.0.0
 * @Author:LRL
 * @Date: 2017/12/12 12:50
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductContorller {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list(){

        //1.查询所有的上架商品
        List<ProductInfo>productInfoList = productService.findUpAll();

        //2.查询类目（一次性查询）
        /**
         * 传统写法
        List<Integer> categoryTypeList = new ArrayList<>();
        for(ProductInfo productInfo: productInfoList){
            categoryTypeList.add(productInfo.getCategoryType());
        }
         */

        /**
         *
         * 精简写法
         */
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3.数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory: productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo: productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }

}
