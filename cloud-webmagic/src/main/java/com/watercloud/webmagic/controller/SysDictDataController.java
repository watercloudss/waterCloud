package com.watercloud.webmagic.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloudwater.common.commonVo.Result;
import com.watercloud.webmagic.service.ISysDictDataService;
import com.watercloud.webmagic.vo.dict.DictDataQueryParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 字典数据表 前端控制器
 * </p>
 *
 * @author lly
 * @since 2022-04-25
 */
@RestController
@RequestMapping("/sys-dict-data")
public class SysDictDataController {
    @Autowired
    private ISysDictDataService iSysDictDataService;

    @GetMapping("/list")
    public Result<IPage> list(DictDataQueryParamVo dictDataQueryParamVo) {
        IPage iPage = iSysDictDataService.list(dictDataQueryParamVo);
        Result<IPage> result = Result.OK(iPage);
        return result;
    }

}
