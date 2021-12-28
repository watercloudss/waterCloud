package com.watercloud.flash.common;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class FlashBlockHandler {
    public static String flashHandlerException(BlockException exception){
        return "被限流了！";
    }
}
