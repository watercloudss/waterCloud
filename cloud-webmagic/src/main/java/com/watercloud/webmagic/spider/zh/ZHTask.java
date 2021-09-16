package com.watercloud.webmagic.spider.zh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

@Component
public class ZHTask {
    @Autowired
    private ZHPageProcessor zhPageProcessor;
    @Autowired
    private ZHPipeline zhPipeline;

    public void startTask(){
        Spider.create(zhPageProcessor)
                // 从https://www.xxx.com/explore开始抓
                .addUrl("https://www.zhihu.com/special/all")
                // 抓取到的数据存数据库
                .addPipeline(zhPipeline)
                // 开启2个线程抓取
                .thread(1)
                // 异步启动爬虫
                .start();
    }
}
