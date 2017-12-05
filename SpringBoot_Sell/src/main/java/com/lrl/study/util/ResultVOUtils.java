package com.lrl.study.util;

import com.lrl.study.VO.ResultVO;

public class ResultVOUtils {

    public static ResultVO success (Object o){
        ResultVO resultVO =new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(o);
        return resultVO;
    }
}
