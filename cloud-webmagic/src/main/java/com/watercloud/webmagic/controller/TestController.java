package com.watercloud.webmagic.controller;


import com.watercloud.webmagic.common.aspect.annotation.AutoLogAnnotation;
import com.watercloud.webmagic.common.exception.CloudWebmagicException;
import com.watercloud.webmagic.common.util.CommonConstant;
import com.watercloud.webmagic.common.vo.Result;
import com.watercloud.webmagic.entity.Usertest;
import com.watercloud.webmagic.mapper.UsertestMapper;
import com.watercloud.webmagic.service.IUsertestService;
import com.watercloud.webmagic.spider.zh.ZHTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lly
 * @since 2021-09-13
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private IUsertestService iUsertestService;
    @Autowired
    private UsertestMapper usertestMapper;
    @Autowired
    ZHTask zhTask;

    @GetMapping("/get/{age}")
    @AutoLogAnnotation(code = "CW0001",logType = CommonConstant.LOG_TYPE_1)
    public Result<Usertest> get(@PathVariable int age){
        Usertest test = new Usertest();
        test.setId(23);
        test.setAge(age);
        test.setName("小明");
//        usertestMapper.insert(test);
        Usertest usertest = iUsertestService.getById(22);
        usertest.setAge(age);
        iUsertestService.updateById(usertest);
//        int i = 1/0;
//        usertestMapper.updateById(test);
        if(true){
            throw new CloudWebmagicException("ohhhhhhhhhhhhhhhhhhhhhhhhhhh!");
        }
        Result<Usertest> result = Result.OK(usertest);
        return result;
    }

    @GetMapping("del/{age}")
    public String del(@PathVariable int age){
//        Usertest test = new Usertest();
//        test.setAge(age);
//        QueryWrapper<Usertest> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("age",100);
//        iUsertestService.remove(queryWrapper);
        return "ok";
    }

    @GetMapping("/startTask")
    public String startTask(){
        zhTask.startTask();
        return "ok";
    }
}
