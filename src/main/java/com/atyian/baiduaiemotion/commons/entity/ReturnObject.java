package com.atyian.baiduaiemotion.commons.entity;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc:
 * @datetime:2022-09-18-10:21
 */
public class ReturnObject {
    private String code;//处理成功或者失败的标识，0--->表示失败，1--->表示成功
    private String message;//提示信息
    private Object returnObject;//其他信息

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getReturnObject() {
        return returnObject;
    }

    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

}
