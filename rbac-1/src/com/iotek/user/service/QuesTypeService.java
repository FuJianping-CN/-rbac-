package com.iotek.user.service;

import java.util.List;

import com.iotek.user.po.QuesType;

public interface QuesTypeService {
	public List<QuesType> queryAllQuesType();
	  public QuesType queryQuesTypeById(Integer quesTypeId);
	  public int insertQuesType(QuesType quesType);
	  public int deleteQuesTypeById(Integer quesTypeId);
	  public int updateQuesTypeById(QuesType quesType);
}
