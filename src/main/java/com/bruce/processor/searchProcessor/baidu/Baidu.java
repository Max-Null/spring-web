package com.bruce.processor.searchProcessor.baidu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bruce.processor.utils.HttpUtil;
import com.bruce.processor.utils.HttpsClientDownloaderBaidu;
import com.dao.model.Program;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.SpiderListener;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 百度
 *
 * @author Bruce_Q
 * @create 2017-02-27 22:39
 **/
public class Baidu {
    final Logger log = LoggerFactory
            .getLogger(Baidu.class);

    public void Baidu(JSONObject data_map, JSONArray commonKey, String keyword, JSONArray keyWords, String searchMethod, Integer flag
            , String workName, Integer workId, JSONArray filterWords, Properties p, String receiveFlag, String version
            , List<Program> programList_list, List<Program> programList_pass_list) {
        Baidu_new processor = new Baidu_new();
        //FIXME
        String url = "";
        if (data_map.get("commonKey") != null && !"[]".equals(data_map.get("commonKey").toString())) {
            for (int i = 0; i < commonKey.size(); i++) {
                JSONObject output = new JSONObject();
                Date sysDateStart = new Date();
                String common = (String) commonKey.get(i);
                url = "http://www.baidu.com/s?wd=" + keyword.replace(" ", "%20") + "" + common.replace(" ", "%20") + "&ie=UTF-8";
                Request request = new Request(url);
                request.putExtra("name", keyWords);
                request.putExtra("common", common);
                request.putExtra("searchMethod", searchMethod);
                request.putExtra("flag", flag);
                request.putExtra("workName", workName);
                request.putExtra("workId", workId);
                request.putExtra("filterWords", filterWords);
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
//                        .setDownloader(new HttpsClientDownloaderBaidu())
                        .addRequest(request)
                        .run();

                List<Program> programList = processor.getProgramList();
                List<Program> programList_pass = processor.getProgramList_pass();


                Date sysDateEnd = new Date();
                long time = sysDateEnd.getTime() - sysDateStart.getTime();
                log.info("----------------processing time:{}s", time / 1000);
                output.put("processing time", time / 1000 + "s");
                log.info("------------------------------------  crarwl over -------------------------------------------");

                output.put("data", JSON.toJSON(programList));
                output.put("data_containPass", JSON.toJSON(programList_pass));
                if (receiveFlag.equals("key")) {
                    output.put("method", "insertCrawlerDataByKeyWords");
                }
                if (receiveFlag.equals("tort")) {
                    output.put("method", "insertCrawlerDataByTort");
                }
                if (receiveFlag.equals("director")) {
                    output.put("method", "insertCrawlerDataByDirector");
                }
                output.put("version", version);
                output.put("workId", workId.toString());
                if (i == commonKey.size() - 1) {
                    output.put("isFinish", "true");
                } else {
                    output.put("isFinish", "false");
                }

                String out = output.toString();
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json;charset=UTF-8");
                HttpUtil.post(p.get("url").toString(), headers, out);
                processor.setProgramList(new CopyOnWriteArrayList());
                processor.setProgramList_pass(new CopyOnWriteArrayList());
                processor.setProgramList_websites(new CopyOnWriteArrayList());
                processor.setProgramList_download(new CopyOnWriteArrayList());
                log.info("------------------------------------  send over  -------------------------------------------");

            }
        } else {
            JSONObject output = new JSONObject();
            Date sysDateStart = new Date();
            url = "http://www.baidu.com/s?wd=" + keyword.replace(" ", "%20") + "&ie=UTF-8";

            url = url.replace(" ", "%20");
            Request request = new Request(url);
            request.putExtra("name", keyWords);
            request.putExtra("searchMethod", searchMethod);
            request.putExtra("flag", flag);
            request.putExtra("workName", workName);
            request.putExtra("workId", workId);
            request.putExtra("filterWords", filterWords);
            Spider.create(processor)
                    .thread(5)
//                    .setDownloader(new HttpsClientDownloaderBaidu())
                    .addRequest(request)
                    .run();

            List<Program> programList = processor.getProgramList();
            List<Program> programList_pass = processor.getProgramList_pass();


            Date sysDateEnd = new Date();
            long time = sysDateEnd.getTime() - sysDateStart.getTime();
            log.info("----------------processing time:{}s", time / 1000);
            output.put("processing time", time / 1000 + "s");
            log.info("------------------------------------  crarwl over  -------------------------------------------");

            output.put("data", JSON.toJSON(programList));
            output.put("data_containPass", JSON.toJSON(programList_pass));
            if (receiveFlag.equals("key")) {
                output.put("method", "insertCrawlerDataByKeyWords");
            }
            if (receiveFlag.equals("tort")) {
                output.put("method", "insertCrawlerDataByTort");
            }
            if (receiveFlag.equals("director")) {
                output.put("method", "insertCrawlerDataByDirector");
            }
            output.put("version", version);
            output.put("workId", workId.toString());
            output.put("isFinish", "true");

            String out = output.toString();
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json;charset=UTF-8");
            HttpUtil.post(p.get("url").toString(), headers, out);
            processor.setProgramList(new CopyOnWriteArrayList());
            processor.setProgramList_pass(new CopyOnWriteArrayList());
            processor.setProgramList_websites(new CopyOnWriteArrayList());
            processor.setProgramList_download(new CopyOnWriteArrayList());
            log.info("------------------------------------  send over  -------------------------------------------");

        }
    }
}

