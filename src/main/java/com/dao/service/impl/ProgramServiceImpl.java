package com.dao.service.impl;

import com.dao.mapper.ProgramMapper;
import com.dao.model.Program;
import com.dao.service.ProgramService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource
    ProgramMapper programMapper;
    @Resource
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

}
