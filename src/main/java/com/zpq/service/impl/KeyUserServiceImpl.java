package com.zpq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zpq.dao.KeyUserDao;
import com.zpq.model.KeyUser;
import com.zpq.service.KeyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("keyUserService")
public class KeyUserServiceImpl extends ServiceImpl<KeyUserDao, KeyUser> implements KeyUserService {

    @Autowired(required = false)
    KeyUserDao keyUserDao;

    @Override
    public int updateByIp(KeyUser keyUser) {
        String listenKey = keyUser.getListenKey();
        if(listenKey == null){
            listenKey = "";
        }
        if(!"".equals(listenKey) && listenKey.lastIndexOf(",")==listenKey.length()-1){
            listenKey=listenKey.substring(0,listenKey.length()-1);
            keyUser.setListenKey(listenKey);
        }
        return keyUserDao.updateByIp(keyUser);
    }

    @Override
    public int updateListenKeyById(KeyUser keyUser) {
        return keyUserDao.updateListenKeyById(keyUser);
    }

    @Override
    public List<String> getNameOrIpList(String query) {
        return keyUserDao.getNameOrIpList(query.replace(" ",""));
    }
}
