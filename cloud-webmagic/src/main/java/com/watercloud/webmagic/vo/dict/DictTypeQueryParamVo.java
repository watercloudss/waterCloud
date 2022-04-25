package com.watercloud.webmagic.vo.dict;

import lombok.Data;

import java.util.List;

@Data
public class DictTypeQueryParamVo {
    private String dictName;
    private String dictType;
    private String beginTime;
    private String endTime;
    private Integer pageNum;
    private Integer pageSize;
}
