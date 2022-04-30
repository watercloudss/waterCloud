package com.watercloud.webmagic.vo.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuVo {
    private String path;
    private String component;
    private String redirect;
    private Boolean alwaysShow;
    private Boolean hidden;
    private String name;
    private MetaVo meta;
    private List<MenuVo> children;
}
