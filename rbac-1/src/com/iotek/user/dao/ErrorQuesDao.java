package com.iotek.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.iotek.user.po.ErrorQues;

@Repository
public interface ErrorQuesDao {
      public List<ErrorQues> queryErrorQuess();
      public List<ErrorQues> queryErrorQuesctsByUserId(Integer userId);
  	  public ErrorQues queryErrorQuesById(Integer errorId);
  	  public void insertErrorQues(@Param("errorQuess") List<ErrorQues> errorQuess);
  	  public int deleteErrorQuesById(Integer errorId);
  	  public List<ErrorQues> queryErrorQuesByConditions(ErrorQues errorQues);
}
