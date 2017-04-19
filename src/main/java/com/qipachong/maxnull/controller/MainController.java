package com.qipachong.maxnull.controller;

import com.qipachong.maxnull.dto.InfoDto;
import com.qipachong.maxnull.model.JsonResult;
import com.qipachong.maxnull.model.Program;
import com.qipachong.maxnull.utils.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MainController extends BaseController {
    /**
     * 起始页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    private String start(Model model) {
        return "index";
    }


    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    private String detail(Model model) {
        return "detail";
    }

    @RequestMapping(value = "/search", method = {RequestMethod.POST, RequestMethod.GET})
    private String getInfo(){
        return "";
    }

    /**
     * 展示爬取到的信息
     *
     * @param key
     * @param range
     * @param model
     * @return
     */
    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getInfo(
            @RequestParam("key") String key,
            @RequestParam("range") String range,
            Model model) {
        String[] ranges = range.split(";");

        List<Program> dataList;
        List<InfoDto> dtoList;
        InfoDto infoDto;
        for (int i = 0; i < ranges.length; i++) {
            String[] values = ranges[i].split("-");
            //values[0]="baidu";values[1]="20";
            if ("baidu".equals(values[0])) {
                System.out.println("OK");
                dataList = programService.Search(key, values[0], values[1]);
                if (null != dataList && dataList.size() > 0) {
                    jr = renderSuccess();
                    dtoList = new ArrayList<>();
                    for (Program program : dataList) {
                        infoDto = new InfoDto();
                        infoDto.setDate(DateUtils.formatDateTime(new Date()));
                        infoDto.setKeys(program.getKeyWords());
                        infoDto.setPlatform(program.getWebsite());
                        infoDto.setState("占位");
                        infoDto.setTitle(program.getTitle());
                        infoDto.setUrl(program.getPcUrl());
                        dtoList.add(infoDto);
                    }
                    jr.setData("baiduList", dtoList);
                } else {
                    renderError("查询失败!");
                }
            }
        }
        return jr;
    }
}
