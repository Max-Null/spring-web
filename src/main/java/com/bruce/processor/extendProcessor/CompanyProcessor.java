package com.bruce.processor.extendProcessor;

import com.bruce.utils.HttpUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * webmagic selenium 爬取动态页面
 *
 * @author Bruce_Q
 * @create 2017-02-26 23:33
 **/
public class CompanyProcessor implements PageProcessor {


    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");


    public void process(Page page) {
        System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.sse.com.cn/assortment/stock/list/info/company/index.shtml?COMPANY_CODE=600000");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement webElement = driver.findElement(By.id("tableData_stockListCompany"));
//        WebElement webElement = driver.findElement(By.xpath("//div[@class='table-responsive sse_table_T05']"));
        String str = webElement.getAttribute("outerHTML");
        System.out.println(str);

        Html html = new Html(str);
        System.out.println(html.xpath("//tbody/tr").all());
        String companyCode = html.xpath("//tbody/tr[1]/td/text()").get();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = html.xpath("//tbody/tr[3]/td/text()").get().split("/")[0];

        String stockCode = html.xpath("//tbody/tr[2]/td/text()").get().split("/")[0];
        String name = html.xpath("//tbody/tr[5]/td/text()").get().split("/")[0];
        String department = html.xpath("//tbody/tr[14]/td/text()").get().split("/")[0];
        System.out.println(companyCode);
        System.out.println(stockCode);
        System.out.println(name);
        System.out.println(department);
        driver.close();

    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new CompanyProcessor())
                .addUrl("http://www.sse.com.cn/assortment/stock/list/info/company/index.shtml?COMPANY_CODE=600000")
                .thread(5)
                .run();
//        Map<String,String> hearders = new HashMap<>();
//        hearders.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//        hearders.put("Accept-Encoding","gzip, deflate, sdch");
//        hearders.put("Accept-Language","zh-CN,zh;q=0.8");
//        hearders.put("Cache-Control","no-cache");
//        hearders.put("Cookie","csrftoken=WFY18tvlL5BXoaAhBBJphfcybj3xibiM; Hm_lvt_1959ccfcbdb63252c5058e9726b57973=1490703646,1490961529; Hm_lpvt_1959ccfcbdb63252c5058e9726b57973=1490962354; sessionid=as3ws2x1pjenqd2xc58y24k2ueop6omn");
//        hearders.put("Host","www.videobang.cn");
//        hearders.put("Pragma","no-cache");
//        hearders.put("Proxy-Connection","keep-alive");
//        hearders.put("Upgrade-Insecure-Requests","1");
//        hearders.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36");
//        HttpUtil.post("http://www.videobang.cn/miaopai/captcha/?phone=18638598651",hearders,"");
//        String a = "";
    }
}