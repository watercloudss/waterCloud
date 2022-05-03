package com.watercloud.webmagic.controller;


import com.cloudwater.common.commonVo.Result;
import com.watercloud.webmagic.service.ISysRolePermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lly
 * @since 2021-09-19
 */
@RestController
@RequestMapping("/sys-role-permission")
public class SysRolePermissionController {
    @Autowired
    private ISysRolePermissionService iSysRolePermissionService;

    @GetMapping("/getRolePermissionGroup/{roleId}")
    @RequiresPermissions("system:rolePermission:rolePermissionGroup")
    public Result<List<Integer>> getRoleGroup(@PathVariable Integer roleId){
        List<Integer> permissions = iSysRolePermissionService.getRolePermissionGroup(roleId);
        return Result.OK(permissions);
    }
}
