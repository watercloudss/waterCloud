package com.watercloud.flash.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * ç§’æ€æ´»åŠ¨è¡¨
 * </p>
 *
 * @author lly
 * @since 2021-12-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FlashActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ä¸»é”®
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * ç§’æ€æ´»åŠ¨åç§°
     */
    private String activityName;

    /**
     * ç§’æ€æ´»åŠ¨æè¿°
     */
    private String activityDesc;

    /**
     * ç§’æ€æ´»åŠ¨å¼€å§‹æ—¶é—´
     */
    private LocalDateTime startTime;

    /**
     * ç§’æ€æ´»åŠ¨ç»“æŸæ—¶é—´
     */
    private LocalDateTime endTime;

    /**
     * ç§’æ€æ´»åŠ¨çŠ¶æ€
     */
    private Integer status;

    /**
     * æ›´æ–°æ—¶é—´
     */
    private LocalDateTime modifiedTime;

    /**
     * åˆ›å»ºæ—¶é—´
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
