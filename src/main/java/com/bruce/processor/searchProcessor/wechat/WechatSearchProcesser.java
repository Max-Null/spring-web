package com.bruce.processor.searchProcessor.wechat;

import com.bruce.processor.searchProcessor.FilterUtils;
import com.qipachong.maxnull.model.Program;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 微信内容爬取
 *
 * @author Bruce_Q
 * @create 2017-03-14 16:06
 **/
public class WechatSearchProcesser implements PageProcessor {
    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    private Integer targetNumber = 0;//目标数量
    private Integer existingNumber = 0;//已获取数量
    private Integer pageNumber = 0;//页数

    //保留信息集合
    List<Program> programList = new CopyOnWriteArrayList<>();

    public List<Program> getProgramList() {
        return programList;
    }

    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    @Override
    public synchronized void process(Page page) {
//        if (page.getRequest().getUrl().endsWith("ie=utf8")) {
////            Integer flag = (Integer) page.getRequest().getExtra("flag");
////            if (flag != null) {
////                if (flag > 1) {
////                    for (int i = 2; i <= flag; i++) {
////                        String pagination = page.getRequest().getUrl() + "&page=" + i;
////                        page.addTargetRequest(pagination);
////                    }
////                }
////            }
//            List<String> url = page.getHtml().xpath("//div[@class='news-box']/ul[@class='news-list']/li/div[@class='txt-box']/h3/a/@data-url").all();
//            for (int i = 0; i < url.size(); i++) {
//                String url_temp = url.get(i);
//                page.addTargetRequest(url_temp);
//            }
//        } else if (page.getRequest().getUrl().contains("&page=")) {
//            List<String> url = page.getHtml().xpath("//div[@class='news-box']/ul[@class='news-list']/li/div[@class='txt-box']/h3/a/@data-url").all();
//            for (int i = 0; i < url.size(); i++) {
//                String url_temp = url.get(i);
//                page.addTargetRequest(url_temp);
//            }
//        } else if (page.getRequest().getUrl().startsWith("http://mp.weixin.qq.com/s")) {
//            FilterUtils wechatFilterUtils = new FilterUtils();
//            wechatFilterUtils.BaiduSearch(page, programList);
//        }
        String key = page.getHtml().$("#query", "value").toString();
        List<String> title = page.getHtml().$("div.c-container h3 a:first-child","InnerHtml").all();
        List<String> urls = page.getHtml().$("div.c-container h3 a:first-child","href").all();
        if (existingNumber < targetNumber) {
            pageNumber++;
            page.addTargetRequest("http://weixin.sogou.com/weixin?query=" + key + "&ie=UTF-8&page=" + pageNumber);
        }
    }

    public WechatSearchProcesser(Integer targetNumber) {
        this.targetNumber = targetNumber;
    }

    public WechatSearchProcesser() {
    }

    @Override
    public Site getSite() {
        return site;
    }
}
