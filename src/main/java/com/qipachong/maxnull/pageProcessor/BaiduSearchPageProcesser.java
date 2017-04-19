package com.qipachong.maxnull.pageProcessor;

import com.qipachong.maxnull.model.Program;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by MaxNull on 2017-3-10 0010.
 */
public class BaiduSearchPageProcesser implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setDomain("www.baidu.com").setRetryTimes(3).setSleepTime(1000);

    private Integer targetNumber = 0;//目标数量
    private Integer existingNumber = 0;//已获取数量
    private Integer pageNumber = 0;//页数

    //保留信息集合
    List<Program> programList = new CopyOnWriteArrayList<>();

    public List<Program> getProgramList() {
        return programList;
    }

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        String key = page.getHtml().$("#kw","value").toString();
        List<String> ids = page.getHtml().$("div.c-container","id").all();
        List<String> title = page.getHtml().$("div.c-container h3 a:first-child","InnerHtml").all();
        List<String> urls = page.getHtml().$("div.c-container h3 a:first-child","href").all();
        page.putField("page",page.getHtml().$("#page strong span.pc","text").toString());
        existingNumber += urls.size();
        Connection.Response res = null;
        for (int i = 0; i <urls.size() ; i++) {
            try {
                    res = Jsoup.connect(urls.get(i)).timeout(1000).method(Connection.Method.GET).followRedirects(false).execute();
                if (res.header("Location").contains("zhidao.baidu.com/link?")){
                    res = Jsoup.connect(res.header("Location")).timeout(1000).method(Connection.Method.GET).followRedirects(false).execute();
                }
                urls.remove(i);
                urls.add(i,res.header("Location"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (existingNumber < targetNumber) {
            pageNumber++;
            page.addTargetRequest("http://www.baidu.com/s?rn=50&wd="+key+"&ie=UTF-8&pn=" + pageNumber*50);
        }
        Program program;
        for (int i = 0; i <urls.size() ; i++) {
            program = new Program();
            program.setId(Long.parseLong(ids.get(i)));
            program.setKeyWords(key);
            program.setTitle(title.get(i));
            program.setPcUrl(urls.get(i));
            programList.add(program);
        }
    }

    public BaiduSearchPageProcesser(Integer targetNumber) {
        this.targetNumber = targetNumber;
    }

    public BaiduSearchPageProcesser() {
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args)throws Exception {
        Spider baiduSpider = Spider.create(new BaiduSearchPageProcesser()).addUrl("http://www.baidu.com/s?rn=50&wd=666&ie=UTF-8")
                .addPipeline(new ConsolePipeline());
        SpiderMonitor.instance().register(baiduSpider);
        baiduSpider.start();
    }
}