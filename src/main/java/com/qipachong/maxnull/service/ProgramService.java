package com.qipachong.maxnull.service;


import com.qipachong.maxnull.model.Program;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @description 用途说明
 * User: hpb
 * Date: 2016/4/12
 */
public interface ProgramService {
    List<Program> Search(String key,String platform,String infoNum);

}
