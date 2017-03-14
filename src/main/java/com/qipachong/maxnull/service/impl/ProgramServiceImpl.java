package com.qipachong.maxnull.service.impl;

import com.qipachong.maxnull.mapper.ProgramMapper;
import com.qipachong.maxnull.model.Program;
import com.qipachong.maxnull.pageProcessor.BaiduSearchPageProcesser;
import com.qipachong.maxnull.service.ProgramService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.SpiderListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @description 用途说明
 * User: hpb
 * Date: 2016/4/12
 */
@Service("programService")
public class ProgramServiceImpl implements ProgramService {
    final Logger log = LoggerFactory.getLogger(ProgramServiceImpl.class);
    @Autowired
    ProgramMapper programMapper;
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    public int insertSelective(Program program) {
        return programMapper.insertSelective(program);
    }

    public int insert(Program program) {
        return programMapper.insert(program);
    }


    @Override
    public void batchInsert(List<Program> programList) {
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);

        for (int i = 0; i < programList.size(); i++) {
            session.insert("com.xiaobai.crawler.mapper.ProgramMapper.insertSelective",
                    programList.get(i));
        }
        session.commit();
        session.close();
    }
    public void updateMatchStatus(Program program) {
        this.programMapper.updateMatchStatus(program);
    }

    @Override
    public List<Program> Search(String key, String platform, String infoNum){
        List<Program> programList = null;
        switch(platform)
        {
            case "baidu":
                programList = BaiduSearch(key,infoNum);
                break;
            case "weixin":
                programList = BaiduSearch(key,infoNum);
                break;
            default:
                System.out.println("default");
                break;
        }
        return programList;
    }
    public List<Program> BaiduSearch(String key,String infoNum){
        //Baidu_new processor = new Baidu_new();
        BaiduSearchPageProcesser processor = new BaiduSearchPageProcesser(Integer.parseInt(infoNum));
        //FIXME
<<<<<<< HEAD
        String url = "";
        url = "http://www.baidu.com/s?wd="+key+"&ie=UTF-8";
=======
        String url;
        url = "http://www.baidu.com/s?rn=50&wd="+key+"&ie=UTF-8";
>>>>>>> Max-Null/master
        Request request = new Request(url);
        List<SpiderListener> listeners = new ArrayList<>();
        listeners.add(new SpiderListener() {
            @Override
            public void onSuccess(Request request) {

            }
            @Override
            public void onError(Request request) {
                log.error("spider listener error,request={}", request.toString());
            }
        });
        Spider baiduspider = Spider.create(processor)
                .thread(5)
                .setSpiderListeners(listeners)
                .addRequest(request);
        baiduspider.run();
        System.out.println("用时：" + (new Date().getTime()-baiduspider.getStartTime().getTime()));
        List<Program> programList = processor.getProgramList().subList(0,Integer.parseInt(infoNum));
        //FilterUtils.setKeepNum(1);
        return programList;
    }

}
