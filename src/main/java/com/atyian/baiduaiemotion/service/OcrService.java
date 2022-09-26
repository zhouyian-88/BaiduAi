package com.atyian.baiduaiemotion.service;

import com.atyian.baiduaiemotion.commons.factory.AipFactory;
import com.baidu.aip.nlp.AipNlp;
import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc:
 * @datetime:2022-09-25-16:11
 */
public class OcrService {
    //获取一个aipOcr
    private static AipOcr client = AipFactory.getOcrInstance();

    /**
     * 该方法是处理通用文字识别
     * @param image
     * @param languageType
     * @param detectDirection
     * @param detectLanguage
     * @param probability
     * @return
     */
    public static JSONObject generalWord(byte[] image,String languageType,String detectDirection,String detectLanguage,String probability) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", languageType);
        options.put("detect_direction", detectDirection);
        options.put("detect_language", detectLanguage);
        options.put("probability", probability);
        // 参数为本地图片路径
      return client.basicGeneral(image, options);

    }


    /**
     * 该方法用于处理身份证识别
     * @param image
     * @param idCardSide
     * @param detectDirection
     * @param detectRisk
     * @return
     */
    public static JSONObject idCard(byte[] image,String idCardSide,String detectDirection,String detectRisk) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", detectDirection);
        options.put("detect_risk", detectRisk);
       return client.idcard(image, idCardSide, options);
    }
}
