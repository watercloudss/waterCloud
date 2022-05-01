package com.watercloud.webmagic.common.enums;


public enum SysPermissionEnum {
    M("M","目录"),C("C","菜单"),O("O","按钮");

    private String type;
    private String typeName;

    private SysPermissionEnum(String type,String typeName){
        this.type = type;
        this.typeName = typeName;
    }

    public String getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }
}
