package com.atyian.baiduaiemotion.commons.util;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc:
 * @datetime:2022-09-26-16:13
 */
public class SpiltBytes {

    public static byte[] splitOne(int fileLen,byte[] imageByte){
        byte[] bytes = new byte[fileLen];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = imageByte[i];
        }
        return bytes;
    }

    public static byte[] splitTwo(int fileLen1,int fileLen2,byte[] imageByte){
        byte[] bytes = new byte[fileLen2];
        int len = 0;
        for (int i = fileLen1; i < imageByte.length; i++) {
            bytes[len++] = imageByte[i];
        }
        return bytes;
    }
}
