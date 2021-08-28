package com.zpq.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zpq.model.Deptment;

import java.util.List;

/**
 * @author 35147
 */
public interface DeptmentDao extends BaseMapper<Deptment> {

    List<Deptment> getAllDeptWithTemplate();

}
