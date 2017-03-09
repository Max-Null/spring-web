package com.qipachong.maxnull.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: D.Yang
 * Email: koyangslash@gmail.com
 * Date: 16/8/31
 * Time: 下午5:50
 * Describe: 封装Json返回信息
 */
public class JsonResult {
    private boolean success;
    private String status;
    private String msg;
    private Map<String, Object> data;// 传到前台的数据
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(String key, Object value) {
        if (null == this.data) {
            this.data = new HashMap<String, Object>();
        }
        this.data.put(key, value);
    }
}
