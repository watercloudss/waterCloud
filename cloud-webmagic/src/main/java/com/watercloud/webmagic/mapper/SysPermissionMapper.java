package com.watercloud.webmagic.mapper;

import com.watercloud.webmagic.entity.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lly
 * @since 2021-09-19
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    public List<String> getUserPermission(String username);
}
