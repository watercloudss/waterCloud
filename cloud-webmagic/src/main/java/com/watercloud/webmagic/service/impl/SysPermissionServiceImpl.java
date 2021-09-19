package com.watercloud.webmagic.service.impl;

import com.watercloud.webmagic.entity.SysPermission;
import com.watercloud.webmagic.mapper.SysPermissionMapper;
import com.watercloud.webmagic.service.ISysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @since 2021-09-19
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public Set<String> getUserPermission(String username) {
        List<String>  userPermissionList = sysPermissionMapper.getUserPermission(username);
        Set<String> userPermissionSet = new HashSet<>(userPermissionList);
        return userPermissionSet;
    }
}
