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
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;

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
    @RequiresRoles({"admin"})
//    @RequiresPermissions("user:add")
    public Result<String> test(String username,String pass, String gender){
        System.out.println(username+":"+pass+":"+gender);
        System.out.println(SecurityUtils.getSubject().getPrincipal());
        iSysUserService.test(username,pass,gender);
        Result<String> result = Result.OK("ojojojojojojo");
        return result;
    }

    @PostMapping("/test1")
    public Result<String> test(@Valid SysLoginVo sysLoginVo){
        System.out.println(sysLoginVo.getUsername()+":"+sysLoginVo.getPassword());
        Result<String> result = Result.OK("test1");
        return result;

    }

    @PostMapping("/test2")
    public Result<String> tes2(@Valid SysLoginVo sysLoginVo){
        System.out.println(sysLoginVo.getUsername()+":"+sysLoginVo.getPassword());
        Result<String> result = Result.OK("tes2");
        return result;

    }

    @GetMapping("/nologin")
    public String nologin(){
        Result<String> result = Result.OK("没有登录");
        return "没有登录";

    }

    @GetMapping("/noRole")
    public Result<String> noRole(){
        Result<String> result = Result.OK("没有权限");
        return result;

    }

    @GetMapping("/logout")
    public Result<String> logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        Result<String> result = Result.OK("成功注销");
        return result;
    }
    @GetMapping("/test3")
    public Result<Object> test3(){
        return Result.error(CommonConstant.SC_NO_AUTHZ,"123");
    }

    @RequestMapping(path = "/unauthorized/{message}")
    public Result<Object> unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
        return Result.error(CommonConstant.SC_NO_AUTHZ,message);
    }

    @GetMapping("/403")
    public Result<?> noauth()  {
        return Result.error("没有权限，请联系管理员授权");
    }
}
