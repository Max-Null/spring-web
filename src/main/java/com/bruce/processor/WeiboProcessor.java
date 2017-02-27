package com.bruce.processor;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Set;

/**
 * 微博登录
 *
 * @author Bruce_Q
 * @create 2017-02-27 22:39
 **/
public class WeiboProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

    @Override
    public void process(Page page) {
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
        driver.findElement(By.id("loginPassword")).sendKeys("bruceq86706519");

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
        Spider.create(new WeiboProcessor())
                .addUrl("https://passport.weibo.cn/signin/login?entry=mweibo&res=wel&wm=3349&r=http%3A%2F%2Fpad.weibo.cn%2F")
                .thread(5)
                .start();
    }
}
