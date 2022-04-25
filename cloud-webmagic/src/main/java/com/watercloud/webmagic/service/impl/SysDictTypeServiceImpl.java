package com.watercloud.webmagic.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.watercloud.webmagic.entity.SysDictData;
import com.watercloud.webmagic.entity.SysDictType;
import com.watercloud.webmagic.mapper.SysDictTypeMapper;
import com.watercloud.webmagic.service.ISysDictDataService;
import com.watercloud.webmagic.service.ISysDictTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.watercloud.webmagic.vo.dict.DictTypeListOutVo;
import com.watercloud.webmagic.vo.dict.DictTypeQueryParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典类型表 服务实现类
 * </p>
 *
 * @author lly
 * @since 2022-04-25
 */
@Service
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements ISysDictTypeService {
    @Autowired
    private ISysDictDataService iSysDictDataService;

    @Override
    public IPage list(DictTypeQueryParamVo dictTypeQueryParamVo) {
        IPage<SysDictType> iPage = new Page<>();
        iPage.setCurrent(dictTypeQueryParamVo.getPageNum());
        iPage.setSize(dictTypeQueryParamVo.getPageSize());
        QueryWrapper<SysDictType> queryWrapper = new QueryWrapper<>();
        if(StrUtil.isNotEmpty(dictTypeQueryParamVo.getDictName()))
          queryWrapper.eq("dict_name",dictTypeQueryParamVo.getDictName());
        if(StrUtil.isNotEmpty(dictTypeQueryParamVo.getDictType()))
          queryWrapper.eq("dict_type",dictTypeQueryParamVo.getDictType());
        if(StrUtil.isNotEmpty(dictTypeQueryParamVo.getBeginTime())&&StrUtil.isNotEmpty(dictTypeQueryParamVo.getEndTime())) {
            queryWrapper.between("create_time",dictTypeQueryParamVo.getBeginTime()
                    , DateUtil.format(DateUtil.offsetDay(DateUtil.parse(dictTypeQueryParamVo.getEndTime()), 1), "yyyy-MM-dd")
            );
        }
        IPage page = this.page(iPage,queryWrapper);
        page.setRecords(Convert.toList(DictTypeListOutVo.class,page.getRecords()));
        return page;
    }

    @Override
    public Boolean delByDeptType(String dictType) {
        QueryWrapper<SysDictType> qwdt = new QueryWrapper<>();
        qwdt.eq("dict_type",dictType);
        ;
        QueryWrapper<SysDictData> qwdd = new QueryWrapper<>();
        qwdd.eq("dict_type",dictType);
        if(this.remove(qwdt)&&iSysDictDataService.remove(qwdd)){
            return true;
        }
        return false;
    }
}
