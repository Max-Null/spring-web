package com.bruce.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


/**
 * Created by 鑫 on 2016/12/11.
 */
public abstract class BaseServlet extends HttpServlet {

    private static final long serialVersionUID = -2379942732083954787L;

    final Logger log = LoggerFactory
            .getLogger(BaseServlet.class);

    ApplicationContext context = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        context = WebApplicationContextUtils
                .getRequiredWebApplicationContext(config.getServletContext());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        JSONObject output = new JSONObject();
        JSONObject input = null;
        String msg = "";
        try {
            StringBuilder sb = new StringBuilder("");
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    req.getInputStream(), "utf-8"));
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            br.close();
            String inputString = sb.toString();

            if (inputString.length() < 1) {
                setResult("没有接收到JSON数据", 1, resp);
                return;
            }
            try {
                input = JSON.parseObject(inputString);
            } catch (Exception ex) {
                msg = "解析json错误：" + ex.toString();
                log.info("解析json错误：" + ex.toString());
            }
            if (input == null) {
                setResult(msg, 1, resp);
                return;
            }
            process(input, output);

        } catch (Exception e) {
            output.put("message", "处理异常，错误调试信息：" + e.toString());
            output.put("code", 1);
                log.error(e.toString(), e);
        }
        outputJSON(output, resp);
    }

    public abstract void process(JSONObject input, JSONObject output);

    protected void outputJSON(JSONObject output, HttpServletResponse resp) {
        try {

            resp.setContentType("application/x-javascript; charset=utf-8");
            PrintWriter pw = resp.getWriter();
            String str = output.toString();
            pw.write(str);
            pw.flush();
            pw.close();
        } catch (Exception ex) {
            log.info(ex.toString());
            ex.printStackTrace();
        }
    }

    private void setResult(String msg, int code, HttpServletResponse resp) {
        JSONObject output = new JSONObject();
        output.put("message", msg);
        output.put("code", code);
        outputJSON(output, resp);
    }

}