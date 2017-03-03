package com.dao.service;


import com.dao.model.Program;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @description 用途说明
 * User: hpb
 * Date: 2016/4/12
 */
public interface ProgramService {
    int insert(Program program);
    int insertSelective(Program program);
    void batchInsert(List<Program> programList);

    void updateMatchStatus(Program program);

}
