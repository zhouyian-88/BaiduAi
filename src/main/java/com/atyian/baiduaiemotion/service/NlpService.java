package com.atyian.baiduaiemotion.service;

import com.atyian.baiduaiemotion.commons.factory.AipFactory;
import com.baidu.aip.nlp.AipNlp;
import com.baidu.aip.nlp.ESimnetType;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc: Nlp的客户端
 * @datetime:2022-09-19-21:50
 */
public class NlpService {
    // 获取一个AipNlp
    private static AipNlp client = AipFactory.getNlpInstance();

    /**
     * 情感分析的AipNlp
     *
     * @param text
     * @param options
     * @return
     */
    public static JSONObject Emotion(String text, HashMap<String, Object> options) {
        // 可选：设置您自己的okHttpClient
        // 连接数超时等都可以通过okHttp提供的接口实现
        /*OkHttpClient.Builder builder = new OkHttpClient.Builder();
        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY, builder.build());*/

        // 调用接口
        // 传入可选参数调用接口
//        HashMap<String, Object> options = new HashMap<String, Object>();

        // 情感倾向分析
        return client.sentimentClassify(text, options);
    }

    /**
     * 地址识别的AipNlp
     *
     * @param text
     * @param options
     * @return
     */
    public static JSONObject address(String text, HashMap<String, Object> options) {

        // 传入可选参数调用接口
//        HashMap<String, Object> options = new HashMap<String, Object>();
        // 地址识别接口
        return client.address(text, options);
//        System.out.println(res.toString(2));
    }

    /**
     * 文章标签的AipNlp
     *
     * @param title
     * @param content
     * @param options
     * @return
     */
    public static JSONObject label(String title, String content, HashMap<String, Object> options) {
        // 文章标签
        return client.keyword(title, content, options);
//        System.out.println(res.toString(2));
    }

    /**
     * 文章分类的AipNlp
     *
     * @param title
     * @param content
     * @param options
     * @return
     */
    public static JSONObject classification(String title, String content, HashMap<String, Object> options) {
        // 文章分类
        return client.topic(title, content, options);
//        System.out.println(res.toString(2));
        //返回结果
    }

    /**
     * 新闻摘要AipNlp
     *
     * @param text
     * @param maxSummaryLen
     * @return
     */
    public static JSONObject headline(String text, int maxSummaryLen, String title) {
        // 传入可选参数调用接口
        HashMap<String, Object> options = new HashMap<String, Object>();
        if (title == null || title == "") {
            options.put("title", "标题");
        } else {
            options.put("title", title);
        }
        return client.newsSummary(text, maxSummaryLen, options);
    }

    /**
     * 对话情绪识别AipNlp
     *
     * @param text
     * @param scene
     * @return
     */
    public static JSONObject emotionRecognition(String text, String scene) {
        // 传入可选参数调用接口
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("scene", scene);

        // 对话情绪识别接口
        return client.emotion(text, options);
    }

    /**
     * 文本纠错功能AipNlp
     * @param text
     * @param options
     * @return
     */
    public static JSONObject textCorrection(String text,HashMap<String, Object> options) {
        // 文本纠错
        return client.ecnet(text, options);
//        System.out.println(res.toString(2));
    }

    /**
     * 评论观点抽取AipNlp
     * @param text
     * @param type
     * @param options
     * @return
     */
    public static JSONObject commentPoint(String text,ESimnetType type,HashMap<String, Object> options) {
       return client.commentTag(text, type, options);
    }

    /**
     * 短文本相似度AipNlp
     * @param originalText
     * @param compareText
     * @param model
     * @return
     */
    public static JSONObject textSimilarity(String originalText,String compareText,String model) {
        // 传入可选参数调用接口
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("model", model);

        // 短文本相似度
        return client.simnet(originalText, compareText, options);
    }

    /**
     * 词义相似度AipNlp
     * @param originalWord
     * @param compareWord
     * @param options
     * @return
     */
    public static JSONObject wordSimilarity(String originalWord,String compareWord,HashMap<String, Object> options) {
        // 词义相似度
       return client.wordSimEmbedding(originalWord, compareWord, options);
    }

    /**
     * 词向量AipNip
     * @param word
     * @param options
     * @return
     */
    public static JSONObject wordVector(String word,HashMap<String, Object> options) {
        // 词向量表示
        return client.wordEmbedding(word, options);
    }

    /**
     * DNN语言模型AipNip
     * @param text
     * @param options
     * @return
     */
    public static JSONObject DnnModel(String text,HashMap<String, Object> options) {
        // DNN语言模型
        return client.dnnlmCn(text, options);
    }

    /**
     * 依存句法分恩熙AipNip
     * @param text
     * @param options
     * @return
     */
    public static JSONObject wordParsing(String text,HashMap<String, Object> options) {
        // 依存句法分析
        return client.depParser(text, options);
    }

    /**
     * 词法分析
     * @param text
     * @param options
     * @return
     */
    public static JSONObject lexicalAnalysis(String text,HashMap<String, Object> options) {
        // 词法分析
        return client.lexer(text, options);
    }

}