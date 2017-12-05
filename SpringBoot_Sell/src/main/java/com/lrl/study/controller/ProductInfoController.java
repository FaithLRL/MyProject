package com.lrl.study.controller;


import com.lrl.study.VO.ProductInfoVO;
import com.lrl.study.VO.ProductVO;
import com.lrl.study.VO.ResultVO;
import com.lrl.study.domain.ProductCategory;
import com.lrl.study.domain.ProductInfo;
import com.lrl.study.service.PorductCategoryService;
import com.lrl.study.service.ProductInfoService;
import com.lrl.study.util.ResultVOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class ProductInfoController extends ResultVOUtils{

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private PorductCategoryService porductCategoryService;

    @GetMapping("/result")
    public ResultVO resultVO(){

        //第一步查询所有上架商品
        List<ProductInfo> productInfoList = this.productInfoService.findUpAll();
        //第二步查询所有上架商品的类目
//        List<Integer> categotyTypeList=new ArrayList<>();
//        for(ProductInfo productInfo: productInfoList){
//            categotyTypeList.add(productInfo.getCategoryType());
//        }
        List<Integer> categotyTypeList=productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());

        List<ProductCategory> productCategoryList=
                this.porductCategoryService.findByCategoryTypeIn(categotyTypeList);


        //第三部是数据拼装
        List<ProductVO> productVOList =new ArrayList<>();
        for(ProductCategory productCategory: productCategoryList){
            ProductVO productVO=new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo: productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO =new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);

                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setCategoryPproductInfoList(productInfoList);
            productVOList.add(productVO);
        }
        return ResultVOUtils.success(productVOList);
    }
}
