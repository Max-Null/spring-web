/**
 * 
 */
package com.qipachong.maxnull.utils;

import com.alibaba.fastjson.JSONObject;

public class CheckReturn {
    private Integer code; // 校验后返回给客户端的code,0=正常，1=异常，2=...
    
    private String msg; // 校验后的返回信息
    
    private boolean success; // 校验参数通过与否true=通过，false=不通过
    
    private boolean userable; // 是否能取值true=能，false=不能
    
    public CheckReturn() {
        super();
    }
    
    public CheckReturn(boolean success) {
        super();
        this.success = success;
    }
    
    public CheckReturn(boolean success, boolean userable) {
        super();
        this.success = success;
        this.userable = userable;
    }
    
    public CheckReturn(Integer code, String msg, boolean success, boolean userable) {
        super();
        this.code = code;
        this.msg = msg;
        this.success = success;
        this.userable = userable;
    }
    
    public void wrong(JSONObject output) {
        output.put("code", this.code);
        output.put("errCode", this.code);
        output.put("msg", this.msg);
        return;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public void setCode(Integer code) {
        this.code = code;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public boolean isUserable() {
        return userable;
    }
    
    public void setUserable(boolean userable) {
        this.userable = userable;
    }
    
}
