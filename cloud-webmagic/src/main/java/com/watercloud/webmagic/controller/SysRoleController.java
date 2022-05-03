package com.watercloud.webmagic.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloudwater.common.commonVo.Result;
import com.watercloud.webmagic.entity.SysDictData;
import com.watercloud.webmagic.entity.SysRole;
import com.watercloud.webmagic.entity.SysRolePermission;
import com.watercloud.webmagic.service.ISysRolePermissionService;
import com.watercloud.webmagic.service.ISysRoleService;
import com.watercloud.webmagic.vo.dict.DictDataInputOutVo;
import com.watercloud.webmagic.vo.dict.DictDataQueryParamVo;
import com.watercloud.webmagic.vo.role.RoleInputOutVo;
import com.watercloud.webmagic.vo.role.RoleQueryParamVo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lly
 * @since 2021-09-19
 */
@RestController
@RequestMapping("/sys-role")
public class SysRoleController {
    @Autowired
    private ISysRoleService iSysRoleService;
    @Autowired
    private ISysRolePermissionService iSysRolePermissionService;

    @GetMapping("/list")
    @RequiresPermissions("system:role:list")
    public Result<IPage> list(RoleQueryParamVo roleQueryParamVo) {
        IPage iPage = iSysRoleService.list(roleQueryParamVo);
        Result<IPage> result = Result.OK(iPage);
        return result;
    }

    @GetMapping("/getById/{id}")
    @RequiresPermissions("system:role:detail")
    public Result<RoleInputOutVo> getByDictCode(@PathVariable Integer id){
        SysRole sysRole = iSysRoleService.getById(id);
        RoleInputOutVo roleInputOutVo = Convert.convert(RoleInputOutVo.class,sysRole);
        Result result = Result.OK(roleInputOutVo);
        return result;
    }

    @PutMapping("/updateOrSave")
    @RequiresPermissions(value={"system:role:add","system:role:update"},logical = Logical.OR)
    @Transactional
    public Result updateDictByIdOrSave(@RequestBody RoleInputOutVo roleInputOutVo){
        SysRole sysRole = Convert.convert(SysRole.class,roleInputOutVo);
        boolean roleFlag = false;
        boolean rolePermissionFlag = true;
        boolean rolePermissionDelFlag = true;
        if(roleInputOutVo.getId()==null){
            QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("role_code",roleInputOutVo.getRoleCode());
            SysRole sr = iSysRoleService.getOne(queryWrapper);
            if(sr!=null){
                return Result.error("操作失败:角色已存在");
            }else{
                if(iSysRoleService.save(sysRole)){
                    roleFlag = true;
                }else{
                    return  Result.error("操作失败");
                }
            }
        }else{
            if(iSysRoleService.updateById(sysRole)){
                roleFlag = true;
            }else{
                return Result.error("操作失败");
            }
        }
        if(CollUtil.isNotEmpty(roleInputOutVo.getAllkeys())){
            List<Integer> permissions = roleInputOutVo.getAllkeys();
            for(int i=0;i<permissions.size();i++){
                if(permissions.get(i)==0){
                    permissions.remove(i);
                    break;
                }
            }
            List<SysRolePermission> sysRolePermissionList = new ArrayList<>();
            for(Integer permissionId:permissions){
                SysRolePermission sysRolePermission = new SysRolePermission();
                sysRolePermission.setRoleId(sysRole.getId());
                sysRolePermission.setPermissionId(permissionId);
                sysRolePermissionList.add(sysRolePermission);
            }
            if(roleInputOutVo.getId()!=null){
                QueryWrapper<SysRolePermission> srpqw = new QueryWrapper<>();
                srpqw.eq("role_id",roleInputOutVo.getId());
                rolePermissionDelFlag = iSysRolePermissionService.remove(srpqw);
            }
            rolePermissionFlag = iSysRolePermissionService.saveBatch(sysRolePermissionList);
        }
        if(roleFlag&&rolePermissionDelFlag&&rolePermissionFlag){
            return Result.ok("操作成功");
        }else{
            return Result.error("操作失败");
        }
    }

    @DeleteMapping("/del/{id}")
    @RequiresPermissions("system:role:delete")
    @Transactional
    public Result  del(@PathVariable Integer id){
        Result result = null;
        boolean rolePermissionDelFlag = true;
        QueryWrapper<SysRolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",id);
        int count = iSysRolePermissionService.count(queryWrapper);
        if(count>0){
            rolePermissionDelFlag = iSysRolePermissionService.remove(queryWrapper);
        }
        if(iSysRoleService.removeById(id)&&rolePermissionDelFlag){
            result = Result.OK();
        }else{
            result = Result.error("删除失败");
        }
        return result;
    }

    @GetMapping("/getRoleGroup")
    @RequiresPermissions(value={"system:role:group","system:users:add","system:users:update"},logical = Logical.OR)
    public Result<List<Map<String,String>>> getRoleGroup(){
        Result<List<Map<String,String>>> result = Result.OK(iSysRoleService.getRoleGroup());
        return result;
    }

}
