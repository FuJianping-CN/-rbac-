package com.iotek.user.po;

import java.util.Date;

/**
 * 题目实体类
 * @author 付建平
 *
 */
public class Question {

	private Integer quesId;
	private Integer quesType;
	private String quesContent;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String quesAnswer;
	private String submitAnswer;
	private Integer creator;
	private Date createDate;
	private Integer updater;
	private Date updateDate;
	public Integer getQuesId() {
		return quesId;
	}
	public void setQuesId(Integer quesId) {
		this.quesId = quesId;
	}
	public Integer getQuesType() {
		return quesType;
	}
	public void setQuesType(Integer quesType) {
		this.quesType = quesType;
	}
	public String getQuesContent() {
		return quesContent;
	}
	public void setQuesContent(String quesContent) {
		this.quesContent = quesContent;
	}
	public String getOptionA() {
		return optionA;
	}
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	public String getOptionB() {
		return optionB;
	}
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	public String getOptionC() {
		return optionC;
	}
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	public String getOptionD() {
		return optionD;
	}
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	public String getQuesAnswer() {
		return quesAnswer;
	}
	public void setQuesAnswer(String quesAnswer) {
		this.quesAnswer = quesAnswer;
	}
	
	public String getSubmitAnswer() {
		return submitAnswer;
	}
	public void setSubmitAnswer(String submitAnswer) {
		this.submitAnswer = submitAnswer;
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
	@Override
	public String toString() {
		return "Question [quesId=" + quesId + ", quesType=" + quesType + ", quesContent=" + quesContent + ", optionA="
				+ optionA + ", optionB=" + optionB + ", optionC=" + optionC + ", optionD=" + optionD + ", quesAnswer="
				+ quesAnswer + ", creator=" + creator + ", createDate=" + createDate + ", updater=" + updater
				+ ", updateDate=" + updateDate + "]";
	}
	
	
	
}
