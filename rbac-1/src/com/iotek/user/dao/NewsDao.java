package com.iotek.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.iotek.user.po.News;

@Repository
public interface NewsDao {
      public List<News> queryAllNews();
  	  public News queryNewsById(Integer newsId);
  	  public int insertNews(News news);
  	  public int deleteNewsById(Integer newsId);
  	  public int updateNewsById(News news);
}
