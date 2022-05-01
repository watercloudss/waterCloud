package com.watercloud.webmagic.common.enums;


public enum SysPermissionStatusEnum {
    ON("1","正常"),OFF("0","停用");

    private String status;
    private String statusName;

    private SysPermissionStatusEnum(String status, String statusName){
        this.status = status;
        this.statusName = statusName;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusName() {
        return statusName;
    }

}
