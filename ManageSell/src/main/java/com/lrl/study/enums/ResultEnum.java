package com.lrl.study.enums;

/**
 * 功能描述: <br>
 * 〈〉
 *
 No such property: code for class: Script1
 * @return:
 * @since: 1.0.0
 * @Author:LRL
 * @Date: 2017/12/15 16:59
 */
public enum ResultEnum {

    PRODUCT_NOT_EXIST(10, "商品不存在"),
    product_stTock_error()
    ;

    private Integer code;

    private String Message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        Message = message;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
