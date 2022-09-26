package com.atyian.baiduaiemotion.commons.util;

import com.baidu.aip.nlp.ESimnetType;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc:
 * @datetime:2022-09-21-19:23
 */
public class EsimnetType {
    public static ESimnetType getESimnetType(String type){
        ESimnetType eSimnetType = null;
        switch (type){
            case "1":
                eSimnetType = ESimnetType.HOTEL;
                break;
            case "2":
                eSimnetType = ESimnetType.KTV;
                break;
            case "3":
                eSimnetType = ESimnetType.BEAUTY;
                break;
            case "4":
                eSimnetType = ESimnetType.FOOD;
                break;
            case "5":
                eSimnetType = ESimnetType.TRAVEL;
                break;
            case "6":
                eSimnetType = ESimnetType.HEALTH;
                break;
            case "7":
                eSimnetType = ESimnetType.EDU;
                break;
            case "8":
                eSimnetType = ESimnetType.BUSINESS;
                break;
            case "9":
                eSimnetType = ESimnetType.HOUSE;
                break;
            case "10":
                eSimnetType = ESimnetType.CAR;
                break;
            case "11":
                eSimnetType = ESimnetType.LIFE;
                break;
            case "12":
                eSimnetType = ESimnetType.SHOPPING;
                break;
            case "13":
                eSimnetType = ESimnetType._3C;
                break;
            default:
                eSimnetType = ESimnetType.FOOD;
                break;
        }
        return eSimnetType;
    }
}
