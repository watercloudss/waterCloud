package com.watercloud.webmagic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.watercloud.webmagic.entity.SysDictData;
import com.baomidou.mybatisplus.extension.service.IService;
import com.watercloud.webmagic.vo.dict.DictDataQueryParamVo;

/**
 * <p>
 * 字典数据表 服务类
 * </p>
 *
 * @author lly
 * @since 2022-04-25
 */
public interface ISysDictDataService extends IService<SysDictData> {
    public IPage list(DictDataQueryParamVo dictDataQueryParamVo);
}
