package com.watercloud.webmagic.vo.role;

import lombok.Data;

@Data
public class RoleQueryParamVo {
    private String roleName;
    private String roleCode;
    private String beginTime;
    private String endTime;
    private Integer pageNum;
    private Integer pageSize;
}
