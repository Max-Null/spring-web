package com.bruce.processor.searchProcessor.baidu;

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
public class FilterUtilsBaidu {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    //保留数量
    private static int i = 1;
    //过滤数量
    private static int j = 1;

    /**
     * 字符串转换unicode
     */
    public static String string2Unicode(String string) {

        StringBuffer unicode = new StringBuffer();

        for (int i = 0; i < string.length(); i++) {

            // 取出每一个字符
            char c = string.charAt(i);

            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }

    public void BaiduSearch(Page page,List<Program> programList) {
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
    }

    /**
     * 将含有passkeyword配置文件中关键词的链接过滤掉
     **/
    public void BaiduPassKeyWord(Page page, List name, List<Program> programList, String searchMethod, String common, String workName, List filterWords) {
        //title：网页的标题
        String title = "";
        if (StringUtils.substringBetween(page.getHtml().toString(), "<title>", "</title>") != null) {
            title = StringUtils.substringBetween(page.getHtml().toString(), "<title>", "</title>");
        }
        String url = page.getRequest().getUrl();


        Program program = new Program();
        boolean flag_pass = true;

        //filterWords为json中传入的过滤词，当标题中含有该过滤词时，过滤该条链接
        if (filterWords != null) {
            for (int i = 0; i < filterWords.size(); i++) {
                if (filterWords.get(i) != null && (title.contains((String) filterWords.get(i)) || url.contains((String) filterWords.get(i)))) {
                    flag_pass = false;
                    break;
                }
            }
        }

        //keyWords：人工传入的关键字
        String keyWords = "";
        if (name != null) {
            for (int i = 0; i < name.size(); i++) {
                if (name.get(i) != null) {
                    keyWords = keyWords + name.get(i) + "/";
                }
            }
        }

        if (flag_pass) {
            try {
                program.setPcUrl(URLDecoder.decode(url.trim(), "UTF-8"));
                program.setTitle(URLDecoder.decode(title.trim(), "UTF-8"));
                if (common != null) {
                    program.setKeyWords(workName + "/" + keyWords.trim() + common);
                } else {
                    program.setKeyWords(workName + "/" + keyWords.trim());
                }
                program.setWorkName(workName);
                program.setSearchMethod(searchMethod.trim());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (program.getPcUrl() != null) {
            programList.add(program);
        }
        logger.info("------------------------------保留操作：" + i + "---------------------------------------------");
        i++;
    }

    /**
     * 将被过滤掉的内容显示出来
     */

    public void BaiduContainsPass(Page page, List name, List<Program> programList, String searchMethod, String common, String workName, List filterWords) {
        String title = "";
        if (StringUtils.substringBetween(page.getHtml().toString(), "<title>", "</title>") != null) {
            title = StringUtils.substringBetween(page.getHtml().toString(), "<title>", "</title>");
        }
        String url = page.getRequest().getUrl();


        Program program = new Program();
        boolean flag_pass = false;


        for (int i = 0; i < filterWords.size(); i++) {
            if (filterWords.get(i) != null && (title.contains((String) filterWords.get(i)) || url.contains((String) filterWords.get(i)))) {
                flag_pass = true;
                break;
            }
        }
        String keyWords = "";
        for (int i = 0; i < name.size(); i++) {
            if (name.get(i) != null) {
                keyWords = keyWords + name.get(i) + "/";
            }
        }
        if (flag_pass) {
            try {
                program.setPcUrl(URLDecoder.decode(url.trim(), "UTF-8"));
                program.setTitle(URLDecoder.decode(title.trim(), "UTF-8"));
                if (common != null) {
                    program.setKeyWords(workName + "/" + keyWords.trim() + common);
                } else {
                    program.setKeyWords(workName + "/" + keyWords.trim());
                }
                program.setWorkName(workName);
                program.setSearchMethod(searchMethod.trim());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (program.getPcUrl() != null) {
            programList.add(program);
        }
        logger.info("------------------------------过滤操作：" + j + "---------------------------------------------");
        j++;
    }


}


