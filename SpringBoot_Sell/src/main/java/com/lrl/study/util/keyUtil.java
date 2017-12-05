package com.lrl.study.util;

import java.util.Random;

/**
 * 生成订单的Id类
 */
public class keyUtil {
    public static synchronized String genUniqueKey(){
        Random random= new Random();

        /**
         * 生成6位随机数
         */
        Integer number = random.nextInt(900000)+100000;

        /**
         * 返回当前系统毫秒数+上面生成的随机数
         */
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
