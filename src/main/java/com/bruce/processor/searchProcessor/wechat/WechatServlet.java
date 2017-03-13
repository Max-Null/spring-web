package com.bruce.processor.searchProcessor.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bruce.processor.searchProcessor.baidu.Baidu_new;
import com.bruce.utils.BaseServlet;
import com.qipachong.maxnull.model.Program;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.SpiderListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 微信
 *
 * @author Bruce_Q
 * @create 2017-03-07 21:50
 **/
public class WechatServlet extends BaseServlet {
    final Logger log = LoggerFactory.getLogger(WechatServlet.class);

    @Override
    public synchronized void process(JSONObject input, JSONObject output1) {
        log.info("input:{}", input.toJSONString());

        Baidu_new processor = new Baidu_new();
        //FIXME
        String url = "";
        JSONObject output = new JSONObject();
        Date sysDateStart = new Date();
        url = "http://weixin.sogou.com/weixin?type=2&query=极限特工&ie=utf8";
        Request request = new Request(url);
        List<SpiderListener> listeners = new ArrayList<>();
        listeners.add(new SpiderListener() {
            @Override
            public void onSuccess(Request request) {

            }

            @Override
            public void onError(Request request) {
                log.error("spider listener error,request={}", request.toString());
            }
        });
        Spider.create(processor)
                .thread(5)
                .setSpiderListeners(listeners)
                .addRequest(request)
                .run();

        List<Program> programList = processor.getProgramList();

        Date sysDateEnd = new Date();
        long time = sysDateEnd.getTime() - sysDateStart.getTime();
        log.info("----------------processing time:{}s", time / 1000);
        output.put("processing time", time / 1000 + "s");
        log.info("------------------------------------  crarwl over -------------------------------------------");

        output.put("data", JSON.toJSON(programList));

    }
}
