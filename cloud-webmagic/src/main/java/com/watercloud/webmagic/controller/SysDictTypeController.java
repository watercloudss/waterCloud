package com.watercloud.webmagic.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloudwater.common.commonVo.Result;
import com.watercloud.webmagic.service.ISysDictTypeService;
import com.watercloud.webmagic.vo.dict.DictTypeInputOutVo;
import com.watercloud.webmagic.vo.dict.DictTypeQueryParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典类型表 前端控制器
 * </p>
 *
 * @author lly
 * @since 2022-04-25
 */
@RestController
@RequestMapping("/sys-dict-type")
public class SysDictTypeController {
    @Autowired
    private ISysDictTypeService iSysDictTypeService;

    @GetMapping("/list")
    public Result<IPage> list(DictTypeQueryParamVo dictTypeQueryParamVo) {
        IPage iPage = iSysDictTypeService.list(dictTypeQueryParamVo);
        Result<IPage> result = Result.OK(iPage);
        return result;
    }

    @GetMapping("/getDictById/{dictId}")
    public Result<DictTypeInputOutVo> getDictById(@PathVariable Long dictId){
        DictTypeInputOutVo dictTypeOutVo = iSysDictTypeService.getDictById(dictId);
        Result<DictTypeInputOutVo> result = Result.OK(dictTypeOutVo);
        return result;
    }

    @GetMapping("/getTypeGroup")
    public Result<List<Map<String,String>>> getDictById(){
        Result<List<Map<String,String>>> result = Result.OK(iSysDictTypeService.getTypeGroup());
        return result;
    }

    @PutMapping("/updateDictByIdOrSave")
    public Result updateDictByIdOrSave(@RequestBody DictTypeInputOutVo dictTypeInputOutVo){
        Result result = null;
        if(iSysDictTypeService.updateDictByIdOrSave(dictTypeInputOutVo)){
            result = Result.OK();
        }else{
            result = Result.error("新增失败：字典类型编码已存在！");
        }
        return result;
    }

    @DeleteMapping("/delByType/{deptType}")
    public Result del(@PathVariable String deptType){
        Boolean status = iSysDictTypeService.delByDictType(deptType);
        Result result = null;
        if(status){
            result = Result.OK();
        }
        return result;
    }
}
