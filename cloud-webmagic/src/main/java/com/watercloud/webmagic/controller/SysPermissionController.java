package com.watercloud.webmagic.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloudwater.common.commonVo.Result;
import com.watercloud.webmagic.common.enums.SysPermissionTypeEnum;
import com.watercloud.webmagic.entity.SysPermission;
import com.watercloud.webmagic.entity.SysUser;
import com.watercloud.webmagic.service.ISysPermissionService;
import com.watercloud.webmagic.vo.menu.MenuGroupVo;
import com.watercloud.webmagic.vo.menu.MenuInputOutVo;
import com.watercloud.webmagic.vo.menu.MenuQueryParamVo;
import com.watercloud.webmagic.vo.menu.MenuVo;
import com.watercloud.webmagic.vo.user.UserInputOutVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/getGroup")
    @RequiresPermissions("system:menu:group")
    public Result<List<MenuGroupVo>> getGroup(){
        List<MenuGroupVo> menuGroups = iSysPermissionService.getGroup();
        Result<List<MenuGroupVo>> result = Result.OK(menuGroups);
        return result;
    }

    @PutMapping("/updateOrSave")
    @RequiresPermissions(value={"system:menu:add","system:menu:update"},logical= Logical.OR)
    @Transactional
    public Result updateOrSave(@RequestBody MenuInputOutVo menuInputOutVo){
        SysPermission sysPermission = Convert.convert(SysPermission.class,menuInputOutVo);
        if(menuInputOutVo.getParentId()==0){
            sysPermission.setComponent("Layout");
            sysPermission.setPath("/"+sysPermission.getName());
        }else{
            sysPermission.setPath(sysPermission.getName());
        }
        if(SysPermissionTypeEnum.M.getType().equals(menuInputOutVo.getType())&&menuInputOutVo.getParentId()!=0){
            sysPermission.setComponent("ParentView");
        }
        iSysPermissionService.saveOrUpdate(sysPermission);
        Result result = Result.ok();
        return result;
    }

    @GetMapping("/getById/{id}")
    @RequiresPermissions("system:menu:detail")
    public Result<MenuInputOutVo> getById(@PathVariable Integer id){
        SysPermission sysPermission = iSysPermissionService.getById(id);
        MenuInputOutVo menuInputOutVo = Convert.convert(MenuInputOutVo.class,sysPermission);
        Result<MenuInputOutVo> result = Result.OK(menuInputOutVo);
        return result;
    }

    @DeleteMapping("/del/{id}")
    @RequiresPermissions("system:menu:delete")
    @Transactional
    public Result del(@PathVariable Integer id){
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        List<SysPermission> sysPermissionList = iSysPermissionService.list(queryWrapper);
        boolean flag = true;
        Result result = null;
        if(CollUtil.isNotEmpty(sysPermissionList)){
            List<Integer> ids = sysPermissionList.stream().map(SysPermission::getId).collect(Collectors.toList());
            iSysPermissionService.removeByIds(ids);
        }
        if(iSysPermissionService.removeById(id)&&flag){
            result = Result.ok();
        }else{
            result = Result.error("删除失败！");
        }
        return result;
    }
}
