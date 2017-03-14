package com.bruce.processor.searchProcessor;

import com.qipachong.maxnull.model.Program;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * @author Bruce_Q
 * @create 2017-03-03 13:51
 **/
public class FilterUtils {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    //保留数量
    private static int i = 1;
    //过滤数量
    private static int j = 1;

    public void BaiduSearch(Page page, List<Program> programList) {
        Program program = new Program();
        String title = "";
        if (StringUtils.substringBetween(page.getHtml().toString(), "<title>", "</title>") != null) {
            title = StringUtils.substringBetween(page.getHtml().toString(), "<title>", "</title>");
        }
        try {
            program.setPcUrl(URLDecoder.decode(page.getRequest().getUrl().trim(), "UTF-8"));
            program.setTitle(URLDecoder.decode(title.trim(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (program.getPcUrl() != null) {
            programList.add(program);
        }
        logger.info("------------------------------保留操作：" + i + "---------------------------------------------");
        i++;
    }
}


