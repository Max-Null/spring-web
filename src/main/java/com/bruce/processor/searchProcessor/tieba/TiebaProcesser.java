package com.bruce.processor.searchProcessor.tieba;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * 百度贴吧爬取（注解形式）
 *
 * @author Bruce_Q
 * @create 2017-03-19 16:18
 **/

@TargetUrl("http://tieba.baidu.com/p/*")
@HelpUrl("http://tieba.baidu.com/f/search/*")
public class TiebaProcesser{
    @ExtractBy(value = "//h1/text()", notNull = true)
    private String name;

//    @ExtractByUrl("http://tieba.baidu.com/p/*")
//    private List<String> url;

    @ExtractBy("//div[@id='readme']/tidyText()")
    private String readme;

    public static void main(String[] args) {
        OOSpider.create(Site.me(),new ConsolePageModelPipeline(),TiebaProcesser.class)
                .addUrl("http://tieba.baidu.com/f/search/res?isnew=1&kw=&qw=%BC%AB%CF%DE%CC%D8%B9%A4&un=&rn=10&pn=0&sd=&ed=&sm=1&only_thread=1")
                .thread(5).run();
    }
}
