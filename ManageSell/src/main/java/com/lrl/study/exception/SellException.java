package com.lrl.study.exception;

import com.lrl.study.enums.ResultEnum;

/**
 * 功能描述: <br>
 * 〈销售异常〉
 *
 No such property: code for class: Script1
 * @return:
 * @since: 1.0.0
 * @Author:LRL
 * @Date: 2017/12/15 16:57
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
