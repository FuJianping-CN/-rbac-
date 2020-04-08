package com.iotek.user.po;

import java.util.Date;

/**
 * 题目类型实体类
 * @author 付建平
 *
 */
public class QuesType {

	private Integer quesTypeId;
	private String quesTypeName;
	private String quesTypeContent;
	private Integer examTime;
	private Integer creator;
	private Date createDate;
	private Integer updater;
	private Date updateDate;
	public Integer getQuesTypeId() {
		return quesTypeId;
	}
	public void setQuesTypeId(Integer quesTypeId) {
		this.quesTypeId = quesTypeId;
	}
	public String getQuesTypeName() {
		return quesTypeName;
	}
	public void setQuesTypeName(String quesTypeName) {
		this.quesTypeName = quesTypeName;
	}
	
	public Integer getExamTime() {
		return examTime;
	}
	public void setExamTime(Integer examTime) {
		this.examTime = examTime;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getUpdater() {
		return updater;
	}
	public void setUpdater(Integer updater) {
		this.updater = updater;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getQuesTypeContent() {
		return quesTypeContent;
	}
	public void setQuesTypeContent(String quesTypeContent) {
		this.quesTypeContent = quesTypeContent;
	}
	@Override
	public String toString() {
		return "QuesType [quesTypeId=" + quesTypeId + ", quesTypeName=" + quesTypeName + ", quesTypeContent="
				+ quesTypeContent + ", examTime=" + examTime + ", creator=" + creator + ", createDate=" + createDate
				+ ", updater=" + updater + ", updateDate=" + updateDate + "]";
	}
	
	
	
	
}
