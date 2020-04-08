package com.iotek.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotek.user.dao.NewsDao;
import com.iotek.user.po.News;

@Service(value="newsService")
public class NewsServiceImpl implements NewsService{
	
	@Autowired
	private NewsDao newsDao;

	@Override
	public List<News> queryAllNews() {
		// TODO Auto-generated method stub
		return this.newsDao.queryAllNews();
	}

	@Override
	public News queryNewsById(Integer newsId) {
		// TODO Auto-generated method stub
		return this.newsDao.queryNewsById(newsId);
	}

	@Override
	public int insertNews(News news) {
		// TODO Auto-generated method stub
		return this.newsDao.insertNews(news);
	}

	@Override
	public int deleteNewsById(Integer newsId) {
		// TODO Auto-generated method stub
		return this.newsDao.deleteNewsById(newsId);
	}

	@Override
	public int updateNewsById(News news) {
		// TODO Auto-generated method stub
		return this.newsDao.updateNewsById(news);
	}

	

}
