package com.watercloud.webmagic.vo.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetaVo {
    private String title;
    private String icon;
    private Boolean noCache;
    private List<String> roles;
}
