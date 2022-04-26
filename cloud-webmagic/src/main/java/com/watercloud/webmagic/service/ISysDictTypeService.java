package com.watercloud.webmagic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.watercloud.webmagic.entity.SysDictType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.watercloud.webmagic.vo.dict.DictTypeInputOutVo;
import com.watercloud.webmagic.vo.dict.DictTypeQueryParamVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典类型表 服务类
 * </p>
 *
 * @author lly
 * @since 2022-04-25
 */
public interface ISysDictTypeService extends IService<SysDictType> {
     public IPage list(DictTypeQueryParamVo dictTypeQueryParamVo);
     public DictTypeInputOutVo getDictById(Long dictId);
     public Boolean updateDictByIdOrSave(DictTypeInputOutVo dictTypeInputOutVo);
     public Boolean delByDictType(String dictType);
     public List<Map<String,String>> getTypeGroup();
}
