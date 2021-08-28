package com.zpq.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * @author 35147
 */
@TableName("dept")
public class Deptment {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String deptName;

    @TableField(exist = false)
    private List<KeyTemplate> keyTemplates;

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

    public List<KeyTemplate> getKeyTemplates() {
        return keyTemplates;
    }

    public void setKeyTemplates(List<KeyTemplate> keyTemplates) {
        this.keyTemplates = keyTemplates;
    }

    @Override
    public String toString() {
        return "Deptment{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                ", keyTemplates=" + keyTemplates +
                '}';
    }
}
