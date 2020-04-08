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
import com.iotek.user.po.News;
import com.iotek.user.po.User;
import com.iotek.user.service.CategoryService;
import com.iotek.user.service.NewsService;

@Controller
@RequestMapping(value="news")
public class NewsController {

	@Autowired
	private NewsService newsService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/index")
	public String index(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			Model model) {
		// 传入当前页码,以及页的大小;
		PageHelper.startPage(pn, 5);
		List<News> newss = this.newsService.queryAllNews();
		System.out.println("newss=" + newss.size());
		// pageinfo包装查询后的结果,只需要将pageInfo交给页面就可以了
		// 封装了分页的信息,6表示连续显示的页数
		PageInfo page = new PageInfo(newss, 6);
		model.addAttribute("pageInfo", page);
		return "news/index";
	}
	
	@RequestMapping("/toAddNews")
	public String toAddNews(Model model) {
		System.err.println("toAddNews...");
		List<Category> categories = this.categoryService.queryAllCategory();
		model.addAttribute("categories", categories);
		return "news/addNews";
	}
	
	@ResponseBody
	@RequestMapping(value = "insertNews")
	public Object insertNews(News news, HttpSession session) {
		System.err.println("insertNews...");
		User creator = (User) session.getAttribute("user");
		news.setCreator(creator.getUserId());
		news.setCreateDate(new Date());
		System.err.println("insertNews news = " + news.toString());
		int insertResult = this.newsService.insertNews(news);
		System.out.println("insertNews insertResult = " + insertResult);
		AjaxResult result = new AjaxResult();
		if (insertResult > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		System.err.println("result="+result.toString());
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "deleteNewsById")
	public Object deleteNewsById(int newsId) {
		System.err.println("deleteNewsById newsId="+newsId);
		int deleteResult = this.newsService.deleteNewsById(newsId);
		System.err.println("deleteNewsById deleteResult="+deleteResult);
		AjaxResult result = new AjaxResult();
		if (deleteResult > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping("/toEditNews")
	public String toUpdateNews(int newsId, Model model) {
		List<Category> categories = this.categoryService.queryAllCategory();
		model.addAttribute("categories", categories);
		News news = this.newsService.queryNewsById(newsId);
		model.addAttribute("news", news);
		return "news/editNews";
	}
	
	@ResponseBody
	@RequestMapping(value = "updateNewsById")
	public Object updateNewsById(News news, HttpSession session) {
		User updater = (User) session.getAttribute("user");
		news.setUpdater(updater.getUserId());
		news.setUpdateDate(new Date());
		int udpateResult = this.newsService.updateNewsById(news);
		AjaxResult result = new AjaxResult();
		if (udpateResult > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}
}
