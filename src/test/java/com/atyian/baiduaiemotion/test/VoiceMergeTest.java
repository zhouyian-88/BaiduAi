package com.atyian.baiduaiemotion.test;

import com.atyian.baiduaiemotion.commons.constant.Constants;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc:
 * @datetime:2022-09-20-17:27
 */
public class VoiceMergeTest {
    public static void main(String[] args) {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(Constants.APPID, Constants.API_KEY, Constants.SECRET_KEY);
        ;


        // 设置可选参数
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("spd", "5");
        options.put("pit", "5");
        options.put("per", "4");
        TtsResponse res = client.synthesis("你好百度", "zh", 1, options);
        JSONObject result = res.getResult();    //服务器返回的内容，合成成功时为null,失败时包含error_no等信息
        byte[] data = res.getData();            //生成的音频数据
        JSONObject res1 = res.getResult();
        if (data != null) {
            try {
                Util.writeBytesToFileSystem(data, "output.mp3");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (res1 != null) {
            System.out.println(res1.toString(2));
        }

    }
}
