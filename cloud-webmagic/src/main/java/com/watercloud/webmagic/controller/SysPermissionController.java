package com.watercloud.webmagic.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cloudwater.common.commonVo.Result;
import com.watercloud.webmagic.common.util.CommonConstant;
import com.watercloud.webmagic.entity.SysPermission;
import com.watercloud.webmagic.entity.SysUser;
import com.watercloud.webmagic.service.ISysPermissionService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lly
 * @since 2022-04-23
 */
@RestController
@RequestMapping("/sys-permission")
public class SysPermissionController {
    @Autowired
    private ISysPermissionService iSysPermissionService;
//    @PostMapping("/getRouters")
//    public Result<JSONArray> getRouters(){
//        Result<JSONArray> result = Result.OK();
//        String str = "[{\"path\":\"/icon\",\"component\":\"Layout\",\"children\":[{\"path\":\"index\",\"component\":\"icons/index\",\"name\":\"Icons\",\"meta\":{\"title\":\"icons\",\"icon\":\"icon\",\"noCache\":true}}]}]";
//        JSONArray res = (JSONArray) JSON.parse(str);
//        result.setCode(CommonConstant.SC_OK_200);
//        result.setData(res);
//        return result;
//    }
    @PostMapping("/getRouters")
    public Result<List<SysPermission>> getRouters(){
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        List<SysPermission> sysPermissionsList = iSysPermissionService.getSysPermissionByUserId(sysUser.getId());
        Result<List<SysPermission>> result = Result.OK(sysPermissionsList);
        return result;
    }
}
