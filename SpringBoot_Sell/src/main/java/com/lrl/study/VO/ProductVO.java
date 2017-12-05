package com.lrl.study.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lrl.study.domain.ProductInfo;


import java.util.List;

public class ProductVO<T> {
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfo> categoryPproductInfoList;


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public List<ProductInfo> getCategoryPproductInfoList() {
        return categoryPproductInfoList;
    }

    public void setCategoryPproductInfoList(List<ProductInfo> categoryPproductInfoList) {
        this.categoryPproductInfoList = categoryPproductInfoList;
    }
}
