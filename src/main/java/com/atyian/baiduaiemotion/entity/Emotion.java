package com.atyian.baiduaiemotion.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc:
 * @datetime:2022-09-19-19:48
 */
@Data
public class Emotion {
    @JSONField(name="log_id")
    private String logId;
    private String text;
    private List<Items> items = new ArrayList<>();

}
