package com.watercloud.webmagic.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.watercloud.webmagic.common.enums.SysPermissionStatusEnum;
import com.watercloud.webmagic.common.enums.SysPermissionTypeEnum;
import com.watercloud.webmagic.entity.SysPermission;
import com.watercloud.webmagic.entity.SysRole;
import com.watercloud.webmagic.entity.SysRolePermission;
import com.watercloud.webmagic.mapper.SysPermissionMapper;
import com.watercloud.webmagic.mapper.SysRoleMapper;
import com.watercloud.webmagic.service.ISysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.watercloud.webmagic.service.ISysRolePermissionService;
import com.watercloud.webmagic.service.ISysRoleService;
import com.watercloud.webmagic.vo.menu.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    private final static List<String> mcTypes = Arrays.asList("M","C");
    private final static List<String> allTypes = Arrays.asList("M","C","O");
    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public Set<String> getUserPermission(Integer userId) {
        List<String> userPermissionList = sysPermissionMapper.getUserPermission(userId);
        Set<String> userPermissionSet = new HashSet<>(userPermissionList);
        userPermissionSet.remove(null);
        return userPermissionSet;
    }

    @Override
    public List<MenuVo> getSysPermissionByUserId(Integer userId) {
        SysRole sysRole = sysRoleMapper.getUserRoleById(userId);
        List<MenuVo> menuVoList = new ArrayList<>();
        if(sysRole!=null){
            List<SysPermission> sysPermissionsList = sysPermissionMapper.getSysPermissionByRoleId(sysRole.getId(),0);
            List<SysPermission> handleMenuList = handlePermission(sysPermissionsList,0);
            for(SysPermission sp:handleMenuList){
                MenuVo menuVo = recursionVo(sp);
                menuVoList.add(menuVo);
            }
        }
        return menuVoList;
    }

    @Override
    public List<MenuGroupVo> getGroup(Integer flag) {
        MenuGroupVo menuGroupVo = new MenuGroupVo();
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        if(flag==1){
            queryWrapper.in("type",mcTypes);
        }else{
            queryWrapper.in("type",allTypes);
        }
        queryWrapper.orderByAsc("sort");
        List<SysPermission>  sysPermissionList = this.list(queryWrapper);
        List<MenuGroupVo> mgList = handlePermissionGroup(sysPermissionList,0);
        menuGroupVo.setId(0);
        menuGroupVo.setLabel("根目录");
        menuGroupVo.setChildren(mgList);
        List<MenuGroupVo> menuGroupVoList = new ArrayList<>();
        menuGroupVoList.add(menuGroupVo);
        return menuGroupVoList;
    }

    @Override
    public List<MenuInputOutVo> getList(MenuQueryParamVo menuQueryParamVo) {
        boolean parentIdFlag = false;
        List<SysPermission> handleMenuList = new ArrayList<>();
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        if(StrUtil.isNotEmpty(menuQueryParamVo.getTitle())){
            parentIdFlag = true;
            queryWrapper.like("title",menuQueryParamVo.getTitle());
        }
        if(StrUtil.isNotEmpty(menuQueryParamVo.getBeginTime())&&StrUtil.isNotEmpty(menuQueryParamVo.getEndTime())) {
            parentIdFlag = true;
            queryWrapper.between("create_time",menuQueryParamVo.getBeginTime()
                    , DateUtil.format(DateUtil.offsetDay(DateUtil.parse(menuQueryParamVo.getEndTime()), 1), "yyyy-MM-dd")
            );
        }
        queryWrapper.orderByAsc("sort");
        List<SysPermission> permissions = this.list(queryWrapper);
        permissions.stream().forEach(e->{
            if(SysPermissionTypeEnum.M.getType().equals(e.getType())){
                e.setType(SysPermissionTypeEnum.M.getTypeName());
            }else if(SysPermissionTypeEnum.C.getType().equals(e.getType())){
                e.setType(SysPermissionTypeEnum.C.getTypeName());
            }else{
                e.setType(SysPermissionTypeEnum.O.getTypeName());
            }
            if(StrUtil.isNotEmpty(e.getIcon())&&e.getIcon().contains("el-icon")){
                e.setIsEl("1");
            }else if(StrUtil.isNotEmpty(e.getIcon())){
                e.setIsEl("2");
            }else{
                e.setIsEl("3");
            }
            if(SysPermissionStatusEnum.ON.getStatus().equals(e.getStatus())){
                e.setStatus(SysPermissionStatusEnum.ON.getStatusName());
            }else{
                e.setStatus(SysPermissionStatusEnum.OFF.getStatusName());
            }
        });
        if(parentIdFlag){
            for(SysPermission s:permissions){
                List<SysPermission> sList = handlePermission(permissions,s.getParentId());
                if(this.isNotParent(permissions,s)){
                    handleMenuList.addAll(sList);
                }
            }
        }else{
            handleMenuList = handlePermission(permissions,0);
        }
        List<MenuInputOutVo> menuInputOutVoList = Convert.toList(MenuInputOutVo.class,handleMenuList);
        return menuInputOutVoList;
    }

    private List<SysPermission> handlePermission(List<SysPermission> sysPermissionsList, Integer parentId){
        List<SysPermission> spList = new ArrayList<>();
         for(int i=0; i<sysPermissionsList.size(); i++){
             SysPermission sysPermission = sysPermissionsList.get(i);
             if(sysPermission.getParentId() == parentId){
                 recursion(sysPermissionsList, sysPermission);
                 spList.add(sysPermission);
             }
         }
         return spList;
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

    private List<MenuGroupVo> handlePermissionGroup(List<SysPermission> sysPermissionsList, Integer parentId){
        List<MenuGroupVo> mgList = new ArrayList<>();
        for(int i=0; i<sysPermissionsList.size(); i++){
            SysPermission sysPermission = sysPermissionsList.get(i);
            if(sysPermission.getParentId() == parentId){
                MenuGroupVo menuGroupVo = recursionGroup(sysPermissionsList, sysPermission);
                mgList.add(menuGroupVo);
            }
        }
        return mgList;
    }

    private MenuGroupVo recursionGroup(List<SysPermission> sysPermissionsList, SysPermission sysPermission){
        MenuGroupVo menuGroupVo = new MenuGroupVo();
        menuGroupVo.setId(sysPermission.getId());
        menuGroupVo.setLabel(sysPermission.getTitle());
        List<SysPermission> spList = getChildren(sysPermissionsList,sysPermission);
        List<MenuGroupVo> menuGroupVoList = new ArrayList<>();
        menuGroupVo.setChildren(menuGroupVoList);
        for(SysPermission s:spList){
            if(getChildren(sysPermissionsList,s).size()>0){
                MenuGroupVo mgv = recursionGroup(sysPermissionsList,s);
                menuGroupVoList.add(mgv);
            }else{
                MenuGroupVo mg = new MenuGroupVo();
                mg.setId(s.getId());
                mg.setLabel(s.getTitle());
                menuGroupVoList.add(mg);
            }
        }
        return menuGroupVo;
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

    private boolean isNotParent(List<SysPermission> sysPermissionsList, SysPermission sysPermission){
        boolean flag = true;
        for(SysPermission sp:sysPermissionsList){
            if(sp.getId() == sysPermission.getParentId()){
                flag = false;
                break;
            }
        }
        return flag;
    }

    private MenuVo getMenuVo(SysPermission sysPermission){
        MenuVo menuVo = new MenuVo();
        menuVo.setPath(sysPermission.getPath());
        menuVo.setAlwaysShow(StrUtil.isEmpty(sysPermission.getAlwaysshow())? null:"1".equals(sysPermission.getAlwaysshow())?true:false);
        menuVo.setHidden(StrUtil.isEmpty(sysPermission.getHidden())? null:"1".equals(sysPermission.getHidden())?true:false);
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
