package com.lrl.web.converter;

import org.apache.struts2.util.StrutsTypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 *
 * 需求：
 * 把表单形式1995/09/19格式的数据转换成日期类型
 * 巴数据库中本地日期格式，转换成1995/09/19形式输出
 *
 * 自定义类转换方法
 * 1.编写一个类，继承StrutsTypeConverter，实现convertFromString ， convertToString的抽象方法；
 */
public class MyTypeConverter extends StrutsTypeConverter {

    //定义一个类型转换器
    private DateFormat format= new SimpleDateFormat("MM/dd/yyyy");
    /**
     * 把字符串数组中的数据转换成日期类型
     *
     * 方法参数详解：
     * Map context:是OGNL的上下对象；
     * String[] values:要转换的数据；
     * Class toClass:目标类型；
     *
     * @param map
     * @param values
     * @param toClass
     * @return
     */
    @Override
    public Object convertFromString(Map map, String[] values, Class toClass) {
        //1.先看看value有没有数据
        if (values ==null || values.length==0){
            return null;
        }
        //2.取出数组中的第一个元素
        String  date=values[0];
        //3.判断目标类型的字节码是不是日期类型
        if(toClass == java.util.Date.class){
            //4.使用DateFormat进行转换，并返回转换后的结果
            try {
               return format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }

        }
        return null;
    }


    /***
     * 把日期中的数据转换成字符串数组类型
     *
     * 方法参数详解：
     * Map context:是OGNL的上下对象；
     * Object o:要转换的数据
     * @param map
     * @param o
     * @return
     */
    @Override
    public String convertToString(Map map, Object o) {
        //1.判断Object 是不是日期类型
        if(o instanceof Date){
            //2.如果是日期类型，使用转换器转换成指定格式的字符串，并返回
            Date date=(Date) o;
            return format.format(date);
        }
        return null;
    }
}
