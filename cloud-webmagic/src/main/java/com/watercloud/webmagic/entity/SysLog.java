package com.watercloud.webmagic.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统日志表
 * </p>
 *
 * @author lly
 * @since 2021-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 日志类型（1登录日志，2操作日志）
     */
    private Integer logType;

    /**
     * 日志内容
     */
    private String methodName;

    /**
     * IP
     */
    private String ip;

    /**
     * 请求java方法
     */
    private String methodClass;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 请求类型
     */
    private String responseParam;

    /**
     * 耗时
     */
    private Long costTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private String requestStatus;


}
