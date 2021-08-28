package com.zpq.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zpq.model.KeyUser;
import com.zpq.model.Result;
import com.zpq.service.KeyUserService;
import com.zpq.utils.KeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 操作key_user
 * @author 35147
 */
@RestController
@RequestMapping("/user")
public class KeyUserController {
    @Autowired
    KeyUserService keyUserService;

    /**
     * 根据关键词查找名字或ip
     * @param query 关键词
     * @return 可能找到多个，所以返回list
     */
    @RequestMapping("/getNameOrIpList")
    public Result<List<KeyUser>> getNameOrIpList(String query){
        QueryWrapper<KeyUser> sql = new QueryWrapper<>();
        sql.select("name");
        sql.like("name",query);
        sql.or();
        sql.like("host_address", query);
        List<KeyUser> list = keyUserService.list(sql);
        return new Result<>(true,200,"success",list);
    }

    /**
     * 模糊查询ip
     * @param query ip
     * @return
     */
    @RequestMapping("/getByIp")
    public Result<List<KeyUser>> getByIp(String query){
        QueryWrapper<KeyUser> sql = new QueryWrapper<>();
        sql.select("host_address");
        sql.like("host_address", query);
        List<KeyUser> list = keyUserService.list(sql);
        return new Result<>(true,200,"success",list);
    }

    /**
     * 分页查询
     * @param pageNum 当前页
     * @param size 一页大小
     * @param query 姓名
     * @return
     */
    @RequestMapping("/list")
    public Result<Map> list(int pageNum, int size, String query){
        QueryWrapper<KeyUser> sql = new QueryWrapper<>();
        Page<KeyUser> page = new Page<>(pageNum,size);
        sql.like("name",query);
        keyUserService.page(page,sql);
        List<KeyUser> keyUsers = page.getRecords();
        Map map = new HashMap(4);
        map.put("data",keyUsers);
        map.put("pageCount",page.getPages());
        map.put("rowCount",page.getTotal());
        return new Result<>(true,200,"success",map);
    }

    @RequestMapping("/edit")
    public Result<Boolean> edit(KeyUser keyUser, Integer modify){
        if(modify==0){
            try {
                keyUserService.save(keyUser);
                return new Result<>(true,200,"保存成功",true);
            } catch (DuplicateKeyException e) {
                e.printStackTrace();
                return new Result<>(true,200,"新增失败,该ip已经存在",false);
            }
        }
        keyUser.setHostAddress(keyUser.getHostAddress().replaceAll(" ",""));
        int i = keyUserService.updateByIp(keyUser);
        if(i<1){
            return new Result<>(true,201,"修改失败",false);
        }
        return new Result<>(true,200,"修改成功",true);
    }

    @RequestMapping("/getById")
    public Result<KeyUser> getById(Integer id){
        QueryWrapper<KeyUser> sql = new QueryWrapper<>();
        sql.eq("id",id);
        List<KeyUser> keyUsers = keyUserService.list(sql);
        return new Result<>(true,200,"success",keyUsers.get(0));
    }

    @RequestMapping("/delete")
    public Result<Boolean> delete(Integer id){
        keyUserService.removeById(id);
        return new Result<>(true,200,"success",true);
    }

    @RequestMapping("/getAllIps")
    public Result<List<KeyUser>> getAllIps(){
        QueryWrapper<KeyUser> sql = new QueryWrapper<>();
        sql.select("distinct host_address,name");
        List<KeyUser> list = keyUserService.list(sql);
        System.out.println(list);
        return new Result<>(true,200,"success",list);
    }

    @RequestMapping("/getKeyNames")
    public Result<ArrayList> getKeyNames(String hostAddress){
        QueryWrapper<KeyUser> sql = new QueryWrapper<>();
        sql.eq("host_address",hostAddress);
        List<KeyUser> keyUsers = keyUserService.list(sql);
        KeyUser keyUser = keyUsers.get(0);
        String listenKeys = keyUser.getListenKey();
        if(listenKeys==null){
            return new Result<>(true,200,"无监听按键",null);
        }
        String[] listenKey = listenKeys.split(",");
        ArrayList<Object> list = new ArrayList<>();
        for (String s : listenKey) {
            list.add(s);
        }
        return new Result<>(true,200,"success",list);
    }

