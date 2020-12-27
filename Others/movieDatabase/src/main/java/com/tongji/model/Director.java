package com.tongji.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class Director implements Serializable {
    private Integer directorId;

    private String name;

    @ApiModelProperty(value = "导演拍摄的电影用字符串标识,便于查询")
    private String filming;

    private static final long serialVersionUID = 1L;

    public Integer getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Integer directorId) {
        this.directorId = directorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilming() {
        return filming;
    }

    public void setFilming(String filming) {
        this.filming = filming;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", directorId=").append(directorId);
        sb.append(", name=").append(name);
        sb.append(", filming=").append(filming);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}