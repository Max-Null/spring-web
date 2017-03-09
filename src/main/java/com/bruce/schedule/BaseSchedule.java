package com.bruce.schedule;

import com.bruce.processor.SpiderThread;
<<<<<<< HEAD
import com.bruce.processor.searchProcessor.baidu.Baidu_new;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
=======
>>>>>>> 4a4d8cf8f6ec4605472e949049abb645a5f7706e
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 爬虫调度类
 *
 * @author Bruce_Q
 * @create 2017-03-04 0:18
 **/
public class BaseSchedule {
    final Logger log = LoggerFactory.getLogger(BaseSchedule.class);
    public void startAll() {
    /*    String url = "http://www.baidu.com/s?wd=极限特工&ie=UTF-8";
        Request request = new Request(url);
        start_crawl(new Baidu_new(),request);*/
    }

    public void start_crawl(PageProcessor processor,Request request) {
        final Spider spider = Spider.create(processor)
                .thread(5)  //线程数写成配置文件形式
                .addRequest(request); //Request为爬取时接收的请求，从前台接收

        SpiderThread.create(spider).setSpiderListener(new SpiderThread.OnSpiderListener() {
            @Override
            public void onEnd() {
                log.info("=================== crawl over ====================");
            }
        }).start();
    }


}
