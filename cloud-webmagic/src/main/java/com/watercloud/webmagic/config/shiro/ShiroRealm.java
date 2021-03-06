package com.watercloud.webmagic.config.shiro;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.watercloud.webmagic.common.util.RedisConstant;
import com.watercloud.webmagic.common.util.RedisUtil;
import com.watercloud.webmagic.config.shiro.jwt.JwtTool;
import com.watercloud.webmagic.entity.SysRole;
import com.watercloud.webmagic.entity.SysUser;
import com.watercloud.webmagic.service.ISysPermissionService;
import com.watercloud.webmagic.service.ISysRoleService;
import com.watercloud.webmagic.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ISysUserService iSysUserService;
    @Autowired
    private ISysRoleService iSysRoleService;
    @Autowired
    private ISysPermissionService iSysPermissionService;
    @Value("${JWTConfig.EXPIRE_TIME}")
    private long EXPIRE_TIME;
    @Value("${ShiroConfig.redisExpire}")
    private Integer redisExpire;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof ShiroJWTToken;
    }
    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     * 超时或错误的token会被gateway网关拦截，能够进来的都是有效的token
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String)authenticationToken.getCredentials();
        String username = JwtTool.getTokenUsername(token);
        log.info("++++++++++++身份认证方法开始++++++++++++:username{username},token{}",token);
        if(!refreshRedisToken(token,username)){
            log.info("————————————身份认证方法完毕:token刷新失败！————————————:username{username},token{}",token);
            throw new AuthenticationException("认证过期，请登录认证!");
        }
        if(StrUtil.isBlank(username)){
            log.info("————————————身份认证方法完毕：token有错误！————————————:username{username},token{}",token);
            throw new AuthenticationException("token有错误！");
        }
        SysUser redisSysUser = (SysUser) redisUtil.get(RedisConstant.SYS_USERS+username);
        if(redisSysUser==null){
            // 从数据库获取对应用户名密码的用户
            QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username",username);
            SysUser sysUser = iSysUserService.getOne(queryWrapper);
            if (null == sysUser||sysUser.getStatus().equals("2")) {
                log.info("————————————身份认证方法完毕：用户不存在！————————————:username{username},token{}",token);
                throw new AuthenticationException("token不正确的！");
            }else{
                redisUtil.set(RedisConstant.SYS_USERS+sysUser.getUsername(),sysUser,redisExpire);
                log.info("————身份认证方法完毕：成功认证！————:username{username},token{}",token);
                return new SimpleAuthenticationInfo(sysUser, token, "MyRealm");
            }
        }else{
            log.info("————身份认证方法完毕：成功认证！————:username{username},token{}",token);
            return new SimpleAuthenticationInfo(redisSysUser, token, "MyRealm");
        }
    }

    /*
    * 这个方法主要用来做保证用户操作时不会掉线
    * 登录时，将token存到redis中，k,v都为token，
    * 其他接口带着token访问时，使用jwt校验v，校验通过的话，重新设置v，
    * 这样只要用户半小时内发请求，登录时k为token的value就永远不会过期，
    * 若用户登录或调用某个接口后，半小时都没有再发任何请求，这时k为token在redis中就会过期，就需要重新认证
    * 即使两个请求并发，value都是能校验通过的
    * */
    private boolean refreshRedisToken(String token,String username){
        String tokenValue = (String) redisUtil.get(token);
        if(StrUtil.isNotBlank(tokenValue)){
//            if(JwtTool.check(tokenValue)){
                String  newTokenValue = JwtTool.sign(username);
                redisUtil.set(token,newTokenValue,EXPIRE_TIME/1000);
                return true;
//            }else{
//                return false;
//            }
        }else{
            return false;
        }
    }

    /**
     * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        log.info("~~~~~~~~~~~~权限认证方法开始：~~~~~~~~~~~~username:{}",sysUser.getUsername());
        Integer userId = sysUser.getId();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
         //设置该用户拥有的角色
        Set<String> roleSet = iSysRoleService.getUserRole(userId);
        info.setRoles(roleSet);
        Set<String> permissionSet = iSysPermissionService.getUserPermission(userId);
        info.setStringPermissions(permissionSet);
        log.info("~~~~~~~~~~~~权限认证方法完毕：~~~~~~~~~~~~username:{}",sysUser.getUsername());
        return info;
    }

}
