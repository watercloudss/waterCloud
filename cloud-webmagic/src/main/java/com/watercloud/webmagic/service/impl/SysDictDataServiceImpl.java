package com.watercloud.webmagic.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.watercloud.webmagic.entity.SysDictData;
import com.watercloud.webmagic.mapper.SysDictDataMapper;
import com.watercloud.webmagic.service.ISysDictDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.watercloud.webmagic.vo.dict.DictDataInputOutVo;
import com.watercloud.webmagic.vo.dict.DictDataQueryParamVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典数据表 服务实现类
 * </p>
 *
 * @author lly
 * @since 2022-04-25
 */
@Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements ISysDictDataService {

    @Override
    public IPage list(DictDataQueryParamVo dictDataQueryParamVo) {
        IPage iPage = new Page<>();
        iPage.setCurrent(dictDataQueryParamVo.getPageNum());
        iPage.setSize(dictDataQueryParamVo.getPageSize());
        QueryWrapper<SysDictData> queryWrapper = new QueryWrapper<>();
        if(StrUtil.isNotEmpty(dictDataQueryParamVo.getName()))
            queryWrapper.eq("dict_label",dictDataQueryParamVo.getName());
        if(StrUtil.isNotEmpty(dictDataQueryParamVo.getDictType())){
            queryWrapper.eq("dict_type",dictDataQueryParamVo.getDictType());
        }
        if(StrUtil.isNotEmpty(dictDataQueryParamVo.getBeginTime())&&StrUtil.isNotEmpty(dictDataQueryParamVo.getEndTime())) {
            queryWrapper.between("create_time",dictDataQueryParamVo.getBeginTime()
                    , DateUtil.format(DateUtil.offsetDay(DateUtil.parse(dictDataQueryParamVo.getEndTime()), 1), "yyyy-MM-dd")
            );
        }
        IPage page = this.page(iPage,queryWrapper);
        page.setRecords(Convert.toList(DictDataInputOutVo.class,page.getRecords()));
        return page;
    }

    @Override
    public DictDataInputOutVo getByDictCode(Long dictCode) {
        SysDictData sysDictData = this.getById(dictCode);
        DictDataInputOutVo dictDataInputOutVo = Convert.convert(DictDataInputOutVo.class,sysDictData);
        return dictDataInputOutVo;
    }

    @Override
    public Boolean updateDictByIdOrSave(DictDataInputOutVo dictDataInputOutVo) {
        SysDictData sysDictData = Convert.convert(SysDictData.class,dictDataInputOutVo);
        return this.saveOrUpdate(sysDictData);
    }

    @Override
    public Boolean delByDictCode(Long dictCode) {
        return this.removeById(dictCode);
    }
}
