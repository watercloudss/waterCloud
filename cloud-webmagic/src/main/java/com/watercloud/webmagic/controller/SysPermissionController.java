package com.watercloud.webmagic.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloudwater.common.commonVo.Result;
import com.watercloud.webmagic.common.util.CommonConstant;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lly
 * @since 2021-09-19
 */
@RestController
@RequestMapping("/sys-permission")
public class SysPermissionController {

    @PostMapping("/getRouters")
    public Result<JSONArray> getRouters(){
        Result<JSONArray> result = Result.OK();
        String str = "[{\"path\":\"/icon\",\"component\":\"Layout\",\"children\":[{\"path\":\"index\",\"component\":\"icons/index\",\"name\":\"Icons\",\"meta\":{\"title\":\"icons\",\"icon\":\"icon\",\"noCache\":true}}]}]";
        JSONArray res = (JSONArray) JSON.parse(str);
        result.setCode(CommonConstant.SC_OK_200);
        result.setData(res);
        return result;
    }
}
