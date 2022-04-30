package com.watercloud.webmagic.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloudwater.common.commonVo.Result;
import com.watercloud.webmagic.service.ISysDictTypeService;
import com.watercloud.webmagic.vo.dict.DictTypeInputOutVo;
import com.watercloud.webmagic.vo.dict.DictTypeQueryParamVo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    @RequiresPermissions("system:dict:type:list")
    public Result<IPage> list(DictTypeQueryParamVo dictTypeQueryParamVo) {
        IPage iPage = iSysDictTypeService.list(dictTypeQueryParamVo);
        Result<IPage> result = Result.OK(iPage);
        return result;
    }

    @GetMapping("/getDictById/{dictId}")
    @RequiresPermissions("system:dict:type:detail")
    public Result<DictTypeInputOutVo> getDictById(@PathVariable Long dictId){
        DictTypeInputOutVo dictTypeOutVo = iSysDictTypeService.getDictById(dictId);
        Result<DictTypeInputOutVo> result = Result.OK(dictTypeOutVo);
        return result;
    }

    @GetMapping("/getTypeGroup")
    @RequiresPermissions(value={"system:dict:type:group","system:dict:data:add","system:dict:data:update"},logical = Logical.OR)
    public Result<List<Map<String,String>>> getDictById(){
        Result<List<Map<String,String>>> result = Result.OK(iSysDictTypeService.getTypeGroup());
        return result;
    }

    @PutMapping("/updateDictByIdOrSave")
    @RequiresPermissions(value={"system:dict:type:add","system:dict:type:update"},logical= Logical.OR)
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
    @RequiresPermissions("system:dict:type:delete")
    public Result del(@PathVariable String deptType){
        Boolean status = iSysDictTypeService.delByDictType(deptType);
        Result result = null;
        if(status){
            result = Result.OK();
        }
        return result;
    }
}
