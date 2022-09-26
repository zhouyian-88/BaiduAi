package com.atyian.baiduaiemotion.commons.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc:
 * @datetime:2022-09-18-10:01
 */
public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决post中文乱码问题
        req.setCharacterEncoding("UTF-8");
        //解决响应中文乱码问题
//        resp.setContentType("application/json;text/html");
        resp.setCharacterEncoding("utf-8");

        //获取参数为action的值
        String action = req.getParameter("action");
        try {
            //利用反射，执行方法名为action的方法
            Method declaredMethod = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            declaredMethod.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
