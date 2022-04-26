package com.watercloud.webmagic.vo.dict;

import lombok.Data;

@Data
public class DictDataQueryParamVo {
    private String name;
    private String dictType;
    private String beginTime;
    private String endTime;
    private Integer pageNum;
    private Integer pageSize;
}
