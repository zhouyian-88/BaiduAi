package com.atyian.baiduaiemotion.commons.util;

import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc: 关闭流
 * @datetime:2022-09-20-8:34
 */
public class CloseStream {
    public static void toClose(HttpServletResponse resp, JSONObject name)throws Exception{
        //返回json数据格式
        PrintWriter out = resp.getWriter();
        out.write(name.toString(2));
        //关闭流
        out.close();
    }

}
