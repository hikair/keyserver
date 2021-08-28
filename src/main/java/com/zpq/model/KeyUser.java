package com.zpq.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author 35147
 */
public class KeyUser {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String hostAddress;
    private Integer deptId;
    private String deptName;
    private String name;
    private String listenKey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getListenKey() {
        return listenKey;
    }

    public void setListenKey(String listenKey) {
        this.listenKey = listenKey;
    }

    @Override
    public String toString() {
        return "KeyUser{" +
                "id=" + id +
                ", hostAddress='" + hostAddress + '\'' +
                ", deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", name='" + name + '\'' +
                ", listenKey='" + listenKey + '\'' +
                '}';
    }
}
