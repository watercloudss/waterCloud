package com.watercloud.webmagic.controller;

import com.cloudwater.common.commonVo.Result;
import com.watercloud.webmagic.common.util.server.Server;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys-server")
public class SysServerController {

    @GetMapping("/server")
    @RequiresPermissions("system:server:list")
    public Result getInfo() throws Exception{
        Server server = new Server();
        server.copyTo();
        return Result.ok(server);
    }

}
