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
 * ç§’æ€å“
 * </p>
 *
 * @author lly
 * @since 2021-12-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FlashItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ä¸»é”®
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * ç§’æ€å“åç§°æ ‡é¢˜
     */
    private String itemTitle;

    /**
     * ç§’æ€å“å‰¯æ ‡é¢˜
     */
    private String itemSubTitle;

    /**
     * ç§’æ€å“ä»‹ç»å¯Œæ–‡æœ¬æ–‡æ¡ˆ
     */
    private String itemDesc;

    /**
     * ç§’æ€å“åˆå§‹åº“å­˜
     */
    private Integer initialStock;

    /**
     * ç§’æ€å“å¯ç”¨åº“å­˜
     */
    private Integer availableStock;

    /**
     * ç§’æ€å“åº“å­˜æ˜¯å¦å·²ç»é¢„çƒ­
     */
    private Integer stockWarmUp;

    /**
     * ç§’æ€å“åŽŸä»·
     */
    private Long originalPrice;

    /**
     * ç§’æ€ä»·
     */
    private Long flashPrice;

    /**
     * ç§’æ€å¼€å§‹æ—¶é—´
     */
    private LocalDateTime startTime;

    /**
     * ç§’æ€ç»“æŸæ—¶é—´
     */
    private LocalDateTime endTime;

    /**
     * ç§’æ€å¯é…è§„åˆ™ï¼ŒJSONæ ¼å¼
     */
    private String rules;

    /**
     * ç§’æ€å“çŠ¶æ€
     */
    private Integer status;

    /**
     * æ‰€å±žæ´»åŠ¨id
     */
    private Long activityId;

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
