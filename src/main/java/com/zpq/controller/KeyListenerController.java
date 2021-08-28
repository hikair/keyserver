package com.zpq.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zpq.model.KeyListener;
import com.zpq.model.Result;
import com.zpq.service.KeyListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 35147
 */
@RestController
@RequestMapping("/key")
public class KeyListenerController {
    @Autowired
    KeyListenerService keyListenerService;

    @RequestMapping("/list")
    public Result<Map> list(String query,int pageNum, int size){
        Page<KeyListener> page = new Page<>(pageNum, size);
        QueryWrapper<KeyListener> sql = new QueryWrapper<>();
        sql.like("host_address",query);
        sql.select().orderByDesc("create_date");
        keyListenerService.page(page,sql);
        List<KeyListener> keyListeners = page.getRecords();
        Map map = new HashMap(4);
        map.put("data",keyListeners);
        map.put("pageCount",page.getPages());
        map.put("rowCount",page.getTotal());
        return new Result<>(true,200,"success",map);
    }

    /**
     * 获取全部ip地址
     * select DISTINCT host_address from key_listener;
     * @return
     */
    @RequestMapping("/getAllIps")
    public Result<List<KeyListener>> getAllIps(){
        QueryWrapper<KeyListener> sql = new QueryWrapper<>();
        sql.select("distinct host_address");
        List<KeyListener> list = keyListenerService.list(sql);
        return new Result<>(true,200,"success",list);
    }

    /**
     * 获取全部日期
     * select distinct DATE_FORMAT(create_date,'%Y-%m-%d') from key_listener
     * @return
     */
    @RequestMapping("/getAllDates")
    public Result<List<String>> getAllDates(){
        List<String> list = keyListenerService.getAllDates();
        return new Result<>(true,200,"success",list);
    }

    /**s
     * 获取某个ip它统计的所有按键名字
     * select DISTINCT key_name from key_listener
     * where host_address='127.0.0.1'
     * and DATE_FORMAT(create_date,'%Y-%m-%d') = '2020-11-16';
     * @param ip
     * @return
     */
    @RequestMapping("/getKeyNames")
    public Result<List<String>> getKeyNames(String ip,String date){
        Map map = new HashMap<>(2);
        map.put("ip",ip);
        map.put("date",date);
        List<String> list = keyListenerService.getKeyNames(map);
        return new Result<>(true,200,"success",list);
    }

    /**
     * 统计某个Ip,某个日期,这天的各个时间段的某个按键的按下次数
     * select count(1) from key_listener where host_address=#{hostAddress} and key_name=#{keyNames}
     * and DATE_FORMAT(create_date,'%Y-%m-%d')=DATE_FORMAT(#{createDate},'%Y-%m-%d') and hour(create_date)=9
     * @param hostAddress ip
     * @param createDate 日期
     * @param keyNames 键名的List
     * @return
     */
    @RequestMapping("/list2")
    public Result<Map> list(String hostAddress,String createDate,String keyNames){
        Map map = new HashMap(4);
        map.put("hostAddress",hostAddress);
        map.put("createDate",createDate);
        map.put("keyNames",keyNames);
        Map<String,List<Integer>> data = keyListenerService.list2(map);
        return new Result<>(true,200,"success",data);
    }

    @RequestMapping("/list3")
    public Result<Map> list3(String hostAddress,String createDate,String keyNames,Integer flag){
        Map map = new HashMap(4);
        map.put("hostAddress",hostAddress);
        map.put("createDate",createDate);
        map.put("keyNames",keyNames);
        Map<String,int[]> data = keyListenerService.countKey(map,flag);
        return new Result<>(true,200,"success",data);
    }
}
