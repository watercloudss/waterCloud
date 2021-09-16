package com.watercloud.webmagic.spider.zh;

import com.watercloud.webmagic.entity.ZhSpiderinfo;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

//对下载的数据进行处理的类
@Component
public class ZHPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        List<Selectable> nodes = page.getHtml().xpath("//div[@class='SpecialListCard SpecialListPage-specialCard']").nodes();
        if(!nodes.isEmpty()){
            List<ZhSpiderinfo> zhSpiderinfoList = new ArrayList<>();
            for(Selectable selectable:nodes){
                String title = selectable.xpath("//a[@class='SpecialListCard-title']/text()").toString();
                String link = selectable.xpath("//a[@class='SpecialListCard-title']/@href").toString();
                String content = selectable.xpath("//a[@class='SpecialListCard-intro']/text()").toString();
                String image = selectable.xpath("//img/@src").toString();
                ZhSpiderinfo zhSpiderinfo  = new ZhSpiderinfo();
                zhSpiderinfo.setTitle(title);
                zhSpiderinfo.setLink(link);
                zhSpiderinfo.setContent(content);
                zhSpiderinfo.setImage(image);
                zhSpiderinfoList.add(zhSpiderinfo);
            }
            page.putField("zhSpiderinfoList",zhSpiderinfoList);
        }
    }
    @Override
    public Site getSite() {
        return site;
    }
}
