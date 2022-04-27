package com.watercloud.webmagic.vo.user;

import lombok.Data;

@Data
public class UserQueryParamVo {
    private String name;
    private String username;
    private String beginTime;
    private String endTime;
    private Integer pageNum;
    private Integer pageSize;
}
