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
public class MainController extends BaseController{

	/**
	 *	起始页面
	 *
	 * @param model
	 * @return
     */
	@RequestMapping(value = "/start", method = RequestMethod.GET)
	private String start(Model model) {
		// list.jsp + model = ModelAndView
		return "start";// WEB-INF/jsp/"list".jsp
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
				dataList = programService.baiduSearch(key,values[1]);
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

/*	// ajax json
	@RequestMapping(value = "/{bookId}/detail", method = RequestMethod.GET)
	@ResponseBody
	private String detail(@PathVariable("bookId") Long bookId, Model model) {
		if (bookId == null) {
			return "redirect:/book/list";
		}
		Book book = bookService.getById(bookId);
		if (book == null) {
			return "forward:/book/list";
		}
		model.addAttribute("book", book);
		return "detail";
	}

	@RequestMapping(value = "/{bookId}/appoint", method = RequestMethod.POST, produces = {
			"application/json; charset=utf-8" })
	private Result<AppointExecution> appoint(@PathVariable("bookId") Long bookId, @Param("studentId") Long studentId) {
		if (studentId == null || studentId.equals("")) {
			return new Result<>(false, "学号不能为空");
		}
		AppointExecution execution = null;
		try {
			execution = bookService.appoint(bookId, studentId);
		} catch (NoNumberException e1) {
			execution = new AppointExecution(bookId, AppointStateEnum.NO_NUMBER);
		} catch (RepeatAppointException e2) {
			execution = new AppointExecution(bookId, AppointStateEnum.REPEAT_APPOINT);
		} catch (Exception e) {
			execution = new AppointExecution(bookId, AppointStateEnum.INNER_ERROR);
		}
		return new Result<AppointExecution>(true, execution);
	}*/

}
