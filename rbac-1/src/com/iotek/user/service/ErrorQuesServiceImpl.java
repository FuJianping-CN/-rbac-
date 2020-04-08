package com.iotek.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotek.user.dao.ErrorQuesDao;
import com.iotek.user.po.ErrorQues;

@Service(value="errorQuesService")
public class ErrorQuesServiceImpl implements ErrorQuesService{
	
	@Autowired
	private ErrorQuesDao errorQuesDao;

	@Override
	public List<ErrorQues> queryErrorQuess() {
		// TODO Auto-generated method stub
		return this.errorQuesDao.queryErrorQuess();
	}

	@Override
	public List<ErrorQues> queryErrorQuesctsByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return this.errorQuesDao.queryErrorQuesctsByUserId(userId);
	}

	@Override
	public ErrorQues queryErrorQuesById(Integer errorId) {
		// TODO Auto-generated method stub
		return this.errorQuesDao.queryErrorQuesById(errorId);
	}

	@Override
	public void insertErrorQues(List<ErrorQues> errorQuess) {
		// TODO Auto-generated method stub
		this.errorQuesDao.insertErrorQues(errorQuess);
	}

	@Override
	public int deleteErrorQuesById(Integer errorId) {
		// TODO Auto-generated method stub
		return this.errorQuesDao.deleteErrorQuesById(errorId);
	}

	@Override
	public List<ErrorQues> queryQuesCollectByConditions(ErrorQues errorQues) {
		// TODO Auto-generated method stub
		return this.errorQuesDao.queryErrorQuesByConditions(errorQues);
	}
	
	

}
