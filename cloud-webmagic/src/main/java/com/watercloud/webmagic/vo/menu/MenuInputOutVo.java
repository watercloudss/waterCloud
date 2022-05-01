package com.watercloud.webmagic.vo.menu;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.watercloud.webmagic.entity.SysPermission;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MenuInputOutVo {
    private Integer id;

    private Integer parentId;

    private String path;

    private String component;

    private String redirect;

    private String alwaysshow;

    private String name;

    private String title;

    private String icon;

    private String permission;

    private String noCache;

    private Integer sort;

    private String type;

    private String hidden;

    private String status;

    private Boolean isEl;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private String createBy;

    private List<MenuInputOutVo> children;
}
