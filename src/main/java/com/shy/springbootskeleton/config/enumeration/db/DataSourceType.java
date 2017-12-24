package com.shy.springbootskeleton.config.enumeration.db;

import lombok.Getter;

@Getter
public enum DataSourceType {
    //这是定义了两个枚举变量:名称分别为 read,write
    read("read", "从库"), write("write", "主库");
    private String type;
    private String name;

    DataSourceType(String type,String name){
        this.type = type;
        this.name = name;
    }
}
