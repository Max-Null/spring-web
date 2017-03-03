package com.bruce.processor.searchProcessor.baidu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bruce.processor.utils.BaseServlet;
import com.dao.model.Program;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author Bruce_Q
 * @create 2017-03-03 12:22
 **/
public class BaiduServlet extends BaseServlet {
    final Logger log = LoggerFactory.getLogger(BaiduServlet.class);

    @Override
    public synchronized void process(JSONObject input, JSONObject output1) {
        log.info("input:{}", input.toJSONString());
        Deliver deliver = new Deliver();
        deliver.deliver(input);
        List<Program> programList_list = deliver.getProgramList_list();
        List<Program> programList_pass_list = deliver.getProgramList_pass_list();
        String receiveFlag = deliver.getReceiveFlag();
        String searchMethod = deliver.getSearchMethod();
        String version = deliver.getVersion();
        Integer flag = deliver.getFlag();
        JSONObject data_map = deliver.getData_map();
        JSONArray filterWords = deliver.getFilterWords();
        Integer workId = deliver.getWorkId();
        JSONArray commonKey = deliver.getCommonKey();
        String keyword = deliver.getKeyword();
        JSONArray keyWords = deliver.getKeyWords();
        String workName = deliver.getWorkName();
        Properties p = deliver.getP();

        //// FIXME: 2017/2/10
        Baidu baidu = new Baidu();
        baidu.Baidu(data_map, commonKey, keyword, keyWords, searchMethod, flag
                , workName, workId, filterWords, p, receiveFlag, version
                , programList_list, programList_pass_list);


    }
}