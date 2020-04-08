package com.iotek.user.controller;

import java.util.ArrayList;
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
import com.iotek.user.po.ErrorQues;
import com.iotek.user.po.QuesType;
import com.iotek.user.po.Question;
import com.iotek.user.po.User;
import com.iotek.user.service.ErrorQuesService;
import com.iotek.user.service.QuesTypeService;
import com.iotek.user.service.QuestionService;

@Controller
@RequestMapping(value = "errorQues")
public class ErrorQuesController {

	@Autowired
	private ErrorQuesService errorQuesService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuesTypeService quesTypeService;
	
	/**
	 * 向“我的错题”界面传输数据
	 */

	@RequestMapping("/index")
	public String index(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model, HttpSession session) {
		// 传入当前页码,以及页的大小;
		User user = (User) session.getAttribute("user");
		System.out.println("CategoryController-- index");
		PageHelper.startPage(pn, 5);
		List<ErrorQues> errorQuess = this.errorQuesService.queryErrorQuesctsByUserId(user.getUserId());
		List<Integer> quesIds = new ArrayList<Integer>();
		for (ErrorQues errorQues : errorQuess) {
			quesIds.add(errorQues.getQuesId());
		}
		List<Question> questions = new ArrayList<>();
		if (quesIds.size() > 0) {
			questions = this.questionService.queryQuestionByIds(quesIds);
		} 
		
		for(int i = 0; i < questions.size(); i++) {
			errorQuess.get(i).setQuestion(questions.get(i));
			QuesType quesType = this.quesTypeService.queryQuesTypeById(questions.get(i).getQuesType());
			errorQuess.get(i).setQuesType(quesType);
		}
		for (ErrorQues errorQues : errorQuess) {
			System.err.println(errorQues.toString());
		}
		// pageinfo包装查询后的结果,只需要将pageInfo交给页面就可以了
		// 封装了分页的信息,6表示连续显示的页数
		PageInfo page = new PageInfo(errorQuess, 6);
		model.addAttribute("pageInfo", page);
		return "errorQues/index";
	}


	@ResponseBody
	@RequestMapping(value = "deleteErrorQuesById")
	public Object deleteErrorQuesById(int errorId) {
		int deleteResult = this.errorQuesService.deleteErrorQuesById(errorId);
		AjaxResult result = new AjaxResult();
		if (deleteResult > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}

	@RequestMapping("/toQuestionDetail")
	public String toQuestionDetail(int errorId, Model model) {
		ErrorQues errorQues = this.errorQuesService.queryErrorQuesById(errorId);
		Question question = this.questionService.queryQuestionById(errorQues.getQuesId());
		QuesType quesType = this.quesTypeService.queryQuesTypeById(question.getQuesType());
		errorQues.setQuestion(question);
		errorQues.setQuesType(quesType);
		model.addAttribute("errorQues", errorQues);
		return "errorQues/questionDetail";
	}
	//
	// @ResponseBody
	// @RequestMapping(value = "updateRoleById")
	// public Object updateCategoryById(Category category, HttpSession session)
	// {
	// User updater = (User) session.getAttribute("user");
	// category.setUpdater(updater.getUserId());
	// category.setUpdateDate(new Date());
	// int udpateResult = this.categoryService.updateCategoryById(category);
	// AjaxResult result = new AjaxResult();
	// if (udpateResult > 0) {
	// result.setSuccess(true);
	// } else {
	// result.setSuccess(false);
	// }
	// return result;
	// }
}