    /**
     * 可以进行批量新增
     * UPDATE key_user SET listen_key='xxx' WHERE id='xxx';
     * 要判断是否已经存在
     * 不支持两个或三个组合键+其他特殊键 不支持Insert
     * 但是一个、两个或三个组合键+数字或字母是可以的。这里的数字按的不是小键盘的数字
     * 一个组合键+其他特殊键也是可以的。如：shift+>
     * @param keyUser 包含id listenKey
     * @return result.data 返回删除后的键名集合
     */
    @RequestMapping("/add")
    public Result<List<String>> add(KeyUser keyUser){
        // 判断是否支持
        String addKeyNames = keyUser.getListenKey();

        if(addKeyNames == null || "".equals(addKeyNames)){
            return new Result<>(true,202,"添加按键为空",null);
        }

        String[] addKeyNameArray = addKeyNames.split(",");
        if(addKeyNameArray.length<1){
            return new Result<>(true,202,"添加按键为空",null);
        }

        // 查询旧监听的键名
        QueryWrapper<KeyUser> sql = new QueryWrapper<>();
        sql.eq("id",keyUser.getId());
        List<KeyUser> list = keyUserService.list(sql);
        if(list.size() < 1){
            return new Result<>(true,202,"无该用户,请联系IT人员",null);
        }
        String listenKey = list.get(0).getListenKey();

        // 最终向数据库添加的监听按键的StringBuffer
        StringBuffer sb = new StringBuffer(listenKey);

        // 遍历每个要新增的按键
        for (String addKey : addKeyNameArray) {
            String[] split = addKey.split("\\+");
            // 如果是组合键
            if(split.length>2){
                int keyCode = KeyUtils.getKeyCode(split[split.length-1]);
                boolean isNumOrWord = (keyCode>47 && keyCode<58)||(keyCode>64 && keyCode<91);
                if(!isNumOrWord){
                    String msg = "暂不支持两个或三个组合键+其他特殊键 不支持Insert\n" +
                            "但是两个或三个组合键+数字或字母是可以的。这里的数字按的不是小键盘的数字\n" +
                            "一个组合键+其他特殊键也是可以的。如：shift+>";
                    return new Result<>(true,202,msg,null);
                }
            }

            if(!"".equals(listenKey)){
                String[] keys = listenKey.split(",");
                for (String key : keys) {
                    if(key.equals(addKey)){
                        return new Result<>(true,202,key+"键已在监听",null);
                    }
                }
            }
            sb.append(",").append(addKey);
        }
        keyUser.setListenKey(sb.toString());
        int i = keyUserService.updateListenKeyById(keyUser);
        if(i < 1){
            return new Result<>(true,201,"新增失败,请联系IT人员",null);
        }
        return new Result<>(true,200,"新增成功", Arrays.asList(sb.toString().split(",")));
    }

    /**
     * 删除某个键 先根据id去数据库读一遍，拿到所有的键名，
     * 遍历所有键名 拼接除了删除的键，再去数据库存一遍
     * @param keyUser id和要删除的键名
     * @return 返回删除后的键名集合
     */
    @RequestMapping("/deleteKeyById")
    public Result<List<String>> deleteKeyById(KeyUser keyUser){
        if(keyUser.getId() == null){
            return new Result<>(true,202,"删除失败",null);
        }

        if(keyUser.getListenKey() == null || "".equals(keyUser.getListenKey())){
            return new Result<>(true,202,"按键为空",null);
        }

        QueryWrapper<KeyUser> sql = new QueryWrapper<>();
        sql.eq("id",keyUser.getId());
        // 查询原先的监听按键
        List<KeyUser> list = keyUserService.list(sql);
        if(list.size() < 1){
            return new Result<>(true,202,"无该用户,请联系IT人员",null);
        }
        String listenKey = list.get(0).getListenKey();
        if(listenKey==null || "".equals(listenKey)){
            return new Result<>(true,202,"删除失败,请联系IT人员",null);
        }

        String[] keys = listenKey.split(",");
        // 最终数据库里的按键是这个
        StringBuffer sb = new StringBuffer();
        boolean flag = false;
        for(int i=0;i<keys.length;i++){
            if(!keys[i].equals(keyUser.getListenKey()) && !"".equals(keys[i])){
                sb.append(keys[i]);
                if(i<keys.length-1){
                    sb.append(",");
                }
            }else {
                //存在要删除的键
                flag = true;
            }
        }
        if(flag){
            String str = sb.toString();
            str = str.endsWith(",")?str.substring(0,str.length()-1):str;
            keyUser.setListenKey(str);
            keyUserService.updateListenKeyById(keyUser);
            return new Result<>(true,200,"删除成功", Arrays.asList(str.split(",")));
        }else {
            return new Result<>(true,201,"删除失败",null);
        }

    }
}
