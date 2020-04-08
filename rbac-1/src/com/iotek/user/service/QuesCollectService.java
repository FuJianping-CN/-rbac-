package com.iotek.user.service;

import java.util.List;

import com.iotek.user.po.QuesCollect;

public interface QuesCollectService {
	public List<QuesCollect> queryAllQuesCollects();

	public List<QuesCollect> queryAllQuesCollectsByUserId(Integer userId);

	public QuesCollect queryQuesCollectById(Integer collectId);

	public int insertQuesCollect(QuesCollect quesCollect);
	
	public int deleteQuesCollectById(Integer collectId);
	
	public List<QuesCollect> queryQuesCollectByConditions(QuesCollect quesCollect);
}
