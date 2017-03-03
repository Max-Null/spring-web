package com.bruce.processor.searchProcessor.baidu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dao.model.Program;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Bruce_Q
 * @create 2017-03-03 13:50
 **/
public class Deliver {
    //保留网站列表
    private List<Program> programList_list;

    //过滤网站列表
    private List<Program> programList_pass_list;

    //包含播放页网站列表
    private List<Program> programList_websites_list;

    //包含下载链接网站列表
    private List<Program> programList_download_list;

    //接收标志
    private String receiveFlag;

    //搜索方式
    private String searchMethod;

    //版本号
    private String version;

    //搜索页数
    private Integer flag;

    //json数据
    private JSONObject data_map;

    //过滤词
    private JSONArray filterWords;

    //作品id
    private Integer workId;

    //关键词（多个）
    private JSONArray commonKey;

    //传入关键词
    private String keyword;

    //参数配置文件
    private Properties p;

    //作品名
    private String workName;

    //关键词
    private JSONArray keyWords;


    public List<Program> getProgramList_websites_list() {
        return programList_websites_list;
    }

    public List<Program> getProgramList_download_list() {
        return programList_download_list;
    }

    public JSONArray getKeyWords() {
        return keyWords;
    }

    public List<Program> getProgramList_list() {
        return programList_list;
    }

    public List<Program> getProgramList_pass_list() {
        return programList_pass_list;
    }

    public String getReceiveFlag() {
        return receiveFlag;
    }

    public String getSearchMethod() {
        return searchMethod;
    }

    public String getVersion() {
        return version;
    }

    public Integer getFlag() {
        return flag;
    }

    public JSONObject getData_map() {
        return data_map;
    }

    public JSONArray getFilterWords() {
        return filterWords;
    }

    public Integer getWorkId() {
        return workId;
    }

    public JSONArray getCommonKey() {
        return commonKey;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Properties getP() {
        return p;
    }

    public void setP(Properties p) {
        this.p = p;
    }

    public String getWorkName() {
        return workName;
    }


    /**
     * 解析Json请求，并给爬虫传值
     *
     * @param input
     */
    public void deliver(JSONObject input) {
        programList_list = new ArrayList<>();
        programList_pass_list = new ArrayList<>();
        programList_websites_list = new ArrayList<>();
        programList_download_list = new ArrayList<>();
        receiveFlag = input.get("receiveFlag").toString();
        searchMethod = input.get("searchMethod").toString();
        version = input.get("version").toString();
        flag = Integer.valueOf(input.get("flag").toString());
        List data = (List) input.get("data");
        for (int j = 0; j < data.size(); j++) {
            data_map = (JSONObject) data.get(j);
            //需要特殊过滤的关键词
            filterWords = (JSONArray) data_map.get("filterWords");
            //搜索关键词（多个）
            keyWords = (JSONArray) data_map.get("keyWords");
            //作品名
            workName = (String) data_map.get("workName");
            //任务id
            workId = (Integer) data_map.get("workId");
            //关键字（需遍历，每次只加一个）
            commonKey = new JSONArray();
            if (data_map.get("commonKey") != null) {
                commonKey = (JSONArray) data_map.get("commonKey");
            }
            String key = "";
            for (int i = 0; i < keyWords.size(); i++) {
                key = key + "%20" + keyWords.getString(i) + "%20";
            }
            keyword = workName + key;
        }
    }

    @Override
    public String toString() {
        return "Deliver{" +
                "programList_list=" + programList_list +
                ", programList_pass_list=" + programList_pass_list +
                ", programList_websites_list=" + programList_websites_list +
                ", programList_download_list=" + programList_download_list +
                ", receiveFlag='" + receiveFlag + '\'' +
                ", searchMethod='" + searchMethod + '\'' +
                ", version='" + version + '\'' +
                ", flag=" + flag +
                ", data_map=" + data_map +
                ", filterWords=" + filterWords +
                ", workId=" + workId +
                ", commonKey=" + commonKey +
                ", keyword='" + keyword + '\'' +
                ", p=" + p +
                ", workName='" + workName + '\'' +
                ", keyWords=" + keyWords +
                '}';



    }

}
