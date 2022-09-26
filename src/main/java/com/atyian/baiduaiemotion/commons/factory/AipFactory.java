package com.atyian.baiduaiemotion.commons.factory;

import com.atyian.baiduaiemotion.commons.constant.Constants;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.imageprocess.AipImageProcess;
import com.baidu.aip.nlp.AipNlp;
import com.baidu.aip.ocr.AipOcr;
import com.baidu.aip.speech.AipSpeech;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc: AipFactory是一个工厂类，只负责创建不同的对象
 * 创建对象时采用单例模式
 * @datetime:2022-09-19-14:46
 */
public class AipFactory {

    private static AipNlp aipNlp;
    private static AipSpeech aipSpeech;
    private static AipFace aipFace;
    private static AipImageClassify aipImageClassify;
    private static AipOcr aipOcr;
    private static AipImageProcess aipImageProcess;

    private AipFactory() {
    }

    /**
     * 自然语言的aip的实例
     * @return
     */
    public synchronized static AipNlp getNlpInstance(){
        //判断AipNlp是否存在
        if(aipNlp!=null){
            return aipNlp;
        }
        //如果不存在则创建一个AipNlp
        aipNlp = new AipNlp(Constants.APPID, Constants.API_KEY, Constants.SECRET_KEY);

        // 可选：设置网络连接参数
        aipNlp.setConnectionTimeoutInMillis(2000);
        aipNlp.setSocketTimeoutInMillis(60000);
        return aipNlp;
    }
    /**
     * 语言技术的aip的实例
     * @return
     */
    public synchronized static AipSpeech getSpeechInstance(){
        //判断AipNlp是否存在
        if(aipSpeech!=null){
            return aipSpeech;
        }
        //如果不存在则创建一个AipNlp
        aipSpeech =new AipSpeech(Constants.APPID, Constants.API_KEY, Constants.SECRET_KEY);

        // 可选：设置网络连接参数
        aipSpeech.setConnectionTimeoutInMillis(2000);
        aipSpeech.setSocketTimeoutInMillis(60000);
        return aipSpeech;
    }
    /**
     * 人脸识别的aip的实例
     * @return
     */

    public synchronized static AipFace getFaceInstance(){
        //判断aipFace是否存在
        if(aipFace!=null){
            return aipFace;
        }
       aipFace = new AipFace(Constants.APPID, Constants.API_KEY, Constants.SECRET_KEY);
        return aipFace;
    }

    /**
     * 该方法采用单例模式形式创建aipImageClassify实例对象，供全局使用
     */
    public synchronized static AipImageClassify getImageClassifyInstance(){
        //判断aipOcr是否存在
        if(aipImageClassify != null){
            return aipImageClassify;
        }
        aipImageClassify = new AipImageClassify(Constants.APPID,Constants.API_KEY,Constants.SECRET_KEY);
        return aipImageClassify;
    }

    /**
     * 该方法采用单例模式形式创建aipOcr实例对象，供全局使用
     */
    public synchronized static AipOcr getOcrInstance(){
        //判断aipOcr是否存在
        if(aipOcr != null){
            return aipOcr;
        }
        aipOcr = new AipOcr(Constants.APPID,Constants.API_KEY,Constants.SECRET_KEY);
        return aipOcr;
    }
    public synchronized static AipImageProcess getImageProcessInstance(){
        //判断aipImageProcess是否存在
        if(aipImageProcess != null){
            return aipImageProcess;
        }
        aipImageProcess = new AipImageProcess(Constants.APPID,Constants.API_KEY,Constants.SECRET_KEY);
        return aipImageProcess;
    }
}
