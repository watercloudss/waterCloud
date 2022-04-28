package com.watercloud.webmagic.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author lly
 * @since 2021-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * md5密码盐
     */
    private String salt;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 生日
     */
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime birthday;

    /**
     * 性别(0-默认未知,1-男,2-女)
     */
    private Boolean sex;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 机构编码
     */
    private String orgCode;

    /**
     * 性别(1-正常,2-冻结)
     */
    private Boolean status;

    /**
     * 删除状态(0-正常,1-已删除)
     */
    private Boolean delFlag;

    /**
     * 第三方登录的唯一标识
     */
    private String thirdId;

    /**
     * 第三方类型
     */
    private String thirdType;

    /**
     * 同步工作流引擎(1-同步,0-不同步)
     */
    private Boolean activitiSync;

    /**
     * 工号，唯一键
     */
    private String workNo;

    /**
     * 职务，关联职务表
     */
    private String post;

    /**
     * 座机号
     */
    private String telephone;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 身份（1普通成员 2上级）
     */
    private Boolean userIdentity;

    /**
     * 负责部门
     */
    private String departIds;

    /**
     * 多租户标识
     */
    private String relTenantIds;

    /**
     * 设备ID
     */
    private String clientId;

    /**
     * 个人描述
     */
    private String introduction;
}
