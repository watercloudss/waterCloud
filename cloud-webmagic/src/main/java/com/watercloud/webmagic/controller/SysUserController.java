package com.watercloud.webmagic.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.watercloud.webmagic.common.aspect.annotation.AutoLogAnnotation;
import com.watercloud.webmagic.common.config.shiro.jwt.JwtTool;
import com.watercloud.webmagic.common.util.CommonConstant;
import com.watercloud.webmagic.common.util.RedisUtil;
import com.watercloud.webmagic.common.commonVo.Result;
import com.watercloud.webmagic.entity.SysUser;
import com.watercloud.webmagic.service.ISysUserService;
import com.watercloud.webmagic.vo.SysLoginVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.validation.Valid;

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
    @Value("${JWTConfig.EXPIRE_TIME}")
    private long EXPIRE_TIME;
    @Autowired
    private ISysUserService iSysUserService;
    @Autowired
    private JwtTool jwtTool;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    DataSource dataSource;

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
                String token = jwtTool.sign(username);
                redisUtil.set(token,token,EXPIRE_TIME/1000);
                JSONObject res = new JSONObject();
                res.put("token", token);
                result.setCode(CommonConstant.SC_OK_200);
                result.setResult(res);
            }else{
                result.setMessage("用户名或密码错误！！！");
                result.setCode(CommonConstant.SC_NO_AUTHZ);
            }
        }
         return result;
    }

    @GetMapping("/logout")
    public Result<String> logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        redisUtil.del(token);
        Subject subject = SecurityUtils.getSubject();
        //这一步原理是认证时SimpleAuthenticationInfo()，第二个参数我们设置的token，所以logout一定要让shiro管理同时header带上token，这时SecurityUtils.getSubject()能拿到相关的值同时会清楚redis中该用户的权限
        subject.logout();
        Result<String> result = Result.OK("成功注销");
        return result;
    }

    @PostMapping("/test")
    @AutoLogAnnotation(logType=CommonConstant.LOG_TYPE_1)
//    @RequiresRoles({"user"})
    @RequiresPermissions("user:add")
    public Result<SysUser> test(String username,String pass, String gender){
        System.out.println(username+":"+pass+":"+gender);
        SysUser sysUser = (SysUser)SecurityUtils.getSubject().getPrincipal();
        redisTemplate.opsForValue().set(sysUser.getUsername(),sysUser);
        SysUser sysUser1 = (SysUser) redisUtil.get(sysUser.getUsername());
        iSysUserService.test(username,pass,gender);
        Result<SysUser> result = Result.OK(sysUser);
        return result;
    }

    @PostMapping("/test1")
    public Result<String> test(@Valid SysLoginVo sysLoginVo){
        System.out.println(sysLoginVo.getUsername()+":"+sysLoginVo.getPassword());
        Result<String> result = Result.OK("test1");
        redisUtil.set("redislock","3213131",130);
        return result;

    }

    @PostMapping("/test2")
    public Result<String> tes2(@Valid SysLoginVo sysLoginVo){
        System.out.println(sysLoginVo.getUsername()+":"+sysLoginVo.getPassword());
        Result<String> result = Result.OK("tes2");
        System.out.println(redisUtil.getExpire("redislock"));
        System.out.println( redisUtil.releaseLock("redislock","3213131"));
        System.out.println(SecurityUtils.getSubject().getPrincipal());
        return result;
    }



    @GetMapping("/test3")
    public Result<Object> test3(){
        return Result.error(CommonConstant.SC_NO_AUTHZ,"123");
    }

    @GetMapping("/403")
    public Result<?> noauth() {
        return Result.error("没有认证，请登录认证!");
    }
}
