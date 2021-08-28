package com.zpq.entity;

import java.io.Serializable;

/**
 * (Menu)实体类
 *
 * @author makejava
 * @since 2021-07-13 22:10:32
 */
public class Menu implements Serializable {
    private static final long serialVersionUID = 187421072150996815L;
    
    private Integer id;
    /**
    * 菜单名
    */
    private String authName;
    /**
    * 父级id
    */
    private Integer parentId;
    /**
    * 跳转路径
    */
    private String path;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}