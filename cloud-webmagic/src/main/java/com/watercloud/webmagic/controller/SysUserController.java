package com.watercloud.webmagic.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.watercloud.webmagic.common.aspect.annotation.AuthCheckAnnotation;
import com.watercloud.webmagic.common.aspect.annotation.AutoLogAnnotation;
import com.watercloud.webmagic.common.exception.CloudWebmagicException;
import com.watercloud.webmagic.common.jwt.JwtTool;
import com.watercloud.webmagic.common.util.CommonConstant;
import com.watercloud.webmagic.common.validator.annotation.Gender;
import com.watercloud.webmagic.common.vo.Result;
import com.watercloud.webmagic.entity.SysUser;
import com.watercloud.webmagic.service.ISysUserService;
import com.watercloud.webmagic.vo.SysLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lly
 * @since 2021-09-14
 */
@RestController
@RequestMapping("/sys-user")
@Validated
public class SysUserController {
    @Autowired
    private ISysUserService iSysUserService;
    @Autowired
    private JwtTool jwtTool;

    @PostMapping("/login")
    @AutoLogAnnotation(logType=CommonConstant.LOG_TYPE_2)
    public Result<JSONObject> login(@RequestBody @Valid SysLoginVo sysLoginVo){
        String username = sysLoginVo.getUsername();
        String password = sysLoginVo.getPassword();
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        SysUser sysUser = iSysUserService.getOne(queryWrapper);
        Result<JSONObject> result = new Result<>();
        if(sysUser==null){
            result.setMessage("用户名或密码错误！！！");
            result.setCode(CommonConstant.SC_NO_AUTHZ);
        }else{
            if(password.equals( sysUser.getPassword())){
                JSONObject res = new JSONObject();
                res.put("token", jwtTool.sign(username));
                result.setCode(CommonConstant.SC_OK_200);
                result.setResult(res);
            }else{
                result.setMessage("用户名或密码错误！！！");
                result.setCode(CommonConstant.SC_NO_AUTHZ);
            }
        }
         return result;
    }

    @PostMapping("/test")
    @AuthCheckAnnotation
    public Result<String> test(String username,String pass, String gender){
        System.out.println(username+":"+pass+":"+gender);
        iSysUserService.test(username,pass,gender);
        Result<String> result = Result.OK("ojojojojojojo");
        return result;
    }

    @PostMapping("/test1")
    public Result<String> test(@Valid SysLoginVo sysLoginVo){
        System.out.println(sysLoginVo.getUsername()+":"+sysLoginVo.getPassword());
        Result<String> result = Result.OK("ojojojojojojo");
        return result;

    }
}
