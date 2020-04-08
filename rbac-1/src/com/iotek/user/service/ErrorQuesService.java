package com.iotek.user.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iotek.user.po.ErrorQues;

public interface ErrorQuesService {
	public List<ErrorQues> queryErrorQuess();

	public List<ErrorQues> queryErrorQuesctsByUserId(Integer userId);

	public ErrorQues queryErrorQuesById(Integer errorId);

	public void insertErrorQues(@Param("errorQuess") List<ErrorQues> errorQuess);

	public int deleteErrorQuesById(Integer errorId);

	public List<ErrorQues> queryQuesCollectByConditions(ErrorQues errorQues);
}
