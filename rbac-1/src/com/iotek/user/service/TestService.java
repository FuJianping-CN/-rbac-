package com.iotek.user.service;

import java.util.List;

import com.iotek.user.po.Test;

public interface TestService {
	public List<Test> queryAllTests();

	public List<Test> queryAllTestsByUserId(Integer userId);

	public Test queryTestById(Integer testId);

	public int insertTest(Test test);
	
	public int deleteTestById(Integer testId);
}
