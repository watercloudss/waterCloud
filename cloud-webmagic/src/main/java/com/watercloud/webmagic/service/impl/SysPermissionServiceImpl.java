package com.watercloud.webmagic.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.watercloud.webmagic.entity.SysPermission;
import com.watercloud.webmagic.entity.SysRole;
import com.watercloud.webmagic.mapper.SysPermissionMapper;
import com.watercloud.webmagic.mapper.SysRoleMapper;
import com.watercloud.webmagic.service.ISysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.watercloud.webmagic.vo.menu.MenuVo;
import com.watercloud.webmagic.vo.menu.MetaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lly
 * @since 2022-04-23
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public Set<String> getUserPermission(Integer userId) {
        List<String> userPermissionList = sysPermissionMapper.getUserPermission(userId);
        Set<String> userPermissionSet = new HashSet<>(userPermissionList);
        return userPermissionSet;
    }

    @Override
    public List<MenuVo> getSysPermissionByUserId(Integer userId) {
        List<MenuVo> handleMenuList = null;
        SysRole sysRole = sysRoleMapper.getUserRoleById(userId);
        if(sysRole!=null){
            List<SysPermission> sysPermissionsList = sysPermissionMapper.getSysPermissionByRoleId(sysRole.getId());
            handleMenuList = handlePermission(sysPermissionsList,0);
        }
        return handleMenuList;
    }

    private List<MenuVo> handlePermission(List<SysPermission> sysPermissionsList,Integer parentId){
        List<SysPermission> spList = new ArrayList<>();
         for(int i=0; i<sysPermissionsList.size(); i++){
             SysPermission sysPermission = sysPermissionsList.get(i);
             if(sysPermission.getParentId() == parentId){
                 recursion(sysPermissionsList, sysPermission);
                 spList.add(sysPermission);
             }
         }
        List<MenuVo> menuVoList = new ArrayList<>();
         for(SysPermission sp:spList){
             MenuVo menuVo = recursionVo(sp);
             menuVoList.add(menuVo);
         }
         return menuVoList;
    }

    private MenuVo recursionVo(SysPermission sysPermissions){
        MenuVo menuVo = getMenuVo(sysPermissions);
        List<SysPermission> childrenList = sysPermissions.getChildren();
        List<MenuVo> menuVoList = new ArrayList<>();
        if(!CollUtil.isEmpty(childrenList)){
            for(SysPermission sp:childrenList){
                MenuVo mv = null;
                if(!CollUtil.isEmpty(sp.getChildren())){
                    mv = recursionVo(sp);
                }else{
                    mv = getMenuVo(sp);
                }
                menuVoList.add(mv);
            }
        }
        menuVo.setChildren(menuVoList);
        return menuVo;
    }

    private void recursion(List<SysPermission> sysPermissionsList, SysPermission sysPermission){
        List<SysPermission> spList = getChildren(sysPermissionsList,sysPermission);
        sysPermission.setChildren(spList);
        for(SysPermission s:spList){
            if(getChildren(sysPermissionsList,s).size()>0){
                recursion(sysPermissionsList,s);
            }
        }

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

    private MenuVo getMenuVo(SysPermission sysPermission){
        MenuVo menuVo = new MenuVo();
        menuVo.setPath(sysPermission.getPath());
        menuVo.setAlwaysShow(StrUtil.isEmpty(sysPermission.getAlwaysshow())? null:"1".equals(sysPermission.getAlwaysshow())?true:false);
        menuVo.setComponent(sysPermission.getComponent());
        menuVo.setName(sysPermission.getName());
        menuVo.setRedirect(sysPermission.getRedirect());
        MetaVo metaVo = new MetaVo();
        metaVo.setIcon(sysPermission.getIcon());
        metaVo.setTitle(sysPermission.getTitle());
        metaVo.setNoCache(StrUtil.isEmpty(sysPermission.getNoCache())? null:"1".equals(sysPermission.getNoCache())?true:false);
        String role = StrUtil.removeSuffix(StrUtil.removePrefix(sysPermission.getRoles(),"["),"]");
        List<String> roles = StrUtil.split(role,",");
        metaVo.setRoles(CollUtil.isEmpty(roles)?null:roles);
        menuVo.setMeta(metaVo);
        return menuVo;
    }

}
