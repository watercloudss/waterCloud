package com.watercloud.webmagic.common.shiro;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.watercloud.webmagic.common.jwt.JwtTool;
import com.watercloud.webmagic.entity.SysRole;
import com.watercloud.webmagic.entity.SysUser;
import com.watercloud.webmagic.service.ISysPermissionService;
import com.watercloud.webmagic.service.ISysRolePermissionService;
import com.watercloud.webmagic.service.ISysRoleService;
import com.watercloud.webmagic.service.ISysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private ISysUserService iSysUserService;
    @Autowired
    private ISysRoleService iSysRoleService;
    @Autowired
    private ISysPermissionService iSysPermissionService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof ShiroJWTToken;
    }
    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        String token = (String)authenticationToken.getCredentials();
        JwtTool.check(token);
        String username = JwtTool.getTokenUsername(token);
        if(StrUtil.isBlank(username)){
            throw new AuthenticationException("token有错误！");
        }
        // 从数据库获取对应用户名密码的用户
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        SysUser sysUser = iSysUserService.getOne(queryWrapper);
        if (null == sysUser||sysUser.getStatus().equals("2")) {
            throw new AuthenticationException("token不正确的！");
        }
        return new SimpleAuthenticationInfo(sysUser, token, "MyRealm");
    }

    /**
     * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("————权限认证————");
        System.out.println(principalCollection);
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        Integer userId = sysUser.getId();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
         //设置该用户拥有的角色
        Set<String> roleSet = iSysRoleService.getUserRole(userId);
        info.setRoles(roleSet);
        Set<String> permissionSet = iSysPermissionService.getUserPermission(userId);
        info.setStringPermissions(permissionSet);
        return info;
    }
}