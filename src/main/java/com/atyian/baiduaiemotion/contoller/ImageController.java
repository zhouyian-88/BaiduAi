package com.atyian.baiduaiemotion.contoller;

import com.atyian.baiduaiemotion.commons.controller.BaseServlet;
import com.atyian.baiduaiemotion.commons.util.*;
import com.atyian.baiduaiemotion.service.ImageService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc:
 * @datetime:2022-09-23-14:31
 */
@WebServlet("/image")
public class ImageController extends BaseServlet {

    /**
     * 该方法用于货币识别功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws Exception
     */
    public void getCurrency(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        //从HttpServletRequest获取请求的图片文件
        byte[] imageBytes = FileUtils.getFileDataByRequest(req);
        //把imageBytes转成base64类型
//        String base64Img = Base64Util.encode(imageBytes);
        //调用百度ai的人脸检测，处理返回结果
        JSONObject jsonObj = ImageService.currency(imageBytes,null);
        CloseStream.toClose(resp,jsonObj);
    }

    /**
     * 该方法用于动物识别
     * @param req
     * @param resp
     * @throws ServletException
     * @throws Exception
     */
    public void getAnimal(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        //从HttpServletRequest获取请求的图片文件
        byte[] imageBytes = FileUtils.getFileDataByRequest(req);
        //调用百度ai的动物识别，处理返回结果
        JSONObject jsonObj = ImageService.animal(imageBytes,"6","0");
        CloseStream.toClose(resp,jsonObj);

    }

    /**
     * 该方法用于通用物体识别
     * @param req
     * @param resp
     * @throws ServletException
     * @throws Exception
     */
    public void getObject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        //从HttpServletRequest获取请求的图片文件
        byte[] imageBytes = FileUtils.getFileDataByRequest(req);
        //调用百度ai的通用动物识别，处理返回结果
        JSONObject jsonObj = ImageService.object(imageBytes,"5");
        CloseStream.toClose(resp,jsonObj);
    }
}
