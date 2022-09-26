package com.atyian.baiduaiemotion.dao.impl;

import com.atyian.baiduaiemotion.dao.EmotionDao;
import com.atyian.baiduaiemotion.entity.Emotion;
import com.atyian.baiduaiemotion.entity.Items;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc:
 * @datetime:2022-09-19-19:46
 */
public class EmotionDaoImpl extends BaseDao implements EmotionDao {
    @Override
    public int insertEmotion(Emotion emotion) {
        String sql = "insert into emotion (logId,text,positive_prob,sentiment,confidence,negative_prob)values(?,?,?,?,?,?)";
        return update(sql,emotion.getLogId(),emotion.getText(),
                emotion.getItems().get(0).getPositiveProb(),emotion.getItems().get(0).getSentiment(),emotion.getItems().get(0).getConfidence(),emotion.getItems().get(0).getNegativeProb());
    }
}
