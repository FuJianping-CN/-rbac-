package com.iotek.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.iotek.user.po.Category;
import com.iotek.user.po.Test;

@Repository
public interface TestDao {
      public List<Test> queryAllTests();
      public List<Test> queryAllTestsByUserId(Integer userId);
  	  public Test queryTestById(Integer testId);
  	  public int insertTest(Test test);
  	  public int deleteTestById(Integer testId);
}
