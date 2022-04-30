package com.watercloud.webmagic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.watercloud.webmagic.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.watercloud.webmagic.vo.menu.MenuQueryParamVo;
import com.watercloud.webmagic.vo.menu.MenuVo;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lly
 * @since 2022-04-23
 */
public interface ISysPermissionService extends IService<SysPermission> {
    public Set<String> getUserPermission(Integer userId);
    public List<MenuVo> getSysPermissionByUserId(Integer userId);
    public IPage getList(MenuQueryParamVo menuQueryParamVo);
}
