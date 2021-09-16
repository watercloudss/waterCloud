package com.watercloud.webmagic.spider.zh;

import com.watercloud.webmagic.entity.ZhSpiderinfo;
import com.watercloud.webmagic.service.IZhSpiderinfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

//一个业务处理类
@Component
@Slf4j
public class ZHPipeline implements Pipeline {
    @Autowired
    private IZhSpiderinfoService iZhSpiderinfoService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<ZhSpiderinfo> zhSpiderinfoList = resultItems.get("zhSpiderinfoList");
        if(!zhSpiderinfoList.isEmpty()){
           Boolean result = iZhSpiderinfoService.saveBatch(zhSpiderinfoList);
           if(result){
               log.info("爬取成功！");
           }else{
               log.info("爬取失败！");
           }
        }else{
            log.info("没有数据！");
        }
    }
}
