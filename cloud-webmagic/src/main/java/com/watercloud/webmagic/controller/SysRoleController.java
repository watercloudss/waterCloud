package com.watercloud.webmagic.controller;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloudwater.common.commonVo.Result;
import com.watercloud.webmagic.entity.SysDictData;
import com.watercloud.webmagic.entity.SysRole;
import com.watercloud.webmagic.service.ISysRoleService;
import com.watercloud.webmagic.vo.dict.DictDataInputOutVo;
import com.watercloud.webmagic.vo.dict.DictDataQueryParamVo;
import com.watercloud.webmagic.vo.role.RoleInputOutVo;
import com.watercloud.webmagic.vo.role.RoleQueryParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/list")
    public Result<IPage> list(RoleQueryParamVo roleQueryParamVo) {
        IPage iPage = iSysRoleService.list(roleQueryParamVo);
        Result<IPage> result = Result.OK(iPage);
        return result;
    }

    @GetMapping("/getById/{id}")
    public Result<RoleInputOutVo> getByDictCode(@PathVariable Integer id){
        SysRole sysRole = iSysRoleService.getById(id);
        RoleInputOutVo roleInputOutVo = Convert.convert(RoleInputOutVo.class,sysRole);
        Result result = Result.OK(roleInputOutVo);
        return result;
    }

    @PutMapping("/updateOrSave")
    public Result updateDictByIdOrSave(@RequestBody RoleInputOutVo roleInputOutVo){
        SysRole sysRole = Convert.convert(SysRole.class,roleInputOutVo);
        Result result = null;
        if(roleInputOutVo.getId()==null){
            QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("role_code",roleInputOutVo.getRoleCode());
            SysRole sr = iSysRoleService.getOne(queryWrapper);
            if(sr!=null){
                result = Result.error("操作失败:角色已存在");
            }else{
                if(iSysRoleService.save(sysRole)){
                    result = Result.OK();
                }else{
                    result = Result.error("操作失败");
                }
            }
        }else{
            if(iSysRoleService.updateById(sysRole)){
                result = Result.OK();
            }else{
                result = Result.error("操作失败");
            }
        }
        return result;
    }

    @DeleteMapping("/del/{id}")
    public Result  del(@PathVariable Integer id){
        Result result = null;
        if(iSysRoleService.removeById(id)){
            result = Result.OK();
        }else{
            result = Result.error("删除失败");
        }
        return result;
    }

    @GetMapping("/getRoleGroup")
    public Result<List<Map<String,String>>> getRoleGroup(){
        Result<List<Map<String,String>>> result = Result.OK(iSysRoleService.getRoleGroup());
        return result;
    }

}
