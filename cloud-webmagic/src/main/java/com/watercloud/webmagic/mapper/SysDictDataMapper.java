package com.watercloud.webmagic.mapper;

import com.watercloud.webmagic.entity.SysDictData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典数据表 Mapper 接口
 * </p>
 *
 * @author lly
 * @since 2022-04-25
 */
@Mapper
public interface SysDictDataMapper extends BaseMapper<SysDictData> {
    public List<Map<String,String>> getTypeGroup();
}
