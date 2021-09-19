package com.watercloud.webmagic.service;

import com.watercloud.webmagic.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lly
 * @since 2021-09-19
 */
public interface ISysPermissionService extends IService<SysPermission> {
     public Set<String> getUserPermission(Integer userId);
}
