package com.iotek.user.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iotek.user.po.AjaxResult;
import com.iotek.user.po.Category;
import com.iotek.user.po.QuesCollect;
import com.iotek.user.po.QuesType;
import com.iotek.user.po.Question;
import com.iotek.user.po.Role;
import com.iotek.user.po.Test;
import com.iotek.user.po.User;
import com.iotek.user.service.CategoryService;
import com.iotek.user.service.QuesCollectService;
import com.iotek.user.service.QuesTypeService;
import com.iotek.user.service.QuestionService;
import com.iotek.user.service.TestService;
import com.iotek.util.MyUtils;

@Controller
@RequestMapping(value="test")
public class TestController {
	
	@Autowired
	private TestService testService;

	@Autowired
	private QuesCollectService quesCollectService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuesTypeService quesTypeService;
	
	
	
	@RequestMapping("/index")
	public String index(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			Model model, HttpSession session) {
		// 传入当前页码,以及页的大小;
		User user = (User) session.getAttribute("user");
		System.out.println("CategoryController-- index");
		PageHelper.startPage(pn, 5);
		List<Test> tests = this.testService.queryAllTestsByUserId(user.getUserId());
		// pageinfo包装查询后的结果,只需要将pageInfo交给页面就可以了
		// 封装了分页的信息,6表示连续显示的页数
		PageInfo page = new PageInfo(tests, 6);
		model.addAttribute("pageInfo", page);
		return "test/index";
	}
	
//	@ResponseBody
//	@RequestMapping(value = "insertTest")
//	public Object insertQuesCollect(Test test, HttpSession session) {
//		User creator = (User) session.getAttribute("user");
//		test.setCreator(creator.getUserId());
//		test.setCreateDate(new Date());
//		int insertResult = this.quesCollectService.insertQuesCollect(quesCollect);
//		AjaxResult result = new AjaxResult();
//		if (insertResult > 0) {
//			result.setSuccess(true);
//		} else {
//			result.setSuccess(false);
//		}
//		return result;
//	}
	
	@ResponseBody
	@RequestMapping(value = "deleteTestById")
	public Object deleteTestById(int testId) {
		int deleteResult = this.testService.deleteTestById(testId);
		AjaxResult result = new AjaxResult();
		if (deleteResult > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping("/toTestDetail")
	public String toTestDetail(int testId, Model model) {
		Test test = this.testService.queryTestById(testId);
		
		String quesIds = test.getQuesIds();
		String submitAnswer = test.getSubmitAnswer();
		System.err.println("toTestDetail: testId = "+ testId +  ",  quesIds="+quesIds+", submitAnswer="+submitAnswer);
		String[] quesIdsStrArr = quesIds.split(", ");
		List<Integer> quesIdsStrInt = new ArrayList<>();
		for (String string : quesIdsStrArr) {
			quesIdsStrInt.add(Integer.valueOf(string));
		}
		System.err.println("quesIdsStrInt:"+quesIdsStrInt);
		
		List<String> submitAnswerStr = MyUtils.strsToList(submitAnswer);
		
		List<Question> questions = this.questionService.queryQuestionByIds(quesIdsStrInt);
		
		for (int i = 0; i < submitAnswerStr.size(); i++) {
			questions.get(i).setSubmitAnswer(submitAnswerStr.get(i));
		}
		
		model.addAttribute("test", test);
		model.addAttribute("questions", questions);
		return "test/testDetail";
	}
//	
//	@ResponseBody
//	@RequestMapping(value = "updateRoleById")
//	public Object updateCategoryById(Category category, HttpSession session) {
//		User updater = (User) session.getAttribute("user");
//		category.setUpdater(updater.getUserId());
//		category.setUpdateDate(new Date());
//		int udpateResult = this.categoryService.updateCategoryById(category);
//		AjaxResult result = new AjaxResult();
//		if (udpateResult > 0) {
//			result.setSuccess(true);
//		} else {
//			result.setSuccess(false);
//		}
//		return result;
//	}
}
