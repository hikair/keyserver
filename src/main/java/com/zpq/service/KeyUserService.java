package com.zpq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zpq.model.KeyUser;

import java.util.List;

public interface KeyUserService extends IService<KeyUser> {
    /**
     * 根据ip修改名字、部门id、部门名字、监听按键
     * @param keyUser
     * @return
     */
    int updateByIp(KeyUser keyUser);

    int updateListenKeyById(KeyUser keyUser);

    List<String> getNameOrIpList(String query);
}
