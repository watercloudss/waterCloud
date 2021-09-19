package com.watercloud.webmagic.service.impl;

import com.watercloud.webmagic.entity.SysRole;
import com.watercloud.webmagic.mapper.SysRoleMapper;
import com.watercloud.webmagic.service.ISysRoleService;
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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public Set<String> getUserRole(String username) {
        List<String> roleList = sysRoleMapper.getUserRole(username);
        Set<String> roleSet = new HashSet<>(roleList);
        return roleSet;
    }
}
