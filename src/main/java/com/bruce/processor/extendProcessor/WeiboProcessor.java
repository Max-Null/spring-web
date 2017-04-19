package com.bruce.processor.extendProcessor;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 微博登录
 *
 * @author Bruce_Q
 * @create 2017-02-27 22:39
 **/
public class WeiboProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2785.143 Safari/537.36");

    @Override
    public void process(Page page) {
        System.setProperty("webdriver.chrome.driver", "G:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://passport.weibo.cn/signin/login?entry=mweibo&res=wel&wm=3349&r=http%3A%2F%2Fpad.weibo.cn%2F");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //找到名为"loginName"的元素，填写帐号
        driver.findElement(By.id("loginName")).clear();
        driver.findElement(By.id("loginName")).sendKeys("805503895@qq.com");

        //找到名为"loginPassword"的元素，填写密码
        driver.findElement(By.id("loginPassword")).clear();
        driver.findElement(By.id("loginPassword")).sendKeys("qixinq86706519");

        //找到登录按钮，点击
        driver.findElement(By.id("loginAction")).click();
        //因为网站不一定可以马上打开，让进程停一下，否则页面还没有加载出来就进行下一步了。
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //获取cookies

        Set<Cookie> cookies = driver.manage().getCookies();
        String cookieStr = "";
        for (Cookie cookie : cookies) {
            cookieStr += cookie.getName() + "=" + cookie.getValue() + "; ";
        }

        System.out.println(cookieStr);
        String a ="";

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        List<SpiderListener> spiderListeners = new ArrayList<>();
        SpiderListener spiderListener =new SpiderListener() {
            @Override
            public void onSuccess(Request request) {
                System.out.println("sucsess");
            }

            @Override
            public void onError(Request request) {

            }
        };
        spiderListeners.add(spiderListener);
        Spider.create(new WeiboProcessor())
                .setSpiderListeners(spiderListeners)
                .addUrl("https://passport.weibo.cn/signin/login?entry=mweibo&res=wel&wm=3349&r=http%3A%2F%2Fpad.weibo.cn%2F")
                .thread(5)
                .start();
    }
}
