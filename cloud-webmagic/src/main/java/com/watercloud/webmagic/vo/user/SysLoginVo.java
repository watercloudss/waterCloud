package com.watercloud.webmagic.vo.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录表单
 *
 */
@Data
public class SysLoginVo {
    @NotBlank(message = "密码不能为空！")
    private String username;
    @NotBlank(message = "用户名不能为空！")
    private String password;
}