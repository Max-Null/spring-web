package com.bruce.processor;

import us.codecraft.webmagic.Spider;

/**
 * 爬虫监听
 *
 * @author Bruce_Q
 * @create 2017-02-27 23:06
 **/

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃  神兽保佑
 * 　　　　┃　　　┃  代码无bug　　
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 */
public class SpiderThread extends Thread{
    private Spider spider;

    private SpiderThread(Spider spider) {
        this.spider = spider;
    }

    public static SpiderThread create(Spider spider) {
        return new SpiderThread(spider);
    }

    OnSpiderListener spiderListener;

    public SpiderThread setSpiderListener(OnSpiderListener spiderListener) {
        this.spiderListener = spiderListener;
        return this;
    }

    @Override
    public void run() {
        if (spider != null) {
            spider.run();
        }
        if (spiderListener != null) {
            spiderListener.onEnd();
        }

    }

    /**
     * spider线程结束监听
     */
    public interface OnSpiderListener {
        public void onEnd();
    }
}
