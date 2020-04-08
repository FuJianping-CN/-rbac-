package com.iotek.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.iotek.user.po.Question;

@Repository
public interface QuestionDao {
      public List<Question> queryAllQuestion();
      public List<Question> queryAllQuestionByType(Integer quesType);
  	  public Question queryQuestionById(Integer quesId);
  	  public int insertQuestion(Question question);
  	  public int deleteQuestionById(Integer quesId);
  	  public int updateQuestionById(Question question);
  	  
  	  public List<Integer> queryIdByQuesTypeId(Integer quesTypeId);
  	  public void deleteQuestionByQuesTypeId(@Param("quesTypeIds") List<Integer> quesTypeIds);
  	  
  	  public List<Question> queryTenRanQuestionByType(Integer quesTypeId);
  	  public List<Question> queryQuestionByIds(@Param("quesIds") List<Integer> quesIds);
}
