package com.watercloud.webmagic.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.watercloud.webmagic.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        String username = null;
        if(SecurityUtils.getSubject().getPrincipal()!=null){
            SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
            username = sysUser.getUsername();
        }
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("createBy", username, metaObject);
        this.setFieldValByName("updateBy", username, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        String username = null;
        if(SecurityUtils.getSubject().getPrincipal()!=null){
            SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
            username = sysUser.getUsername();
        }
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateBy", username, metaObject);
    }


}
