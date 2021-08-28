package com.zpq.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zpq.model.KeyUser;

import java.util.List;

public interface KeyUserDao extends BaseMapper<KeyUser> {
    int updateByIp(KeyUser keyUser);

    List<String> getNameOrIpList(String query);

    int updateListenKeyById(KeyUser keyUser);
}
