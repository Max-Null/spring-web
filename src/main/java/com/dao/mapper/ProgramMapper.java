package com.dao.mapper;

import com.dao.model.Program;

/**
 * @author Bruce_Q
 * @create 2017-03-03 14:08
 **/
public interface ProgramMapper {
    int insertSelective(Program program);
    int insert(Program program);
    int updateMatchStatus(Program program);
}
