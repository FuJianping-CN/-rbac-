package com.iotek.user.service;

import java.util.List;

import com.iotek.user.po.News;

public interface NewsService {
	  public List<News> queryAllNews();
	  public News queryNewsById(Integer newsId);
	  public int insertNews(News news);
	  public int deleteNewsById(Integer newsId);
	  public int updateNewsById(News news);
}
