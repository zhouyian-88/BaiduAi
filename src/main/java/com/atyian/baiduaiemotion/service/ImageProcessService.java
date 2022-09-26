package com.atyian.baiduaiemotion.service;

import com.atyian.baiduaiemotion.commons.factory.AipFactory;
import com.baidu.aip.imageprocess.AipImageProcess;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc:
 * @datetime:2022-09-25-17:53
 */
public class ImageProcessService {
    private static AipImageProcess client = AipFactory.getImageProcessInstance();

    /**
     * 该方法用于人物动漫化
     * @param image
     * @return
     */
    public static JSONObject personAnimation(byte[] image,String type,String maskId) {
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("type", "anime");
        options.put("mask_id", "3");
        // 参数为本地路径
        return client.selfieAnime(image, options);

    }


    /**
     * 该方法用于黑白上色
     * @param image
     * @param options
     * @return
     */
    public static JSONObject blackWhite(byte[] image, HashMap<String, String> options) {
        // 参数为本地路径
        return client.colourize(image, options);

    }

    /**
     * 该方法用于风格转换
     * @param image
     * @param option
     * @return
     */
    public static JSONObject styleTrans(byte[] image,String option) {
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("option", option);
        // 参数为本地路径
        return client.styleTrans(image, options);

    }
}
