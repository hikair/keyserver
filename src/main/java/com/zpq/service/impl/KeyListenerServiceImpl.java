package com.zpq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zpq.dao.KeyListenerDao;
import com.zpq.model.KeyListener;
import com.zpq.service.KeyListenerService;
import com.zpq.utils.KeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 35147
 */
@Service("keyListenerService")
public class KeyListenerServiceImpl extends ServiceImpl<KeyListenerDao, KeyListener> implements KeyListenerService {

    @Autowired(required = false)
    KeyListenerDao keyListenerDao;

    @Override
    public List<String> getAllDates() {
        return keyListenerDao.getAllDates();
    }

    @Override
    public List<String> getKeyNames(Map map) {
        return keyListenerDao.getKeyNames(map);
    }

    /**
     * ip,日期(年月日)是不变的,遍历键名
     * @param map
     * @return
     */
    @Override
    public Map<String, List<Integer>> list2(Map map) {
        String keyNames = (String) map.get("keyNames");
        String[] split = keyNames.split(",");
        Map resultMap = new HashMap();
        for (String s : split) {
            if(!"".equals(s)){
                map.put("keyNames",s);
                List<Integer> list = keyListenerDao.count(map);
                resultMap.put(s,list);
            }
        }
        return resultMap;
    }


    /**
     * 24小时*60分钟=1440分钟  统计每分钟的每个按键的敲击次数
     * 查出的日期按照升序
     * 传进来的map里keyname是有多个的,要遍历它带入dao去查
     * @param map ip,keyNames,date
     * @param flag 0按分钟统计，1按照小时统计
     * @return resultMap   key:键名  value:每一分钟或每一小时敲击次数 用数组存储
     */
    @Override
    public Map<String,int[]> countKey(Map map,Integer flag){
        String keyNames = (String) map.get("keyNames");
        String[] split = keyNames.split(",");
        int[] countNum;
        Map resultMap = new HashMap();
        for(int i=0;i<split.length;i++){
            //每个按钮都要重新new一个
            if(split[i]!=null && !"".equals(split[i])){
                map.put("keyNames",split[i]);
                List<String> dates = keyListenerDao.countKey(map);
                //返回的日期 2020-12-12 17:27:01  是按照升序排的
                //按分钟统计
                if(flag == 0){
                    countNum = new int[1440];
                    for (String date: dates) {
                        if(date!=null && !"".equals(date)){
                            //17:27:01
                            String time = date.split(" ")[1];
                            if(time!=null && !"".equals(time)){
                                int index = KeyUtils.findIndexOfMinute(time);
                                int temp = countNum[index];
                                countNum[index]=temp+1;
                            }
                        }
                    }
                }
                //按小时统计
                else {
                    countNum = new int[24];
                    for (String date : dates) {
                        if(date!=null && !"".equals(date)){
                            String time = date.split(" ")[1];
                            if(time!=null && !"".equals(time)){
                                int index = KeyUtils.findIndexOfHour(time);
                                int temp = countNum[index];
                                countNum[index]=temp+1;
                            }
                        }
                    }
                }
                //key: keyName  value: 每一分钟或每个小时这个按键的敲击次数，分装成一个数组
                resultMap.put(split[i],countNum);
            }
        }
        return resultMap;
    }
}
