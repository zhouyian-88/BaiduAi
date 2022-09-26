package com.atyian.baiduaiemotion.service;

import com.atyian.baiduaiemotion.commons.factory.AipFactory;
import com.baidu.aip.imageclassify.AipImageClassify;
import org.json.JSONObject;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.util.HashMap;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc:
 * @datetime:2022-09-23-14:33
 */
public class ImageService {
    private static AipImageClassify client = AipFactory.getImageClassifyInstance();

    /**
     * 该方法用于货币识别
     * @param image
     * @param options
     * @return
     */
    public static JSONObject currency(byte[] image, HashMap<String, String> options) {
        return client.currency(image, options);

    }

    /**
     * 该方法用于返回识别的动物的接口
     */
    public static JSONObject animal(byte[] image,String topNum,String baikeNum){
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("top_num", topNum);
        options.put("baike_num", baikeNum);
        return client.animalDetect(image, options);
    }

    /**
     * 该方法用于返回识别的通用物体的接口
     */
    public static JSONObject object(byte[] image,String baikeNum){
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("baike_num", baikeNum);
        return client.advancedGeneral(image, options);
    }
}
