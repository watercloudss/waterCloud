package com.watercloud.webmagic.vo.menu;

import lombok.Data;

@Data
public class MenuQueryParamVo {
    private String title;
    private String beginTime;
    private String endTime;
    private Integer pageNum;
    private Integer pageSize;
}
