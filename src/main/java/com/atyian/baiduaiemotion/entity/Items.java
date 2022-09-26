package com.atyian.baiduaiemotion.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc:
 * @datetime:2022-09-19-20:55
 */
@Data
public class Items {
    @JSONField(name="positive_prob")
    private String positiveProb;
    @JSONField(name="sentiment")
    private Integer sentiment;
    @JSONField(name="confidence")
    private String confidence;
    @JSONField(name="negative_prob")
    private String negativeProb;
}
