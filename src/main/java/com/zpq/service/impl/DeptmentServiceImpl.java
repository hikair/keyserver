package com.zpq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zpq.dao.DeptmentDao;
import com.zpq.model.Deptment;
import com.zpq.service.DeptmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 35147
 */
@Service("deptmentService")
public class DeptmentServiceImpl extends ServiceImpl<DeptmentDao, Deptment> implements DeptmentService {

    @Autowired(required = false)
    DeptmentDao deptmentDao;

    @Override
    public List<Deptment> getAllDeptWithTemplate() {
        return deptmentDao.getAllDeptWithTemplate();
    }
}
