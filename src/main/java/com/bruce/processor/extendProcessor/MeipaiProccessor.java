package com.bruce.processor.extendProcessor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * 美拍模拟登陆
 *
 * @author Bruce_Q
 * @create 2017-02-27 23:14
 **/
public class MeipaiProccessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

    @Override
    public void process(Page page) {
        WebDriver driver = new ChromeDriver();
//        driver.get("http://www.meipai.com/");
        driver.get("http://weixin.sogou.com/weixin?type=2&s_from=input&query=%E6%88%91%E7%9A%84%E7%89%B9%E5%B7%A5%E7%88%B7%E7%88%B7&ie=utf8&_sug_=y&_sug_type_=&w=01019900&sut=25&sst0=1490428824764&lkt=1,1490428824661,1490428824661");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //找到登录按钮，点击
        driver.findElement(By.id("headerLogin")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("dialogLWrap")).findElement(By.id("dialogLPlatform")).findElement(By.tagName("a")).click();
        driver.findElement(By.id("userId")).sendKeys("805503895@qq.com");
        driver.findElement(By.id("passwd")).sendKeys("bruceq86706519");
        driver.findElement(By.xpath("//p[@class='oauth_formbtn']/a[@node-type='submit']")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//p[@class='oauth_formbtn']/a[@node-type='submit']")).click();

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        List<SpiderListener> spiderListeners = new ArrayList<>();
        SpiderListener spiderListener = new SpiderListener() {
            @Override
            public void onSuccess(Request request) {
                System.out.println("sucsess");
            }

            @Override
            public void onError(Request request) {

            }
        };
        spiderListeners.add(spiderListener);
        Spider.create(new MeipaiProccessor())
                .setSpiderListeners(spiderListeners)
//                .addUrl("http://www.meipai.com/")
                .addUrl("http://weixin.sogou.com/weixin?type=2&s_from=input&query=%E6%88%91%E7%9A%84%E7%89%B9%E5%B7%A5%E7%88%B7%E7%88%B7&ie=utf8&_sug_=y&_sug_type_=&w=01019900&sut=25&sst0=1490428824764&lkt=1,1490428824661,1490428824661")
                .thread(5)
                .start();
    }
}

