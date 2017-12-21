package com.lrl.study.repository;
import com.lrl.study.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * 功能描述: <br>
 * 〈商品类〉
 *
 No such property: code for class: Script1
 * @return:
 * @since: 1.0.0
 * @Author:LRL
 * @Date: 2017/12/11 22:48
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /**
     * 查询上架商品
     * @param productStatus
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
