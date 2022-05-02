package com.watercloud.webmagic.vo.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuGroupVo {
    private Integer id;
    private String label;
    private List<MenuGroupVo> children;
}
