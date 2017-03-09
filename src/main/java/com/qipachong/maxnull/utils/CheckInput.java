/**
 * 
 */
package com.qipachong.maxnull.utils;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.alibaba.fastjson.JSONObject;

public class CheckInput {
    private JSONObject input;
    
    private CheckReturn checkReturn;
    
    // 字段选择枚举类
    public enum KeySelect {
        require, opzio;
    }
    
    // 字段类型枚举类
    public enum KeyType {
        string, integer, number, positiveInteger, bool;
    }
    
    public CheckInput() {
        super();
    }
    
    public CheckInput(JSONObject input) {
        super();
        this.input = input;
    }
    
    /**
     * params: ks:KeySelect，字段选择 kt:KeyType， 字段名类型 key:String， 字段名
     */
    public CheckReturn checkKey(KeySelect ks, KeyType kt, String key) {
        this.checkReturn = new CheckReturn(false, false);
        if(ks == null || kt == null || key == null) {
            return null;
        }
        switch (ks) {
            case require:
                this.checkRequire(kt, key);
                break;
            case opzio:
                this.checkOpzio(kt, key);
                break;
            default:
                this.checkRequire(kt, key);
        }
        return this.checkReturn;
    }
    
    private void checkRequire(KeyType kt, String key) {
        if(!input.containsKey(key)) {
            this.checkReturn.setCode(1);
            this.checkReturn.setMsg("必选参数" + key + "未提供");
            return;
        }
        switch (kt) {
        // 字符串类型
            case string:
                if(StringUtils.isEmpty(input.getString(key))) {
                    this.checkReturn.setCode(1);
                    this.checkReturn.setMsg("必选参数" + key + "不能为空");
                } else {
                    // this.checkReturn.setCode(0);
                    // this.checkReturn.setMsg("必选参数"+key+"校验正确");
                    this.checkReturn.setSuccess(true);
                    this.checkReturn.setUserable(true);
                }
                break;
            // 布尔类型
            case bool:
                if(StringUtils.isEmpty(input.getString(key))) {
                    this.checkReturn.setCode(1);
                    this.checkReturn.setMsg("必选参数" + key + "不能为空");
                } else if(!BooleanUtils.toBoolean(input.getString(key))) {
                    this.checkReturn.setCode(1);
                    this.checkReturn.setMsg("必选参数" + key + "不能转换成bool型");
                } else {
                    this.checkReturn.setSuccess(true);
                    this.checkReturn.setUserable(true);
                }
                break;
            // 整数类型
            case integer:
                if(StringUtils.isEmpty(input.getString(key))) {
                    this.checkReturn.setCode(1);
                    this.checkReturn.setMsg("必选参数" + key + "不能为空");
                } else if(!NumberUtils.isDigits(input.getString(key))) {
                    this.checkReturn.setCode(1);
                    this.checkReturn.setMsg("必选参数" + key + "必须为整数");
                } else {
                    // this.checkReturn.setCode(0);
                    // this.checkReturn.setMsg("必选参数"+key+"校验正确");
                    this.checkReturn.setSuccess(true);
                    this.checkReturn.setUserable(true);
                }
                break;
            // 数值类型
            case number:
                if(StringUtils.isEmpty(input.getString(key))) {
                    this.checkReturn.setCode(1);
                    this.checkReturn.setMsg("必选参数" + key + "不能为空");
                } else if(!NumberUtils.isNumber(input.getString(key))) {
                    this.checkReturn.setCode(1);
                    this.checkReturn.setMsg("必选参数" + key + "必须为数值");
                } else {
                    // this.checkReturn.setCode(0);
                    // this.checkReturn.setMsg("必选参数"+key+"校验正确");
                    this.checkReturn.setSuccess(true);
                    this.checkReturn.setUserable(true);
                }
                break;
            // 正整数类型
            case positiveInteger:
                if(StringUtils.isEmpty(input.getString(key))) {
                    this.checkReturn.setCode(1);
                    this.checkReturn.setMsg("必选参数" + key + "不能为空");
                } else if(!NumberUtils.isDigits(input.getString(key))) {
                    this.checkReturn.setCode(1);
                    this.checkReturn.setMsg("必选参数" + key + "必须为整数");
                } else if(Long.parseLong(input.getString(key)) < 1) {
                    this.checkReturn.setCode(1);
                    this.checkReturn.setMsg("必选参数" + key + "必须为大于0的整数");
                } else {
                    // this.checkReturn.setCode(0);
                    // this.checkReturn.setMsg("必选参数"+key+"校验正确");
                    this.checkReturn.setSuccess(true);
                    this.checkReturn.setUserable(true);
                }
                break;
            default:
                if(StringUtils.isEmpty(input.getString(key))) {
                    this.checkReturn.setCode(1);
                    this.checkReturn.setMsg("必选参数" + key + "不能为空");
                } else {
                    // this.checkReturn.setCode(0);
                    // this.checkReturn.setMsg("必选参数"+key+"校验正确");
                    this.checkReturn.setSuccess(true);
                }
        }
    }
    
