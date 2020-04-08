package com.iotek.user.po;

import java.util.Date;

/**
 * 收藏题目
 * @author 付建平
 *
 */
public class QuesCollect {

	private Integer collectId;
	private String quesTypeName;
	private Integer quesId;
	private String quesContent;
	private Integer creator;
	private Date createDate;
	private Integer updater;
	private Date updateDate;
	public Integer getCollectId() {
		return collectId;
	}
	public void setCollectId(Integer collectId) {
		this.collectId = collectId;
	}
	public String getQuesTypeName() {
		return quesTypeName;
	}
	public void setQuesTypeName(String quesTypeName) {
		this.quesTypeName = quesTypeName;
	}
	public Integer getQuesId() {
		return quesId;
	}
	public void setQuesId(Integer quesId) {
		this.quesId = quesId;
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
	
	public String getQuesContent() {
		return quesContent;
	}
	public void setQuesContent(String quesContent) {
		this.quesContent = quesContent;
	}
	@Override
	public String toString() {
		return "QuesCollect [collectId=" + collectId + ", quesTypeName=" + quesTypeName + ", quesId=" + quesId
				+ ", quesContent=" + quesContent + ", creator=" + creator + ", createDate=" + createDate + ", updater="
				+ updater + ", updateDate=" + updateDate + "]";
	}
	
	
}
