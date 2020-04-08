package com.iotek.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotek.user.dao.QuesCollectDao;
import com.iotek.user.po.QuesCollect;

@Service(value="quesCollectService")
public class QuesCollectServiceImpl implements QuesCollectService{
	
	@Autowired
	private QuesCollectDao quesCollectDao;

	@Override
	public List<QuesCollect> queryAllQuesCollects() {
		// TODO Auto-generated method stub
		return this.quesCollectDao.queryAllQuesCollects();
	}

	@Override
	public List<QuesCollect> queryAllQuesCollectsByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return this.quesCollectDao.queryAllQuesCollectsByUserId(userId);
	}

	@Override
	public QuesCollect queryQuesCollectById(Integer collectId) {
		// TODO Auto-generated method stub
		return this.quesCollectDao.queryQuesCollectById(collectId);
	}

	@Override
	public int insertQuesCollect(QuesCollect quesCollect) {
		// TODO Auto-generated method stub
		return this.quesCollectDao.insertQuesCollect(quesCollect);
	}

	@Override
	public int deleteQuesCollectById(Integer collectId) {
		// TODO Auto-generated method stub
		return this.quesCollectDao.deleteQuesCollectById(collectId);
	}

	@Override
	public List<QuesCollect> queryQuesCollectByConditions(QuesCollect quesCollect) {
		// TODO Auto-generated method stub
		return this.quesCollectDao.queryQuesCollectByConditions(quesCollect);
	}

}
