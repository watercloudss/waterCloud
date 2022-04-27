package com.watercloud.webmagic.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloudwater.common.commonVo.Result;
import com.watercloud.webmagic.service.ISysDictDataService;
import com.watercloud.webmagic.vo.dict.DictDataInputOutVo;
import com.watercloud.webmagic.vo.dict.DictDataQueryParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getByDictCode/{dictCode}")
    public Result<DictDataInputOutVo> getByDictCode(@PathVariable Long dictCode){
        DictDataInputOutVo dictDataInputOutVo = iSysDictDataService.getByDictCode(dictCode);
        Result result = Result.OK(dictDataInputOutVo);
        return result;
    }

    @PutMapping("/updateDictByIdOrSave")
    public Result updateDictByIdOrSave(@RequestBody DictDataInputOutVo dictDataInputOutVo){
        Result result = null;
        if(iSysDictDataService.updateDictByIdOrSave(dictDataInputOutVo)){
            result = Result.OK();
        }else{
            result = Result.error("操作失败");
        }
        return result;
    }

    @DeleteMapping("/del/{dictCode}")
    public Result  delByDictCode(@PathVariable Long dictCode){
       Result result = null;
       if(iSysDictDataService.delByDictCode(dictCode)){
           result = Result.OK();
       }else{
           result = Result.error("删除失败");
       }
       return result;
    }

}
