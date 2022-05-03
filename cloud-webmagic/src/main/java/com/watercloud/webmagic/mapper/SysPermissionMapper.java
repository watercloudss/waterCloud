package com.watercloud.webmagic.mapper;

import com.watercloud.webmagic.entity.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lly
 * @since 2022-04-23
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    public List<String> getUserPermission(Integer userId);
    public List<SysPermission> getSysPermissionByRoleId(@Param("roleId")Integer roleId,@Param("flag") Integer flag);
}
