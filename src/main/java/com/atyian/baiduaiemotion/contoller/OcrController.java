package com.atyian.baiduaiemotion.contoller;

import com.atyian.baiduaiemotion.commons.constant.Constants;
import com.atyian.baiduaiemotion.commons.controller.BaseServlet;
import com.atyian.baiduaiemotion.commons.util.CloseStream;
import com.atyian.baiduaiemotion.commons.util.FileUtils;
import com.atyian.baiduaiemotion.service.ImageService;
import com.atyian.baiduaiemotion.service.OcrService;
import com.baidu.aip.util.Base64Util;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.logging.ConsoleHandler;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc:
 * @datetime:2022-09-25-16:17
 */
@WebServlet("/ocr")
public class OcrController extends BaseServlet {

    /**
     * 该方法为通用文字识别功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws Exception
     */
    public void getGeneralWord(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        //获取参数
        String languageType = req.getParameter("type");
        System.out.println(languageType);
        //从HttpServletRequest获取请求的图片文件
        byte[] imageBytes = FileUtils.getFileDataByRequest(req);
        //转成base64
//        String image = Base64Util.encode(imageBytes);
        //调用百度ai的通用动物识别，处理返回结果
        JSONObject jsonObj = OcrService.generalWord(imageBytes, languageType, "true", "true", "true");
        CloseStream.toClose(resp,jsonObj);

    }

    /**
     * 该方法为身份证识别
     * @param req
     * @param resp
     * @throws ServletException
     * @throws Exception
     */
    public void getIdCard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        byte[] imageBytes = FileUtils.getFileDataByRequest(req);
        //转成base64
//        String image = Base64Util.encode(imageBytes);
        //调用百度ai的通用动物识别，处理返回结果
        JSONObject jsonObj = OcrService.idCard(imageBytes,  "back", "true", "false");
        CloseStream.toClose(resp,jsonObj);
    }
}
