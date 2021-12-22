package com.watercloud.webmagic.service.impl;

import com.watercloud.webmagic.common.validator.annotation.Gender;
import com.watercloud.webmagic.entity.SysUser;
import com.watercloud.webmagic.mapper.SysUserMapper;
import com.watercloud.webmagic.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lly
 * @since 2021-09-14
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Override
    public void test(@NotBlank(message = "参数不能为空！") String username, @NotBlank(message = "不能为空！") String pass, @Gender String gender) {
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        System.out.println("ser----"+sysUser.getUsername());
        System.out.println(username+":"+pass);
    }
}
