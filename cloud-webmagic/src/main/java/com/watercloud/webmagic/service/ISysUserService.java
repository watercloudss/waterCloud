package com.watercloud.webmagic.service;

import com.watercloud.webmagic.validator.annotation.Gender;
import com.watercloud.webmagic.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lly
 * @since 2021-09-14
 */
@Validated
public interface ISysUserService extends IService<SysUser> {
      public void test(@NotBlank(message = "参数不能为空！") String username, @NotBlank(message = "不能为空！") String pass, @Gender String gender);
}
