package com.iotek.user.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iotek.user.po.Question;

public interface QuestionService {
	public List<Question> queryAllQuestion();

	public List<Question> queryAllQuestionByType(Integer quesType);

	public Question queryQuestionById(Integer quesId);

	public int insertQuestion(Question question);

	public int deleteQuestionById(Integer quesId);

	public int updateQuestionById(Question question);
	
	public void deleteQuestionByQuesTypeId(List<Integer> quesTypeIds);
	
	public List<Integer> queryIdByQuesTypeId(Integer quesTypeId);
	
	public List<Question> queryTenRanQuestionByType(Integer quesTypeId);
	
	public List<Question> queryQuestionByIds(List<Integer> quesIds);
}
