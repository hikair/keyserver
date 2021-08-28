package com.zpq.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zpq.model.KeyListener;

import java.util.List;
import java.util.Map;

/**
 * @author 35147
 */
public interface KeyListenerDao extends BaseMapper<KeyListener> {
    List<String> getAllDates();

    List<String> getKeyNames(Map map);

    List<Integer> count(Map map);

    List<String> countKey(Map map);
}
