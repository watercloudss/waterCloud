package com.watercloud.webmagic.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloudwater.common.commonVo.Result;
import com.watercloud.webmagic.entity.SysUser;
import com.watercloud.webmagic.service.ISysPermissionService;
import com.watercloud.webmagic.vo.menu.MenuInputOutVo;
import com.watercloud.webmagic.vo.menu.MenuQueryParamVo;
import com.watercloud.webmagic.vo.menu.MenuVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lly
 * @since 2022-04-23
 */
@RestController
@RequestMapping("/sys-permission")
public class SysPermissionController {
    @Autowired
    private ISysPermissionService iSysPermissionService;

    @PostMapping("/getRouters")
    public Result<List<MenuVo>> getRouters(){
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        List<MenuVo> menuVoList = iSysPermissionService.getSysPermissionByUserId(sysUser.getId());
        Result<List<MenuVo>> result = Result.OK(menuVoList);
        return result;
    }

    @GetMapping("/list")
    @RequiresPermissions("system:menu:list")
    public Result<List<MenuInputOutVo>> list(MenuQueryParamVo menuQueryParamVo){
        List<MenuInputOutVo> menuInputOutVoList = iSysPermissionService.getList(menuQueryParamVo);
        Result<List<MenuInputOutVo>> result = Result.OK(menuInputOutVoList);
        return result;
    }
}
