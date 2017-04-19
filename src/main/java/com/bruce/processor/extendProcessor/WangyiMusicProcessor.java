package com.bruce.processor.extendProcessor;

import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.IOException;

/**
 * 网易云音乐爬取
 *
 * @author Bruce_Q
 * @create 2017-03-31 17:30
 **/
public class WangyiMusicProcessor  implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000);
//            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

    @Override
    public void process(Page page) {
        site.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .addHeader("Accept-Encoding","gzip, deflate, sdch")
                .addHeader("Accept-Language","zh-CN,zh;q=0.8")
                .addHeader("Cache-Control","no-cache")
                .addHeader("Cookie","_ntes_nuid=8747cadf4c54be4cbcdc1fbf7e8912b9; usertrack=ZUcIhVjZs38EYVxcBX8GAg==; Province=010; City=010; _ntes_nnid=6061ab6cb07b56d64d7e726a2f27c744,1490662273538; _ga=GA1.2.2123968286.1490662277; JSESSIONID-WYYY=Exr%5Chq82UY%2BpbVNeppbPmtP%5Cvh7kUFqexFmVXlDxwmpkRRIhcoRiErDXxj7o4JRpE%2BQGhfO2TfZo83pJeVeiwzjUIpWUsNU0cRpKvhCoXVUIpF%2FT%2B3Bv4ms6kBJ7Dsr2VJ8UKwx3fWypQbvBoCSOJZN5T0DUA2v0P9dkpsv7ZEEqpria%3A1491020900435; _iuqxldmzr_=32; __utma=94650624.2123968286.1490662277.1490960697.1491019101.9; __utmb=94650624.5.10.1491019101; __utmc=94650624; __utmz=94650624.1491019101.9.5.utmcsr=baidu|utmccn=(organic)|utmcmd=organic")
                .addHeader("Host","music.163.com")
                .addHeader("Pragma","no-cache")
                .addHeader("Proxy-Connection","Proxy-Connection")
                .addHeader("Referer","http://music.163.com/")
                .addHeader("Upgrade-Insecure-Requests","1")
                .addHeader("User-Agent","spider");
        WebDriver driver = new ChromeDriver();
        driver.get("http://music.163.com/#/playlist?id=646881111");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement webElement = driver.findElement(By.className("g-wrap3"));

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws IOException {
//        Spider.create(new WangyiMusicProcessor())
//                .addUrl("http://music.163.com/#/playlist?id=646881111")
//                .start();
        Jsoup.connect("http://music.163.com/playlist?id=317113395")
                .header("Referer", "http://music.163.com/")
                .header("Host", "music.163.com").get().select("ul[class=f-hide] a")
                .stream().map(w-> w.text() + "-->" + w.attr("href"))
                .forEach(System.out::println);
        String a = "";
    }
}
