package com.bruce.processor.searchProcessor.wechat;

import com.bruce.processor.searchProcessor.FilterUtils;
import com.qipachong.maxnull.model.Program;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 微信爬取
 *
 * @author Bruce_Q
 * @create 2017-03-07 21:53
 **/
public class Wechat_new implements PageProcessor {
    protected Site site = Site.me().setCycleRetryTimes(3).addHeader("Accept-Encoding", "*").setTimeOut(60000).setSleepTime(1000)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");

    //保留信息集合
    List<Program> programList = new CopyOnWriteArrayList<>();

    public List<Program> getProgramList() {
        return programList;
    }

    @Override
    public void process(Page page) {
        if (page.getRequest().getUrl().endsWith("ie=utf8")) {
//            Integer flag = (Integer) page.getRequest().getExtra("flag");
//            if (flag != null) {
//                if (flag > 1) {
//                    for (int i = 2; i <= flag; i++) {
//                        String pagination = page.getRequest().getUrl() + "&page=" + i;
//                        page.addTargetRequest(pagination);
//                    }
//                }
//            }
            List<String> url = page.getHtml().xpath("//div[@class='news-box']/ul[@class='news-list']/li/div[@class='txt-box']/h3/a/@data-url").all();
            for (int i = 0; i < url.size(); i++) {
                String url_temp = url.get(i);
                page.addTargetRequest(url_temp);
            }
        } else if (page.getRequest().getUrl().contains("&page=")) {
            List<String> url = page.getHtml().xpath("//div[@class='news-box']/ul[@class='news-list']/li/div[@class='txt-box']/h3/a/@data-url").all();
            for (int i = 0; i < url.size(); i++) {
                String url_temp = url.get(i);
                page.addTargetRequest(url_temp);
            }
        } else if (page.getRequest().getUrl().startsWith("http://mp.weixin.qq.com/s")) {
            FilterUtils wechatFilterUtils = new FilterUtils();
            wechatFilterUtils.BaiduSearch(page, programList);
        }
    }


    public static void main(String[] args) {
        Spider.create(new Wechat_new())
                .addUrl("http://weixin.sogou.com/weixin?query=极限特工&type=2&ie=utf8")
                .start();
    }


    @Override
    public Site getSite() {
        return site;
    }
}

