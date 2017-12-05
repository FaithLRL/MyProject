package com.lrl.study.utils;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;



@MappedSuperclass
public class TimeUtils {

    /**
     * 表示创建时间不变false
     * @Column(updatable = false)
     */
    /**
     * 创建时间
     */
    @Column(updatable = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private long createTime;

    /**
     * 更新时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private long updateTime;



    //插入自动时间
    @PrePersist
    public void prePersist(){
        Date date =new Date();
        this.updateTime=date.getTime();
        this.createTime=this.updateTime;
    }

    @PreUpdate
    public void preUpdate(){
        Date date =new Date();
        this.updateTime=date.getTime();
    }


}
