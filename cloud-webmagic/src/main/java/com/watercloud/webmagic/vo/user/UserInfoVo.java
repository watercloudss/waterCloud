package com.watercloud.webmagic.vo.user;

import com.watercloud.webmagic.entity.SysUser;
import lombok.Data;

import java.util.Set;

@Data
public class UserInfoVo {
    Set<String> roles;
    SysUser user;
}
