package com.zpq.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 35147
 */
@TableName("key_template")
public class KeyTemplate {
    @TableId(type = IdType.AUTO)
    private Integer templateId;
    private String templateName;
    private Integer deptId;
    private String deptName;
    private String keyNames;

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getKeyNames() {
        return keyNames;
    }

    public void setKeyNames(String keyNames) {
        this.keyNames = keyNames;
    }

    @Override
    public String toString() {
        return "KeyTemplate{" +
                "templateId=" + templateId +
                ", templateName='" + templateName + '\'' +
                ", deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", keyNames='" + keyNames + '\'' +
                '}';
    }
}
