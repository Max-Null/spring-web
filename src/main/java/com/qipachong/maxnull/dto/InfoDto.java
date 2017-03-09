package com.qipachong.maxnull.dto;

/**
 * Created by MaxNull on 2017-3-9 0009.
 */
public class InfoDto {

    /**
     * 平台platform
     */
    private String platform;
    /**
     * 标题
     */
    private String title;
    /**
     * 网址
     */
    private String url;
    /**
     * 关键词
     */
    private String keys;
    /**
     * 时间
     */
    private String date;
    /**
     * 状态
     */
    private String state;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
