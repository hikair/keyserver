package com.zpq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zpq.model.Deptment;

import java.util.List;

/**
 * @author 35147
 */
public interface DeptmentService extends IService<Deptment> {
    /**
     * 获取所有部门 带上所有模板
     * @return
     */
    List<Deptment> getAllDeptWithTemplate();
}
