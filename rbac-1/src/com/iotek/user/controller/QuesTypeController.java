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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iotek.user.po.AjaxResult;
import com.iotek.user.po.Category;
import com.iotek.user.po.QuesType;
import com.iotek.user.po.Role;
import com.iotek.user.po.User;
import com.iotek.user.service.CategoryService;
import com.iotek.user.service.QuesTypeService;
import com.iotek.user.service.QuestionService;

@Controller
@RequestMapping(value="quesType")
public class QuesTypeController {

	@Autowired
	private QuesTypeService quesTypeService;
	
	@Autowired
	private QuestionService questionService;
	
	@RequestMapping("/index")
	public String index(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			Model model) {
		// 传入当前页码,以及页的大小;
		PageHelper.startPage(pn, 5);
		List<QuesType> quesTypes = this.quesTypeService.queryAllQuesType();
		// pageinfo包装查询后的结果,只需要将pageInfo交给页面就可以了
		// 封装了分页的信息,6表示连续显示的页数
		PageInfo page = new PageInfo(quesTypes, 6);
		model.addAttribute("pageInfo", page);
		return "quesType/index";
	}
	
	@ResponseBody
	@RequestMapping(value = "insertQuesType")
	public Object insertQuesType(QuesType quesType, HttpSession session) {
		User creator = (User) session.getAttribute("user");
		quesType.setCreator(creator.getUserId());
		quesType.setCreateDate(new Date());
		int insertResult = this.quesTypeService.insertQuesType(quesType);
		AjaxResult result = new AjaxResult();
		if (insertResult > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "deleteQuesTypeById")
	public Object deleteQuesTypeById(Integer quesTypeId) {
		int deleteResult = this.quesTypeService.deleteQuesTypeById(quesTypeId);
		
		//当删除试题类型时，也要删除question表中该试题类型的试题
		System.err.println("deleteQuesTypeById quesTypeId="+quesTypeId);
		List<Integer> quesTypeIds = this.questionService.queryIdByQuesTypeId(quesTypeId);
		System.err.println("deleteQuesTypeById quesTypeIds="+quesTypeIds);
		this.questionService.deleteQuestionByQuesTypeId(quesTypeIds);
		
		AjaxResult result = new AjaxResult();
		if (deleteResult > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping("/toUpdateQuesType")
	public String toUpdateQuesType(int quesTypeId, Model model) {
		QuesType quesType = this.quesTypeService.queryQuesTypeById(quesTypeId);
		model.addAttribute("quesType", quesType);
		return "quesType/updateQuesType";
	}
	
	@ResponseBody
	@RequestMapping(value = "updateQuesTypeIdById")
	public Object updatequesTypeIdById(QuesType quesType, HttpSession session) {
		User updater = (User) session.getAttribute("user");
		quesType.setUpdater(updater.getUserId());
		quesType.setUpdateDate(new Date());
		int udpateResult = this.quesTypeService.updateQuesTypeById(quesType);
		AjaxResult result = new AjaxResult();
		if (udpateResult > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}
	
	
	@RequestMapping("/toQuesManage")
	public String toQuesManage(Integer quesTypeId, Model model, RedirectAttributes redirectAttributes) {
//		QuesType quesType = this.quesTypeService.queryQuesTypeById(quesTypeId);
//		model.addAttribute("quesType", quesType);
//		return "question/quesManage";
		System.err.println("toQuesManage  quesTypeId = " + quesTypeId);
		redirectAttributes.addAttribute("quesTypeId", quesTypeId);
		return "redirect:/question/index?quesTypeId"+quesTypeId;
	}
}
