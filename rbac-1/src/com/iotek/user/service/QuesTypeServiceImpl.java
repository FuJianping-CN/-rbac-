package com.iotek.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotek.user.dao.CategoryDao;
import com.iotek.user.dao.QuesTypeDao;
import com.iotek.user.po.Category;
import com.iotek.user.po.QuesType;

@Service(value="quesTypeService")
public class QuesTypeServiceImpl implements QuesTypeService{
	
	@Autowired
	private QuesTypeDao quesTypeDao;

	@Override
	public List<QuesType> queryAllQuesType() {
		// TODO Auto-generated method stub
		return this.quesTypeDao.queryAllQuesType();
	}

	@Override
	public QuesType queryQuesTypeById(Integer quesTypeId) {
		// TODO Auto-generated method stub
		return this.quesTypeDao.queryQuesTypeById(quesTypeId);
	}

	@Override
	public int insertQuesType(QuesType quesType) {
		// TODO Auto-generated method stub
		return this.quesTypeDao.insertQuesType(quesType);
	}

	@Override
	public int deleteQuesTypeById(Integer quesTypeId) {
		// TODO Auto-generated method stub
		return this.quesTypeDao.deleteQuesTypeById(quesTypeId);
	}

	@Override
	public int updateQuesTypeById(QuesType quesType) {
		// TODO Auto-generated method stub
		return this.quesTypeDao.updateQuesTypeById(quesType);
	}


}
