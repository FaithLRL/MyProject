package com.lrl.study.repository;

import com.lrl.study.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 功能描述: <br>
 * 〈买家订单〉
 *
 No such property: code for class: Script1
 * @return:
 * @since: 1.0.0
 * @Author:LRL
 * @Date: 2017/12/12 22:26
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    /**
     * 分页查询用户订单
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
