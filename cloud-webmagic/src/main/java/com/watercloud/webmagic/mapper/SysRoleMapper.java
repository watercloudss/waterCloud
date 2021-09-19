package com.watercloud.webmagic.mapper;

import com.watercloud.webmagic.entity.SysRole;
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
public interface SysRoleMapper extends BaseMapper<SysRole> {
    public List<String> getUserRole(Integer userId);
}
