package com.atyian.baiduaiemotion.contoller;

import com.atyian.baiduaiemotion.commons.controller.BaseServlet;
import com.atyian.baiduaiemotion.commons.util.CloseStream;
import com.atyian.baiduaiemotion.commons.util.SpiltBytes;
import com.atyian.baiduaiemotion.service.FaceService;
import com.atyian.baiduaiemotion.commons.util.FileUtils;
import com.baidu.aip.face.MatchRequest;
import com.baidu.aip.util.Base64Util;
import org.json.JSONObject;

import javax.imageio.IIOImage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc:
 * @datetime:2022-09-22-14:05
 */
@WebServlet("/face")
public class FaceController extends BaseServlet {

    /**
     * 该方法用于人脸检测功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws Exception
     */
    public void getFaceDetection(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        //获取前端请求参数
//        String age = req.getParameter("age");
//        String beauty = req.getParameter("beauty");
//        String expression = req.getParameter("expression");
//        String faceShape = req.getParameter("face_shape");
//        String gender = req.getParameter("gender");
//        String glasses = req.getParameter("glasses");
//        String quality = req.getParameter("quality");
//        String eye_status = req.getParameter("eye_status");
//        String emotion = req.getParameter("emotion");
//        String face_type = req.getParameter("face_type");
        String face_field = req.getParameter("face_field");
        System.out.println(face_field);
        //设置参数
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("face_field", face_field);
        options.put("max_face_num", "2");
        options.put("face_type", "LIVE");
        options.put("liveness_control", "LOW");
        //从HttpServletRequest获取请求的图片文件
        byte[] imageBytes = FileUtils.getFileDataByRequest(req);

        System.out.println(imageBytes.toString());
        //把imageBytes转成base64类型
        String base64Img = Base64Util.encode(imageBytes);
        //调用百度ai的人脸检测，处理返回结果
        JSONObject jsonObj = FaceService.faceDetection(base64Img, "BASE64", options);
        CloseStream.toClose(resp,jsonObj);


    }


    /**
     * 该方法用于人脸对比功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws Exception
     */
    public void getFaceCompare(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        //从HttpServletRequest获取请求的图片文件
        //获取文件的大小
        String size1 = req.getParameter("size1");
        String size2 = req.getParameter("size2");
        int sizeInteger1 = Integer.valueOf(size1);
        int sizeInteger2 = Integer.valueOf(size2);
        byte[] imageBytes = FileUtils.getFileDataByRequest(req);


        byte[] bytes1 = SpiltBytes.splitOne(sizeInteger1, imageBytes);
        byte[] bytes2 = SpiltBytes.splitTwo(sizeInteger1, sizeInteger2, imageBytes);

        //把imageBytes转成base64类型
        String base64Img1 = Base64Util.encode(bytes1);
        String base64Img2 = Base64Util.encode(bytes2);
//        System.out.println(base64Img1);
//        System.out.println(base64Img2);
        // image1/image2也可以为url或facetoken, 相应的imageType参数需要与之对应。
        MatchRequest req1 = new MatchRequest(base64Img1, "BASE64");
        MatchRequest req2 = new MatchRequest(base64Img2, "BASE64");
        ArrayList<MatchRequest> requests = new ArrayList<MatchRequest>();
        requests.add(req1);
        requests.add(req2);

        JSONObject jsonObj = FaceService.faceCompare(requests);
        CloseStream.toClose(resp,jsonObj);
    }
}
