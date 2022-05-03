package com.watercloud.webmagic.service;

import com.watercloud.webmagic.entity.SysRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lly
 * @since 2021-09-19
 */
public interface ISysRolePermissionService extends IService<SysRolePermission> {
    public List<Integer> getRolePermissionGroup(Integer roleId);
}
