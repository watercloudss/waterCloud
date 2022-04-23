package com.watercloud.webmagic.vo.menu;

import lombok.Data;

@Data
public class ChildrenVo {
    private String path;
    private String component;
    private String name;
    private ChildrenMetaVo meta;

}
