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
import com.iotek.user.po.Role;
import com.iotek.user.po.User;
import com.iotek.user.service.CategoryService;

@Controller
@RequestMapping(value="category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/index")
	public String index(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			Model model) {
		// 传入当前页码,以及页的大小;
		System.out.println("CategoryController-- index");
		PageHelper.startPage(pn, 5);
		List<Category> categories = this.categoryService.queryAllCategory();
		System.out.println("categories=" + categories.size());
		// pageinfo包装查询后的结果,只需要将pageInfo交给页面就可以了
		// 封装了分页的信息,6表示连续显示的页数
		PageInfo page = new PageInfo(categories, 6);
		model.addAttribute("pageInfo", page);
		return "category/index";
	}
	
	@ResponseBody
	@RequestMapping(value = "insertCategory")
	public Object insertCategory(Category category, HttpSession session) {
		User creator = (User) session.getAttribute("user");
		category.setCreator(creator.getUserId());
		category.setCreateDate(new Date());
		int insertResult = this.categoryService.insertCategory(category);
		AjaxResult result = new AjaxResult();
		if (insertResult > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "deleteCategoryById")
	public Object deleteCategoryById(int categId) {
		int deleteResult = this.categoryService.deleteCategoryById(categId);
		AjaxResult result = new AjaxResult();
		if (deleteResult > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping("/toUpdateCategory")
	public String toUpdateCategory(int categId, Model model) {
		Category category = this.categoryService.queryCategoryById(categId);
		model.addAttribute("category", category);
		return "category/updateCategory";
	}
	
	@ResponseBody
	@RequestMapping(value = "updateRoleById")
	public Object updateCategoryById(Category category, HttpSession session) {
		User updater = (User) session.getAttribute("user");
		category.setUpdater(updater.getUserId());
		category.setUpdateDate(new Date());
		int udpateResult = this.categoryService.updateCategoryById(category);
		AjaxResult result = new AjaxResult();
		if (udpateResult > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}
}
