package com.qipachong.maxnull.controller;


import com.qipachong.maxnull.model.JsonResult;
import com.qipachong.maxnull.service.ProgramService;
import com.qipachong.maxnull.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.util.Date;


/**
 * Author: D.Yang
 * Email: koyangslash@gmail.com
 * Date: 16/10/9
 * Time: 下午1:37
 * Describe: 基础控制器
 */
public class BaseController extends AbstractController {

    @Autowired
    protected ServletContext servletContext;
    protected Logger log = LoggerFactory.getLogger(getClass());
    /**
     * 数据库中假删除字段（deleted）默认值，用于查询或插入数据方法 value:0
     */
    protected static final Short DELETED_VALUE_OF_SELECT_OR_SAVE = Short
            .parseShort("0");

    /**
     * 数据库中假删除字段（deleted）默认值，用于删除或修改数据方法 value:1
     */
    protected static final Short DELETED_VALUE_OF_DELETE_OR_UPDATE = Short
            .parseShort("1");

    /**
     * 字符数组，用于中文汉字转拼音时，追加到不唯一的标识符后面
     */
    protected static final char[] SUFFIX_TRANSITION_PINYING = new char[]{'a',
            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    /**
     * 分页默认页码
     */
    protected static final String PAGE_NUM = "1";

    /**
     * 分页默认每页显示数据条数
     */
    protected static final String PAGE_SIZE = "20";


    protected JsonResult jr = new JsonResult();
    @Autowired
    ProgramService programService;


    /**
     * 渲染失败数据
     *
     * @return result
     */
    protected JsonResult renderError() {
        JsonResult result = new JsonResult();
        result.setSuccess(false);
        result.setStatus("500");
        return result;
    }

    /**
     * 渲染失败数据（带消息）
     *
     * @param msg 需要返回的消息
     * @return result
     */
    protected JsonResult renderError(String msg) {
        JsonResult result = renderError();
        result.setSuccess(false);
        result.setMsg(msg);
        result.setStatus("500");
        return result;
    }

    /**
     * 渲染成功数据
     *
     * @return result
     */
    protected JsonResult renderSuccess() {
        JsonResult result = new JsonResult();
        result.setSuccess(true);
        result.setStatus("200");
        return result;
    }

    /**
     * 渲染成功数据（带信息）
     *
     * @param msg 需要返回的信息
     * @return result
     */
    protected JsonResult renderSuccess(String msg) {
        JsonResult result = renderSuccess();
        result.setSuccess(true);
        result.setStatus("200");
        result.setMsg(msg);
        return result;
    }


    /**
     * 1.初始化数据绑定 2.将字段中Date类型转换为String类型
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {

            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
                                                 HttpServletResponse arg1) throws Exception {
        log.info("BaseController...");
        return super.handleRequest(arg0, arg1);
    }
}
