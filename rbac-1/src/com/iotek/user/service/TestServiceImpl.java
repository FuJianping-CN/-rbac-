package com.iotek.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotek.user.dao.TestDao;
import com.iotek.user.po.Test;

@Service(value="testService")
public class TestServiceImpl implements TestService{
	
	@Autowired
	private TestDao testDao;

	@Override
	public List<Test> queryAllTests() {
		// TODO Auto-generated method stub
		return this.testDao.queryAllTests();
	}

	@Override
	public List<Test> queryAllTestsByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return this.testDao.queryAllTestsByUserId(userId);
	}

	@Override
	public Test queryTestById(Integer testId) {
		// TODO Auto-generated method stub
		return this.testDao.queryTestById(testId);
	}

	@Override
	public int insertTest(Test test) {
		// TODO Auto-generated method stub
		return this.testDao.insertTest(test);
	}

	@Override
	public int deleteTestById(Integer testId) {
		// TODO Auto-generated method stub
		return this.testDao.deleteTestById(testId);
	}

}
