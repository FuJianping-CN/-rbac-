package com.iotek.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotek.user.dao.QuestionDao;
import com.iotek.user.po.Question;

@Service(value="questionService")
public class QuestionServiceImpl implements QuestionService{
	
	@Autowired
	private QuestionDao questionDao;

	@Override
	public List<Question> queryAllQuestion() {
		// TODO Auto-generated method stub
		return this.questionDao.queryAllQuestion();
	}

	@Override
	public List<Question> queryAllQuestionByType(Integer quesType) {
		// TODO Auto-generated method stub
		return this.questionDao.queryAllQuestionByType(quesType);
	}

	@Override
	public Question queryQuestionById(Integer quesId) {
		// TODO Auto-generated method stub
		return this.questionDao.queryQuestionById(quesId);
	}

	@Override
	public int insertQuestion(Question question) {
		// TODO Auto-generated method stub
		return this.questionDao.insertQuestion(question);
	}

	@Override
	public int deleteQuestionById(Integer quesId) {
		// TODO Auto-generated method stub
		return this.questionDao.deleteQuestionById(quesId);
	}

	@Override
	public int updateQuestionById(Question question) {
		// TODO Auto-generated method stub
		return this.questionDao.updateQuestionById(question);
	}

	@Override
	public void deleteQuestionByQuesTypeId(List<Integer> quesTypeIds) {
		// TODO Auto-generated method stub
		this.questionDao.deleteQuestionByQuesTypeId(quesTypeIds);
	}

	@Override
	public List<Integer> queryIdByQuesTypeId(Integer quesTypeId) {
		// TODO Auto-generated method stub
		return this.questionDao.queryIdByQuesTypeId(quesTypeId);
	}

	@Override
	public List<Question> queryTenRanQuestionByType(Integer quesTypeId) {
		// TODO Auto-generated method stub
		return this.questionDao.queryTenRanQuestionByType(quesTypeId);
	}

	@Override
	public List<Question> queryQuestionByIds(List<Integer> quesIds) {
		// TODO Auto-generated method stub
		return this.questionDao.queryQuestionByIds(quesIds);
	}


}
