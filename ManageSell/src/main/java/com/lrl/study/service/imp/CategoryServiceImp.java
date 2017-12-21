package com.lrl.study.service.imp;

import com.lrl.study.dataobject.ProductCategory;
import com.lrl.study.repository.ProductCategoryRepository;
import com.lrl.study.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 功能描述: <br>
 * 〈类目的实现类〉
 *
 No such property: code for class: Script1
 * @return:
 * @since: 1.0.0
 * @Author:LRL
 * @Date: 2017/12/11 22:03
 */
@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    /**
     * 根据Id查询类目
     * @param catedoryId
     * @return
     */
    @Override
    public ProductCategory findOne(Integer catedoryId) {
        return this.productCategoryRepository.findOne(catedoryId) ;
    }

    /**
     * 查询所有类目
     * @return
     */
    @Override
    public List<ProductCategory> findAll() {
        return this.productCategoryRepository.findAll();
    }

    /**
     *分支
     * @param categoryTypeList
     * @return
     */
    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return this.productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }

    /**
     * 添加类目
     * @param productCategory
     * @return
     */
    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return this.productCategoryRepository.save(productCategory);


    }
}
