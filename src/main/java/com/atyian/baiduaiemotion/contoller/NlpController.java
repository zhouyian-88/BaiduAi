package com.atyian.baiduaiemotion.contoller;

import com.alibaba.fastjson2.JSON;
import com.atyian.baiduaiemotion.commons.controller.BaseServlet;
import com.atyian.baiduaiemotion.commons.util.CloseStream;
import com.atyian.baiduaiemotion.commons.util.EsimnetType;
import com.atyian.baiduaiemotion.service.NlpService;
import com.atyian.baiduaiemotion.entity.Emotion;
import com.atyian.baiduaiemotion.service.EmotionService;
import com.atyian.baiduaiemotion.service.impl.EmotionServiceImpl;
import com.baidu.aip.nlp.ESimnetType;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc:
 * @datetime:2022-09-19-16:53
 */
@WebServlet("/nlp")
public class NlpController extends BaseServlet {
    private EmotionService emotionService = new EmotionServiceImpl();

    /**
     * 情感分析功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void emotionAnalysis(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        //获取参数
        String text = req.getParameter("text");
        //调用controller，处理业务
        JSONObject emotion = NlpService.Emotion(text, null);
        System.out.println(emotion);


        Emotion emotion1 = JSON.parseObject(String.valueOf(emotion), Emotion.class);
        System.out.println(emotion1);
        int i = emotionService.saveCreateEmotion(emotion1);
        System.out.println(i);

        //返回json数据格式
//        PrintWriter out = resp.getWriter();
//        out.write(emotion.toString(2));
//        //关闭流
//        out.close();
        CloseStream.toClose(resp, emotion);
    }

    /**
     * 地址识别功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void addressRecognize(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        //获取参数
        String text = req.getParameter("text");
        //调用NlpClient，获取结果
        JSONObject address = NlpService.address(text, null);
        System.out.println(address);
        //将得到的地址返回到前台和关闭流
        CloseStream.toClose(resp, address);
    }


    /**
     * 文章标签功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws Exception
     */
    public void artLabel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        //获取参数
        String title = req.getParameter("title");
        String text = req.getParameter("text");
        //调用NlpClient,获取百度ai处理
        JSONObject label = NlpService.label(title, text, null);
        System.out.println(label);
        //将百度ai处理得到的结果返回到前端
        CloseStream.toClose(resp, label);
    }

    /**
     * 文章分类功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws Exception
     */
    public void artClassification(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        //获取参数
        String title = req.getParameter("title");
        String text = req.getParameter("text");
        //调用NlpClient,获取百度ai处理
        JSONObject classification = NlpService.classification(title, text, null);
        System.out.println(classification);
        //将百度ai处理得到的结果返回到前端
        CloseStream.toClose(resp, classification);
    }

    /**
     * 新闻摘要功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws Exception
     */
    public void headline(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        //获取参数
        String title = req.getParameter("title");
        String text = req.getParameter("text");
        String maxSummaryLen = req.getParameter("max_summary_len");
        Integer maxLen = Integer.valueOf(maxSummaryLen);
        //调用NlpClient,获取百度ai处理
        JSONObject headline = NlpService.headline(text, maxLen, title);
        System.out.println(headline);
        //将百度ai处理得到的结果返回到前端
        CloseStream.toClose(resp, headline);
    }

    /**
     * 对话情绪分析功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws Exception
     */
    public void getEmotionRecognize(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        //获取参数
        String text = req.getParameter("text");
        String scene = req.getParameter("scene");
        JSONObject jsonObj = NlpService.emotionRecognition(text, scene);
        System.out.println(jsonObj);
        CloseStream.toClose(resp,jsonObj);

    }

    /**
     * 文本纠错功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws Exception
     */
    public void getTextCorrection(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        //获取参数
        String text = req.getParameter("text");
        JSONObject jsonObj = NlpService.textCorrection(text, null);
        System.out.println(jsonObj);
        CloseStream.toClose(resp,jsonObj);
    }

    /**
     * 评论观点抽取
     * @param req
     * @param resp
     * @throws ServletException
     * @throws Exception
     */
    public void getCommentPoint(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        String text = req.getParameter("text");
        String type = req.getParameter("type");
        ESimnetType eSimnetType = EsimnetType.getESimnetType(type);
        JSONObject jsonObj = NlpService.commentPoint(text, eSimnetType, null);
        System.out.println(jsonObj);
        CloseStream.toClose(resp,jsonObj);
    }

    /**
     * 短文本相似度功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws Exception
     */
    public void getTextSimilarity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        String originalText = req.getParameter("originalText");
        String compareText = req.getParameter("compareText");
        String model = req.getParameter("model");
        JSONObject jsonObj = NlpService.textSimilarity(originalText, compareText, model);
        CloseStream.toClose(resp,jsonObj);
    }

    /**
     * 词义相似度功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws Exception
     */
    public void getWordSimilarity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        String originalWord = req.getParameter("originalWord");
        String compareWord = req.getParameter("compareWord");
        JSONObject jsonObj = NlpService.wordSimilarity(originalWord, compareWord, null);
        CloseStream.toClose(resp,jsonObj);
    }


    /**
     * 依存句法分析表示功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws Exception
     */
    public void getWordParsing(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        String word = req.getParameter("word");
        JSONObject jsonObj = NlpService.wordParsing(word, null);
        CloseStream.toClose(resp,jsonObj);
    }


    /**
     * DNN语言模型功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws Exception
     */
    public void getDnnModel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        String text = req.getParameter("text");
        JSONObject jsonObj = NlpService.DnnModel(text, null);
        CloseStream.toClose(resp,jsonObj);
    }


    /**
     * 词法向量表示功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws Exception
     */
    public void getWordVector(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        String word = req.getParameter("word");
        JSONObject jsonObj = NlpService.wordVector(word, null);
        CloseStream.toClose(resp,jsonObj);
    }


    /**
     * 词法分析功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws Exception
     */
    public void getLexAnalysis(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        String text = req.getParameter("text");
        JSONObject jsonObj = NlpService.lexicalAnalysis(text, null);
        System.out.println(jsonObj);
        CloseStream.toClose(resp,jsonObj);
    }

}
