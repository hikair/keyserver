package com.zpq.controller;

import com.zpq.model.Deptment;
import com.zpq.model.Result;
import com.zpq.service.DeptmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 35147
 */
@RestController
@RequestMapping("/dept")
public class DeptmentController {

    @Autowired
    DeptmentService deptmentService;

    @RequestMapping("/list")
    public Result<List<Deptment>> list(){
        List<Deptment> list = deptmentService.list();
        return new Result<>(true,200,"success",list);
    }

    @RequestMapping("/getAllDeptWithTemplate")
    public Result<List<Deptment>> getAllDeptWithTemplate(){
        List<Deptment> list = deptmentService.getAllDeptWithTemplate();
        return new Result<>(true,200,"success",list);
    }
}
