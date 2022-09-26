package com.atyian.baiduaiemotion.contoller;

import com.atyian.baiduaiemotion.commons.controller.BaseServlet;
import com.atyian.baiduaiemotion.commons.util.CloseStream;
import com.atyian.baiduaiemotion.commons.util.FileUtils;
import com.atyian.baiduaiemotion.service.SpeechService;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Base64Util;
import org.json.JSONObject;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc:
 * @datetime:2022-09-20-15:48
 */
@WebServlet("/speech")
public class SpeechController extends BaseServlet {
    /**
     * 该方法为语音合成功能
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getVoiceMerge(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取参数
        String text = req.getParameter("text");
        String spd = req.getParameter("spd");
        String pit = req.getParameter("pit");
        String vol = req.getParameter("vol");
        String per = req.getParameter("per");

            /*
        最长的长度
         */
        int maxLength = 1024;
        if (text.getBytes().length >= maxLength) {
            return;
        }
//        System.out.println(text);
        /// 设置可选参数
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("spd", spd);
        options.put("pit", pit);
        options.put("vol",vol);
        options.put("per", per);
        TtsResponse res = SpeechService.getVoiceMerge(text, "zh", options);
        //JSONObject result = res.getResult();    //服务器返回的内容，合成成功时为null,失败时包含error_no等信息
        byte[] data = res.getData();            //生成的音频数据
        String base64Voice = Base64Util.encode(data);
        System.out.println(base64Voice);
        //转成json格式
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("data", base64Voice);
//        PrintWriter out = resp.getWriter();
//        out.write(jsonObj.toString());
        CloseStream.toClose(resp,jsonObj);
    }

    /**
     * 该方法为语音识别功能
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getVoiceRecognition(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        从HttpServletRequest获取语音文件
        byte[] voiceBytes = FileUtils.getFileDataByRequest(req);
        //调用百度ai语音技术，处理结果
        JSONObject jsonObj = SpeechService.getVoiceRecognition(voiceBytes, "pcm", 16000, null);
        //关闭流
        CloseStream.toClose(resp,jsonObj);
    }
}
