package com.zpq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zpq.model.KeyListener;

import java.util.List;
import java.util.Map;

/**
 * @author 35147
 */
public interface KeyListenerService extends IService<KeyListener> {
    List<String> getAllDates();

    List<String> getKeyNames(Map map);

    Map<String, List<Integer>> list2(Map map);

    Map<String,int[]> countKey(Map map,Integer flag);
}
