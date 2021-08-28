package com.zpq.entity;

import java.io.Serializable;

/**
 * (Dept)实体类
 *
 * @author makejava
 * @since 2021-07-13 20:29:02
 */
public class Dept implements Serializable {
    private static final long serialVersionUID = -43239459846580870L;
    
    private Integer id;
    
    private String deptName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

}