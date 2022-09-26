package com.atyian.baiduaiemotion.contoller;

import com.atyian.baiduaiemotion.commons.controller.BaseServlet;
import com.atyian.baiduaiemotion.commons.util.CloseStream;
import com.atyian.baiduaiemotion.commons.util.FileUtils;
import com.atyian.baiduaiemotion.service.ImageProcessService;
import com.atyian.baiduaiemotion.service.ImageService;
import com.atyian.baiduaiemotion.service.OcrService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc:
 * @datetime:2022-09-25-18:00
 */
@WebServlet("/imageProcess")
public class ImageProcessController extends BaseServlet {
    /**
     * 该方法是人物动漫化
     * @param req
     * @param resp
     * @throws ServletException
     * @throws Exception
     */
    public void getPersonAnimation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        //从HttpServletRequest获取请求的图片文件
        byte[] imageBytes = FileUtils.getFileDataByRequest(req);
        //调用百度ai的人物动漫化，处理返回结果
        JSONObject jsonObj = ImageProcessService.personAnimation(imageBytes,"anime", "1");
        CloseStream.toClose(resp,jsonObj);
    }

    /**
     * 该方法用于黑白上色
     * @param req
     * @param resp
     * @throws ServletException
     * @throws Exception
     */
    public void getBlackWhite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        //从HttpServletRequest获取请求的图片文件
        byte[] imageBytes = FileUtils.getFileDataByRequest(req);
        //调用百度ai的黑白上色，处理返回结果
        JSONObject jsonObj = ImageProcessService.blackWhite(imageBytes,null);
        CloseStream.toClose(resp,jsonObj);
    }

    /**
     * 该方法用于风格转换
     * @param req
     * @param resp
     * @throws ServletException
     * @throws Exception
     */
    public void getStyleTrans(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        //获取参数
        String options = req.getParameter("type");
        //从HttpServletRequest获取请求的图片文件
        byte[] imageBytes = FileUtils.getFileDataByRequest(req);
        //调用百度ai的风格转换，处理返回结果
        JSONObject jsonObj = ImageProcessService.styleTrans(imageBytes, options);
        CloseStream.toClose(resp,jsonObj);

    }
}
