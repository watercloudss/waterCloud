package com.watercloud.webmagic.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.watercloud.webmagic.entity.SysPermission;
import com.watercloud.webmagic.entity.SysRolePermission;
import com.watercloud.webmagic.mapper.SysPermissionMapper;
import com.watercloud.webmagic.mapper.SysRolePermissionMapper;
import com.watercloud.webmagic.service.ISysRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lly
 * @since 2021-09-19
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements ISysRolePermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<Integer> getRolePermissionGroup(Integer roleId) {
        List<SysPermission> sysPermissions = sysPermissionMapper.getSysPermissionByRoleId(roleId,1);
        List<Integer> permissions = getPermissions(sysPermissions);
        return permissions;
    }

    private List<Integer> getPermissions(List<SysPermission> sysPermissionsList){
        List<Integer> permissions = new ArrayList<>();
        for(SysPermission sysPermission:sysPermissionsList){
            List<SysPermission> children = getChildren(sysPermissionsList,sysPermission);
            if(CollUtil.isNotEmpty(children)){
                getPermissions(children);
            }else{
                 permissions.add(sysPermission.getId());
            }
        }
        return permissions;
    }

    private List<SysPermission> getChildren(List<SysPermission> sysPermissionsList, SysPermission sysPermission){
        List<SysPermission> spList = new ArrayList<>();
        int id = sysPermission.getId();
        for(SysPermission sp:sysPermissionsList){
            if(sp.getParentId() == id){
                spList.add(sp);
            }
        }
        return spList;
    }

}
