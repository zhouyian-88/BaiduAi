package com.atyian.baiduaiemotion.service;

import com.atyian.baiduaiemotion.commons.factory.AipFactory;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc: speech的客户端
 * @datetime:2022-09-20-15:40
 */
public class SpeechService {
    private static AipSpeech aipSpeech = AipFactory.getSpeechInstance();

    /**
     * 该方法用于处理语音合成的业务
     * @param text
     * @param language
     * @param option
     * @return
     */
    public static TtsResponse getVoiceMerge(String text, String language, HashMap<String, Object> option){
       return aipSpeech.synthesis(text, language, 1, option);
    }


    /**
     * 该方法用于处理语音处理的业务
     * @param data
     * @param format
     * @param rate
     * @param options
     * @return
     */
    public static JSONObject getVoiceRecognition(byte[] data,String format,int rate,HashMap<String, Object> options){
        return aipSpeech.asr(data, format, rate, options);
    }

}
