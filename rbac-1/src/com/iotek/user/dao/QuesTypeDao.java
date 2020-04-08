package com.iotek.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.iotek.user.po.QuesType;

@Repository
public interface QuesTypeDao {
      public List<QuesType> queryAllQuesType();
  	  public QuesType queryQuesTypeById(Integer quesTypeId);
  	  public int insertQuesType(QuesType quesType);
  	  public int deleteQuesTypeById(Integer quesTypeId);
  	  public int updateQuesTypeById(QuesType quesType);
}
