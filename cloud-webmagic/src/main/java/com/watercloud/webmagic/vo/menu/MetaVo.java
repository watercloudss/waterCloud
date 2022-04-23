package com.watercloud.webmagic.vo.menu;

import lombok.Data;

import java.util.List;

@Data
public class MetaVo {
    private String title;
    private String icon;
    private List<String> roles;
}
