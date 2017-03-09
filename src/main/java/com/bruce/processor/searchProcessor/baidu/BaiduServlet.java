package com.bruce.processor.searchProcessor.baidu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
 * @author Bruce_Q
 * @create 2017-03-03 12:22
 **/
public class BaiduServlet extends BaseServlet {
    final Logger log = LoggerFactory.getLogger(BaiduServlet.class);

    @Override
    public synchronized void process(JSONObject input, JSONObject output1) {
        log.info("input:{}", input.toJSONString());
        //// FIXME: 2017/2/10
        Baidu_new processor = new Baidu_new();
        //FIXME
        String url = "";
        JSONObject output = new JSONObject();
        Date sysDateStart = new Date();
        url = "http://www.baidu.com/s?wd=极限特工&ie=UTF-8";
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