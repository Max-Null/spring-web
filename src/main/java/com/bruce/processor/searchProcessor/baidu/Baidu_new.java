package com.bruce.processor.searchProcessor.baidu;

import com.alibaba.fastjson.JSONArray;
import com.dao.model.Program;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
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

    public void setProgramList(List<Program> programList) {
        this.programList = programList;
    }

    //包含播放页信息集合
    List<Program> programList_websites = new CopyOnWriteArrayList<>();

    public List<Program> getProgramList_websites() {
        return programList_websites;
    }

    public void setProgramList_websites(List<Program> programList_websites) {
        this.programList_websites = programList_websites;
    }

    //包含下载信息集合
    List<Program> programList_download = new CopyOnWriteArrayList<>();

    public List<Program> getProgramList_download() {
        return programList_download;
    }

    public void setProgramList_download(List<Program> programList_download) {
        this.programList_download = programList_download;
    }

    //被过滤掉信息集合
    List<Program> programList_pass = new CopyOnWriteArrayList<>();

    public List<Program> getProgramList_pass() {
        return programList_pass;
    }

    public void setProgramList_pass(List<Program> programList_pass) {
        this.programList_pass = programList_pass;
    }

    List name = new ArrayList();
    static String searchMethod = "";
    static String common = "";
    static String workName = "";
    static Integer workId = new Integer(0);
    static List filterWords = new JSONArray();

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        if (page.getRequest().getUrl().endsWith("&ie=UTF-8")) {
            searchMethod = (String) page.getRequest().getExtra("searchMethod");
            name = (List) page.getRequest().getExtra("name");
            common = (String) page.getRequest().getExtra("common");
            workName = (String) page.getRequest().getExtra("workName");
            workId = (Integer) page.getRequest().getExtra("workId");
            filterWords = (List) page.getRequest().getExtra("filterWords");
            Integer flag = (Integer) page.getRequest().getExtra("flag");
            if (flag != null) {
                for (int i = 1; i < flag; i++) {
                    String pagination = page.getRequest().getUrl().replace("&ie=UTF-8", "& =UTF-8&pn=" + i + "0");
                    page.addTargetRequest(pagination);
                }
            }
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
            FilterUtilsBaidu baiduFilterUtils = new FilterUtilsBaidu();
            baiduFilterUtils.BaiduPassKeyWord(page, name, programList, searchMethod, common, workName, filterWords);
            baiduFilterUtils.BaiduContainsPass(page, name, programList_pass, searchMethod, common, workName, filterWords);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}

