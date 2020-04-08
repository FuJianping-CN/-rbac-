package com.iotek.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.iotek.user.po.QuesCollect;

@Repository
public interface QuesCollectDao {
      public List<QuesCollect> queryAllQuesCollects();
      public List<QuesCollect> queryAllQuesCollectsByUserId(Integer userId);
  	  public QuesCollect queryQuesCollectById(Integer collectId);
  	  public int insertQuesCollect(QuesCollect quesCollect);
  	  public int deleteQuesCollectById(Integer collectId);
  	  public List<QuesCollect> queryQuesCollectByConditions(QuesCollect quesCollect);
}
