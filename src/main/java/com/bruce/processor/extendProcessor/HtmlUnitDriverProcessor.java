package com.bruce.processor.extendProcessor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * HtmlUnitDriver爬取模板，HtmlUnitDriver是不需弹出浏览器的模拟浏览器爬取方法
 *
 * @author Bruce_Q
 * @create 2017-03-05 23:08
 **/
public class HtmlUnitDriverProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");


    @Override
    public void process(Page page) {
        WebDriver dr = new HtmlUnitDriver();
        dr.get("http://www.baidu.com");
        WebElement element = dr.findElement(By.name("wd"));
        element.sendKeys("webdriver");
        element.submit();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("page title is:" + dr.getTitle());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new HtmlUnitDriverProcessor())
                .addUrl("http://www.baidu.com")
                .thread(5)
                .start();
    }
}
