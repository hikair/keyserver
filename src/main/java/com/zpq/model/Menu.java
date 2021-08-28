package com.zpq.model;

import java.util.List;

/**
 * 用于前台菜单显示
 * @author 35147
 */
public class Menu {
    private Integer id;
    private String authName;
    private Integer parentId;
    private String path;
    private List<Menu> children;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

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

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", authName='" + authName + '\'' +
                ", parentId=" + parentId +
                ", path='" + path + '\'' +
                ", children=" + children +
                '}';
    }
}