    private void checkOpzio(KeyType kt, String key) {
        switch (kt) {
        // 字符串类型
            case string:
                if(!input.containsKey(key)) {
                    this.checkReturn.setSuccess(true);
                } else if(StringUtils.isEmpty(input.getString(key))) {
                    this.checkReturn.setSuccess(true);
                } else {
                    this.checkReturn.setSuccess(true);
                    this.checkReturn.setUserable(true);
                }
                break;
            // 布尔类型
            case bool:
                if(!input.containsKey(key)) {
                    this.checkReturn.setSuccess(true);
                } else if(StringUtils.isEmpty(input.getString(key))) {
                    this.checkReturn.setSuccess(true);
                } else {
                    this.checkReturn.setSuccess(true);
                    // 判断是否能转成bool型
                    if(BooleanUtils.toBoolean(input.getString(key))) {
                        this.checkReturn.setUserable(true);
                    }
                }
                break;
            // 整数类型
            case integer:
                if(!input.containsKey(key)) {
                    this.checkReturn.setSuccess(true);
                } else if(StringUtils.isEmpty(input.getString(key))) {
                    this.checkReturn.setSuccess(true);
                } else if(!NumberUtils.isDigits(input.getString(key))) {
                    this.checkReturn.setCode(1);
                    this.checkReturn.setMsg("可选参数" + key + "必须为整数");
                } else {
                    this.checkReturn.setSuccess(true);
                    this.checkReturn.setUserable(true);
                }
                break;
            // 数值类型
            case number:
                if(!input.containsKey(key)) {
                    // this.checkReturn.setCode(0);
                    // this.checkReturn.setMsg("可选参数"+key+"可不用提供");
                    this.checkReturn.setSuccess(true);
                } else if(StringUtils.isEmpty(input.getString(key))) {
                    // this.checkReturn.setCode(0);
                    // this.checkReturn.setMsg("可选参数"+key+"可为空");
                    this.checkReturn.setSuccess(true);
                } else if(!NumberUtils.isNumber(input.getString(key))) {
                    this.checkReturn.setCode(1);
                    this.checkReturn.setMsg("可选参数" + key + "必须为数值");
                } else {
                    // this.checkReturn.setCode(0);
                    // this.checkReturn.setMsg("可选参数"+key+"校验正确");
                    this.checkReturn.setSuccess(true);
                    this.checkReturn.setUserable(true);
                }
                break;
            // 正整数类型
            case positiveInteger:
                if(!input.containsKey(key)) {
                    // this.checkReturn.setCode(0);
                    // this.checkReturn.setMsg("可选参数"+key+"可不用提供");
                    this.checkReturn.setSuccess(true);
                } else if(StringUtils.isEmpty(input.getString(key))) {
                    // this.checkReturn.setCode(0);
                    // this.checkReturn.setMsg("可选参数"+key+"可为空");
                    this.checkReturn.setSuccess(true);
                } else if(!NumberUtils.isDigits(input.getString(key))) {
                    this.checkReturn.setCode(1);
                    this.checkReturn.setMsg("可选参数" + key + "必须为整数");
                } else if(Long.parseLong(input.getString(key)) < 1) {
                    this.checkReturn.setCode(1);
                    this.checkReturn.setMsg("可选参数" + key + "必须为大于0的整数");
                } else {
                    // this.checkReturn.setCode(0);
                    // this.checkReturn.setMsg("可选参数"+key+"校验正确");
                    this.checkReturn.setSuccess(true);
                    this.checkReturn.setUserable(true);
                }
                break;
            default:
                if(!input.containsKey(key)) {
                    // this.checkReturn.setCode(0);
                    // this.checkReturn.setMsg("可选参数"+key+"可不用提供");
                    this.checkReturn.setSuccess(true);
                } else if(StringUtils.isEmpty(input.getString(key))) {
                    // this.checkReturn.setCode(0);
                    // this.checkReturn.setMsg("可选参数"+key+"可为空");
                    this.checkReturn.setSuccess(true);
                } else {
                    // this.checkReturn.setCode(0);
                    // this.checkReturn.setMsg("可选参数"+key+"校验正确");
                    this.checkReturn.setSuccess(true);
                    this.checkReturn.setUserable(true);
                }
        }
    }
    
    public JSONObject getInput() {
        return input;
    }
    
    public void setInput(JSONObject input) {
        this.input = input;
    }
    
    public CheckReturn getCheckReturn() {
        return checkReturn;
    }
    
    public void setCheckReturn(CheckReturn checkReturn) {
        this.checkReturn = checkReturn;
    }
    
}
