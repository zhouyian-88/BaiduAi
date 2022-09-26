package com.atyian.baiduaiemotion.service.impl;

import com.atyian.baiduaiemotion.dao.EmotionDao;
import com.atyian.baiduaiemotion.dao.impl.EmotionDaoImpl;
import com.atyian.baiduaiemotion.entity.Emotion;
import com.atyian.baiduaiemotion.service.EmotionService;

import java.util.List;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc:
 * @datetime:2022-09-19-20:10
 */
public class EmotionServiceImpl implements EmotionService {
    private EmotionDao emotionDao = new EmotionDaoImpl();
    @Override
    public int saveCreateEmotion(Emotion emotion) {
        return emotionDao.insertEmotion(emotion);
    }
}
