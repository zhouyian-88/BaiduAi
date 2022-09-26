package com.atyian.baiduaiemotion.service;

import com.atyian.baiduaiemotion.commons.factory.AipFactory;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import jdk.internal.org.objectweb.asm.commons.StaticInitMerger;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc:
 * @datetime:2022-09-22-14:06
 */
public class FaceService {
    private static AipFace client = AipFactory.getFaceInstance();

    /**
     * 人脸检测功能
     * @param image
     * @param imageType
     * @param options
     * @return
     */
    public static JSONObject faceDetection(String image,String imageType ,HashMap<String, String> options) {
       return client.detect(image, imageType, options);

    }

    /**
     * 该方法用于人脸对比功能
     * @param requests
     * @return
     */
    public static JSONObject faceCompare(ArrayList<MatchRequest> requests) {
        return client.match(requests);
    }

}
