package com.watercloud.webmagic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.watercloud.webmagic.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.watercloud.webmagic.vo.dict.DictDataQueryParamVo;
import com.watercloud.webmagic.vo.role.RoleQueryParamVo;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lly
 * @since 2021-09-19
 */
public interface ISysRoleService extends IService<SysRole> {
     public Set<String> getUserRole(Integer userId);
     public IPage list(RoleQueryParamVo roleQueryParamVo);
}
