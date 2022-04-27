package com.watercloud.webmagic.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.watercloud.webmagic.entity.SysDictData;
import com.watercloud.webmagic.entity.SysRole;
import com.watercloud.webmagic.mapper.SysRoleMapper;
import com.watercloud.webmagic.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.watercloud.webmagic.vo.role.RoleInputOutVo;
import com.watercloud.webmagic.vo.role.RoleQueryParamVo;
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
    public Set<String> getUserRole(Integer userId) {
        List<String> roleList = sysRoleMapper.getUserRole(userId);
        Set<String> roleSet = new HashSet<>(roleList);
        return roleSet;
    }

    @Override
    public IPage list(RoleQueryParamVo roleQueryParamVo) {
        IPage page = new Page<>();
        page.setCurrent(roleQueryParamVo.getPageNum());
        page.setSize(roleQueryParamVo.getPageSize());
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        if(StrUtil.isNotEmpty(roleQueryParamVo.getRoleName()))
            queryWrapper.eq("role_name",roleQueryParamVo.getRoleName());
        if(StrUtil.isNotEmpty(roleQueryParamVo.getRoleCode())){
            queryWrapper.eq("role_code",roleQueryParamVo.getRoleCode());
        }
        if(StrUtil.isNotEmpty(roleQueryParamVo.getBeginTime())&&StrUtil.isNotEmpty(roleQueryParamVo.getEndTime())) {
            queryWrapper.between("create_time",roleQueryParamVo.getBeginTime()
                    , DateUtil.format(DateUtil.offsetDay(DateUtil.parse(roleQueryParamVo.getEndTime()), 1), "yyyy-MM-dd")
            );
        }
        IPage iPage  = this.page(page,queryWrapper);
        page.setRecords(Convert.toList(RoleInputOutVo.class,page.getRecords()));
        return iPage;
    }
}
