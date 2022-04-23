package com.watercloud.webmagic.service.impl;

import com.watercloud.webmagic.entity.SysPermission;
import com.watercloud.webmagic.mapper.SysPermissionMapper;
import com.watercloud.webmagic.service.ISysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.watercloud.webmagic.vo.menu.ChildrenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Override
    public Set<String> getUserPermission(Integer userId) {
        List<String> userPermissionList = sysPermissionMapper.getUserPermission(userId);
        Set<String> userPermissionSet = new HashSet<>(userPermissionList);
        return userPermissionSet;
    }

    @Override
    public List<SysPermission> getSysPermissionByUserId(Integer userId) {
        List<SysPermission> sysPermissionsList = sysPermissionMapper.getSysPermissionByUserId(userId);
        return sysPermissionsList;
    }

    public List<ChildrenVo> getChildren(List<SysPermission> sysPermissionsList,Integer parentId){
         for(int i=0; i<sysPermissionsList.size(); i++){
             SysPermission sysPermission = sysPermissionsList.get(i);
             if(sysPermission.getParentId() == parentId){

             }
         }
         return null;
    }
}
