package com.watercloud.webmagic.controller;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.system.UserInfo;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloudwater.common.commonVo.Result;
import com.watercloud.webmagic.common.aspect.annotation.AutoLogAnnotation;
import com.watercloud.webmagic.common.util.CommonConstant;
import com.watercloud.webmagic.common.util.RedisConstant;
import com.watercloud.webmagic.common.util.RedisUtil;
import com.watercloud.webmagic.config.shiro.jwt.JwtTool;
import com.watercloud.webmagic.entity.SysDictType;
import com.watercloud.webmagic.entity.SysRole;
import com.watercloud.webmagic.entity.SysUser;
import com.watercloud.webmagic.entity.SysUserRole;
import com.watercloud.webmagic.service.ISysRoleService;
import com.watercloud.webmagic.service.ISysUserRoleService;
import com.watercloud.webmagic.service.ISysUserService;
import com.watercloud.webmagic.vo.dict.DictTypeInputOutVo;
import com.watercloud.webmagic.vo.role.RoleInputOutVo;
import com.watercloud.webmagic.vo.role.RoleQueryParamVo;
import com.watercloud.webmagic.vo.user.SysLoginVo;
import com.watercloud.webmagic.vo.user.UserInfoVo;
import com.watercloud.webmagic.vo.user.UserInputOutVo;
import com.watercloud.webmagic.vo.user.UserQueryParamVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.validation.Valid;
import java.util.Set;

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
    @Value("${ShiroConfig.redisExpire}")
    private Integer redisExpire;
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
    @Autowired
    private ISysRoleService iSysRoleService;
    @Autowired
    private ISysUserRoleService iSysUserRoleService;

    @PostMapping("/login")
    @AutoLogAnnotation(logType= CommonConstant.LOG_TYPE_2)
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
            if(password.equals(sysUser.getPassword())){
                String token = jwtTool.sign(username);
                redisUtil.set(token,token,EXPIRE_TIME/1000);
                redisUtil.set(RedisConstant.SYS_USERS +sysUser.getUsername(),sysUser,redisExpire);
                JSONObject res = new JSONObject();
                res.put("token", token);
                result.setCode(CommonConstant.SC_OK_200);
                result.setData(res);
            }else{
                result.setMessage("用户名或密码错误！！！");
                result.setCode(CommonConstant.SC_NO_AUTHZ);
            }
        }
         return result;
    }

    @PostMapping("/logout")
    public Result<String> logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        redisUtil.del(token);
        Subject subject = SecurityUtils.getSubject();
        //这一步原理是认证时SimpleAuthenticationInfo()，第二个参数我们设置的token，所以logout一定要让shiro管理同时header带上token，这时SecurityUtils.getSubject()能拿到相关的值同时会清楚redis中该用户的权限
        subject.logout();
        Result<String> result = Result.OK("成功注销");
        return result;
    }

    @RequestMapping("/info")
    @AutoLogAnnotation(logType=CommonConstant.LOG_TYPE_1)
    public Result<UserInfoVo> info(){
        SysUser sysUser = (SysUser)SecurityUtils.getSubject().getPrincipal();
        Set<String> roleSet = iSysRoleService.getUserRole(sysUser.getId());
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setRoles(roleSet);
        userInfoVo.setUser(sysUser);
        Result<UserInfoVo> result = Result.OK(userInfoVo);
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
//        System.out.println( redisUtil.releaseLock("redislock","3213131"));
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

    @GetMapping("/list")
    @RequiresPermissions("system:users:list")
    //@RequiresRoles({"user"})
    public Result<IPage> list(UserQueryParamVo userQueryParamVo) {
        IPage iPage = new Page<>();
        iPage.setCurrent(userQueryParamVo.getPageNum());
        iPage.setSize(userQueryParamVo.getPageSize());
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        if(StrUtil.isNotEmpty(userQueryParamVo.getName()))
            queryWrapper.eq("name",userQueryParamVo.getName());
        if(StrUtil.isNotEmpty(userQueryParamVo.getUsername()))
            queryWrapper.eq("username",userQueryParamVo.getUsername());
        if(StrUtil.isNotEmpty(userQueryParamVo.getBeginTime())&&StrUtil.isNotEmpty(userQueryParamVo.getEndTime())) {
            queryWrapper.between("create_time",userQueryParamVo.getBeginTime()
                    , DateUtil.format(DateUtil.offsetDay(DateUtil.parse(userQueryParamVo.getEndTime()), 1), "yyyy-MM-dd")
            );
        }
        queryWrapper.eq("del_flag","0");
        queryWrapper.orderByDesc("create_time");
        IPage page = iSysUserService.page(iPage,queryWrapper);
        page.setRecords(Convert.toList(UserInputOutVo.class,page.getRecords()));
        Result<IPage> result = Result.OK(iPage);
        return result;
    }

    @GetMapping("/getById/{id}")
    @RequiresPermissions("system:users:detail")
    public Result<RoleInputOutVo> getByDictCode(@PathVariable Integer id){
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        SysUserRole sysUserRole = iSysUserRoleService.getOne(queryWrapper);
        SysUser sysUser = iSysUserService.getById(id);
        UserInputOutVo userInputOutVo = Convert.convert(UserInputOutVo.class,sysUser);
        if(sysUserRole!=null){
            SysRole sysRole = iSysRoleService.getById(sysUserRole.getRoleId());
            userInputOutVo.setRoleCode(sysRole.getRoleCode());
        }
        Result result = Result.OK(userInputOutVo);
        return result;
    }

    @PutMapping("/updateOrSave")
    @RequiresPermissions(value={"system:users:add","system:users:update"},logical= Logical.OR)
    @Transactional
    public Result updateOrSave(@RequestBody UserInputOutVo userInputOutVo){
        Result result = null;
        SysUser sysUser = Convert.convert(SysUser.class,userInputOutVo);
        QueryWrapper<SysRole> qwsr = new QueryWrapper<>();
        qwsr.eq("role_code",userInputOutVo.getRoleCode());
        SysRole sysRole = iSysRoleService.getOne(qwsr);
        boolean uFlag=true;
        boolean urFlag=true;
        if(userInputOutVo.getId()!=null){
            QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id",userInputOutVo.getId());
            SysUserRole sysUserRole = iSysUserRoleService.getOne(queryWrapper);
            sysUserRole.setRoleId(sysRole.getId());
            urFlag = iSysUserRoleService.updateById(sysUserRole);
            uFlag = iSysUserService.updateById(sysUser);
        }else{
            QueryWrapper<SysUser> suqw = new QueryWrapper<>();
            suqw.eq("username",sysUser.getUsername());
            if(iSysUserService.count()>0){
                result = Result.error("操作失败:用户账号已存在！");
            }
            sysUser.setPassword("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92");
            uFlag = iSysUserService.save(sysUser);
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(sysRole.getId());
            sysUserRole.setUserId(sysUser.getId());
            urFlag = iSysUserRoleService.save(sysUserRole);
        }
        if(uFlag&&urFlag){
            result = Result.OK();
        }else{
            result = Result.error("操作失败");
        }
        return result;
    }

    @DeleteMapping("/del/{id}")
    @RequiresPermissions("system:users:delete")
    public Result  del(@PathVariable Integer id){
        SysUser sysUser = new SysUser();
        sysUser.setDelFlag(true);
        sysUser.setId(id);
        Result result = null;
        if(iSysUserService.updateById(sysUser)){
            result = Result.OK();
        }else{
            result = Result.error("删除失败");
        }
        return result;
    }
}
