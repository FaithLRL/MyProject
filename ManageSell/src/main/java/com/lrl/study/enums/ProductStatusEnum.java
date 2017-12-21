package com.lrl.study.enums;

/**
 * 功能描述: <br>
 * 〈商品状态〉
 *
 No such property: code for class: Script1
 * @return:
 * @since: 1.0.0
 * @Author:LRL
 * @Date: 2017/12/12 12:27
 */
public enum ProductStatusEnum {
    UP(0, "在架"),
    DOWN(1, "下架"),
    ;
    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
