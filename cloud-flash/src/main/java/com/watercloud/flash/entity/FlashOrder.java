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
 * ç§’æ€è®¢å•è¡¨
 * </p>
 *
 * @author lly
 * @since 2021-12-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FlashOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ä¸»é”®
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * ç§’æ€å“ID
     */
    private Long itemId;

    /**
     * ç§’æ€æ´»åŠ¨ID
     */
    private Long activityId;

    /**
     * ç§’æ€å“åç§°æ ‡é¢˜
     */
    private String itemTitle;

    /**
     * ç§’æ€ä»·
     */
    private Long flashPrice;

    /**
     * æ•°é‡
     */
    private Integer quantity;

    /**
     * æ€»ä»·æ ¼
     */
    private Long totalAmount;

    /**
     * è®¢å•çŠ¶æ€
     */
    private Integer status;

    /**
     * ç”¨æˆ·ID
     */
    private Long userId;

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
