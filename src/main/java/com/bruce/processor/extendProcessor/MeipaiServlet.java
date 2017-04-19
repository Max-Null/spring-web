package com.bruce.processor.extendProcessor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bruce.processor.searchProcessor.baidu.BaiduServlet;
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
 * @author Bruce_Q
 * @create 2017-04-06 10:41
 **/
public class MeipaiServlet extends BaseServlet {
    final Logger log = LoggerFactory.getLogger(BaiduServlet.class);

    @Override
    public synchronized void process(JSONObject input, JSONObject output1) {
        log.info("input:{}", input.toJSONString());
        //// FIXME: 2017/2/10
        MeipaiProccessor processor = new MeipaiProccessor();
        //FIXME
        String url = "";
        JSONObject output = new JSONObject();
        Date sysDateStart = new Date();
        url = "http://weixin.sogou.com/weixin?type=2&s_from=input&query=%E6%88%91%E7%9A%84%E7%89%B9%E5%B7%A5%E7%88%B7%E7%88%B7&ie=utf8&_sug_=y&_sug_type_=&w=01019900&sut=25&sst0=1490428824764&lkt=1,1490428824661,1490428824661";
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
                .thread(1)
                .setSpiderListeners(listeners)
                .addRequest(request)
                .run();


        Date sysDateEnd = new Date();
        long time = sysDateEnd.getTime() - sysDateStart.getTime();
        log.info("----------------processing time:{}s", time / 1000);
        output.put("processing time", time / 1000 + "s");
        log.info("------------------------------------  crarwl over -------------------------------------------");



    }
}
