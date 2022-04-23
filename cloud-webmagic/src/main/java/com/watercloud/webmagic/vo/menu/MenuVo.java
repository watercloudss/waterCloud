package com.watercloud.webmagic.vo.menu;

import lombok.Data;

@Data
public class MenuVo {
    private String path;
    private String component;
    private String redirect;
    private boolean alwaysShow;
    private String name;
    private MetaVo meta;
}
