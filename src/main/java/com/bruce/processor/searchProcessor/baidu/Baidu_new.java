package com.bruce.processor.searchProcessor.baidu;

<<<<<<< HEAD
import com.bruce.processor.searchProcessor.FilterUtils;
import com.dao.model.Program;
=======
import com.qipachong.maxnull.model.Program;
>>>>>>> 4a4d8cf8f6ec4605472e949049abb645a5f7706e
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Bruce_Q
 * @create 2017-03-03 12:21
 **/
public class Baidu_new implements PageProcessor {

    protected Site site = Site.me().setCycleRetryTimes(3).addHeader("Accept-Encoding", "*").setTimeOut(60000).setSleepTime(1000)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");

    //保留信息集合
    List<Program> programList = new CopyOnWriteArrayList<>();

    public List<Program> getProgramList() {
        return programList;
    }

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        if (page.getRequest().getUrl().endsWith("&ie=UTF-8")) {
            String eqid = StringUtils.substringBetween(page.getHtml().toString(), "bds.comm.eqid = \"", "\";");
            List<String> url = html.xpath("//div[@class='c-container']/h3/a/@href").all();
            for (int i = 0; i < url.size(); i++) {
                String url_temp = url.get(i).replace("http", "https") + "&wd=&eqid=" + eqid;
                page.addTargetRequest(url_temp);
            }
        } else if (page.getRequest().getUrl().contains("&pn=")) {
            String eqid = StringUtils.substringBetween(page.getHtml().toString(), "bds.comm.eqid = \"", "\";");
            List<String> url = html.xpath("//div[@class='c-container']/h3/a/@href").all();
            for (int i = 0; i < url.size(); i++) {
                String url_temp = url.get(i).replace("http", "https") + "&wd=&eqid=" + eqid;
                page.addTargetRequest(url_temp);
            }
        } else if (page.getRequest().getUrl().startsWith("https://www.baidu.com/link")) {
            if (StringUtils.substringBetween(page.getHtml().toString(), "URL=\'", "\'") != null) {
                String url_real = StringUtils.substringBetween(page.getHtml().toString(), "URL=\'", "\'").replace("amp;", "");
                page.addTargetRequest(url_real);
            }
        } else {
            FilterUtils baiduFilterUtils = new FilterUtils();
            baiduFilterUtils.BaiduSearch(page,programList);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}

