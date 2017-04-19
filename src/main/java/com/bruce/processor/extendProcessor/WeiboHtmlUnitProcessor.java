package com.bruce.processor.extendProcessor;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author Bruce_Q
 * @create 2017-04-06 16:18
 **/
public class WeiboHtmlUnitProcessor implements PageProcessor{
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2785.143 Safari/537.36");

    @Override
    public void process(Page page) {
        System.setProperty("webdriver.chrome.driver","/home/eaglestrike/下载/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://s.weibo.com/weibo/%25E7%2585%258E%25E9%25A5%25BC%25E4%25BE%25A0&Refer=focus_index");
        String a = "";
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new WeiboHtmlUnitProcessor())
                .addUrl("http://s.weibo.com/weibo/%25E7%2585%258E%25E9%25A5%25BC%25E4%25BE%25A0&Refer=focus_index")
                .start();
    }

}

