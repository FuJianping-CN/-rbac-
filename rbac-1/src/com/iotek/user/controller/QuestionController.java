package com.iotek.user.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.iotek.user.po.QuesType;
import com.iotek.user.po.Question;
import com.iotek.user.po.Role;
import com.iotek.user.po.User;
import com.iotek.user.service.CategoryService;
import com.iotek.user.service.QuesTypeService;
import com.iotek.user.service.QuestionService;

@Controller
@RequestMapping(value="question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuesTypeService quesTypeService;
	
	@RequestMapping("/index")
	public String index(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			Model model, 
			@RequestParam(value="quesTypeId") Integer quesTypeId) {
		System.err.println("QuestionController index quesTypeId = " + quesTypeId);
		// 传入当前页码,以及页的大小;
		PageHelper.startPage(pn, 5);
		List<Question> questions = this.questionService.queryAllQuestionByType(quesTypeId);
		// pageinfo包装查询后的结果,只需要将pageInfo交给页面就可以了
		// 封装了分页的信息,6表示连续显示的页数
		PageInfo page = new PageInfo(questions, 6);
		model.addAttribute("pageInfo", page);
		
		QuesType quesType = this.quesTypeService.queryQuesTypeById(quesTypeId);
//		System.err.println("quesType = " + quesType.toString());
		model.addAttribute("quesType", quesType);
		
		return "question/index";
	}
	
	
	@RequestMapping("/toAddQuestion")
	public String toAddQuestion(Integer quesTypeId, Model model){
		QuesType quesType = this.quesTypeService.queryQuesTypeById(quesTypeId);
		model.addAttribute("quesType", quesType);
		return "question/addQuestion";
	}
	
	@ResponseBody
	@RequestMapping(value = "insertQuestion")
	public Object insertQuestion(Question question, HttpSession session) {
		User creator = (User) session.getAttribute("user");
		question.setCreator(creator.getUserId());
		question.setCreateDate(new Date());
		System.err.println("insertQuestion question= " + question);
		int insertResult = this.questionService.insertQuestion(question);
		AjaxResult result = new AjaxResult();
		if (insertResult > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "deleteQuestionById")
	public Object deleteQuestionById(Integer quesId) {
		int deleteResult = this.questionService.deleteQuestionById(quesId);
		AjaxResult result = new AjaxResult();
		if (deleteResult > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping("/toUpdateQuestion")
	public String toUpdateQuestion(int quesId, Model model) {
		Question question = this.questionService.queryQuestionById(quesId);
		model.addAttribute("question", question);
		QuesType quesType = this.quesTypeService.queryQuesTypeById(question.getQuesType());
		model.addAttribute("quesType", quesType);
		return "question/updateQuestion";
	}
	
	@ResponseBody
	@RequestMapping(value = "updateQuestionIdById")
	public Object updateQuestionIdById(Question question, HttpSession session) {
		User updater = (User) session.getAttribute("user");
		question.setUpdater(updater.getUserId());
		question.setUpdateDate(new Date());
		int udpateResult = this.questionService.updateQuestionById(question);
		AjaxResult result = new AjaxResult();
		if (udpateResult > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}
	
}
