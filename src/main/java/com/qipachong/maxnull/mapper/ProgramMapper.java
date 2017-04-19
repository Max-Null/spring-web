package com.qipachong.maxnull.mapper;


import com.qipachong.maxnull.model.Program;

import java.util.List;

/**
 * @author Bruce_Q
 * @create 2017-03-03 14:08
 **/
public interface ProgramMapper {
    List<Program> Search(String key, String platform, String infoNum);
}
