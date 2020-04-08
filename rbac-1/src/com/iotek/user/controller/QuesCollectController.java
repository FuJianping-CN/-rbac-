package com.iotek.user.controller;

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
import com.iotek.user.po.User;
import com.iotek.user.service.CategoryService;
import com.iotek.user.service.QuesCollectService;
import com.iotek.user.service.QuesTypeService;
import com.iotek.user.service.QuestionService;

@Controller
@RequestMapping(value = "quesCollect")
public class QuesCollectController {

	@Autowired
	private QuesCollectService quesCollectService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuesTypeService quesTypeService;

	@RequestMapping("/index")
	public String index(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model, HttpSession session) {
		// ���뵱ǰҳ��,�Լ�ҳ�Ĵ�С;
		User user = (User) session.getAttribute("user");
		System.out.println("CategoryController-- index");
		PageHelper.startPage(pn, 5);
		List<QuesCollect> quesCollects = this.quesCollectService.queryAllQuesCollectsByUserId(user.getUserId());
		System.out.println("quesCollects=" + quesCollects.size());
		// pageinfo��װ��ѯ��Ľ��,ֻ��Ҫ��pageInfo����ҳ��Ϳ�����
		// ��װ�˷�ҳ����Ϣ,6��ʾ������ʾ��ҳ��
		PageInfo page = new PageInfo(quesCollects, 6);
		model.addAttribute("pageInfo", page);
		return "quesCollect/index";
	}

	@ResponseBody
	@RequestMapping(value = "insertQuesCollect")
	public Object insertQuesCollect(QuesCollect quesCollect, HttpSession session) {
		User creator = (User) session.getAttribute("user");
		quesCollect.setCreator(creator.getUserId());
		quesCollect.setCreateDate(new Date());
		AjaxResult result = new AjaxResult();
		System.err.println("insertQuesCollect quesCollect="+quesCollect);
		// �жϸ��û��Ƿ����ղظ���
		List<QuesCollect> isHasCollected = this.quesCollectService.queryQuesCollectByConditions(quesCollect);
		if (isHasCollected.size() > 0) {
			result.setSuccess(false);// ���û����ղ�
		} else {
			Question question = this.questionService.queryQuestionById(quesCollect.getQuesId());
			quesCollect.setQuesContent(question.getQuesContent());
			int insertResult = this.quesCollectService.insertQuesCollect(quesCollect);

			if (insertResult > 0) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
		}

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "deleteQuesCollectById")
	public Object deleteQuesCollectById(int collectId) {
		int deleteResult = this.quesCollectService.deleteQuesCollectById(collectId);
		AjaxResult result = new AjaxResult();
		if (deleteResult > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}

	@RequestMapping("/toQuestionDetail")
	public String toQuestionDetail(int quesId, Model model) {
		Question question = this.questionService.queryQuestionById(quesId);
		QuesType quesType = this.quesTypeService.queryQuesTypeById(question.getQuesType());
		model.addAttribute("question", question);
		model.addAttribute("quesType", quesType);
		return "quesCollect/questionDetail";
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
