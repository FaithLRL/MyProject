package com.lrl.result;

import cn.dsna.util.images.ValidateCode;
import com.opensymphony.xwork2.ActionInvocation;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义结果类型
 */
public class CAPTCHAResult extends StrutsResultSupport {

    @Override
    protected void doExecute(String s, ActionInvocation actionInvocation) throws Exception {
//        int width = 200;
//         int height= 30;
//        BufferedImage image =new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
//        image.getGraphics();
        /**
         * 使用第三方生成验证码的jar包
         * 1.拷贝ValidateCode.jar包到工程lib目录
         * 2.创建ValidateCode的对象
         * 3.获取相应对象的输出流
         * 4.输出到浏览器
         */

        //创建ValidateCode的对象
        ValidateCode code=new ValidateCode(200,40,4,10);
        //获取相应对象的输出流
        HttpServletResponse response = ServletActionContext.getResponse();
        //输出到浏览器
        code.write(response.getOutputStream());
    }
}
