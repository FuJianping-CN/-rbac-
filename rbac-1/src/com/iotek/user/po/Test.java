package com.iotek.user.po;

import java.util.Date;

/**
 * ÊÔ¾í±í
 * @author ¸¶½¨Æ½
 *
 */
public class Test {

	private Integer testId;
	private String quesTypeName;
	private Integer grade;
	private String quesIds;
	private String submitAnswer;
	private String answer;
	private Integer creator;
	private Date createDate;
	private Integer updater;
	private Date updateDate;
	
	public Test() {
		super();
	}
	
	public Test(String quesTypeName, Integer grade, Integer creator, Date createDate) {
		super();
		this.quesTypeName = quesTypeName;
		this.grade = grade;
		this.creator = creator;
		this.createDate = createDate;
	}
	
	

	public Test(String quesTypeName, Integer grade, String quesIds, String submitAnswer, Integer creator,
			Date createDate) {
		super();
		this.quesTypeName = quesTypeName;
		this.grade = grade;
		this.quesIds = quesIds;
		this.submitAnswer = submitAnswer;
		this.creator = creator;
		this.createDate = createDate;
	}

	public Integer getTestId() {
		return testId;
	}
	public void setTestId(Integer testId) {
		this.testId = testId;
	}
	
	public String getQuesTypeName() {
		return quesTypeName;
	}

	public void setQuesTypeName(String quesTypeName) {
		this.quesTypeName = quesTypeName;
	}

	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	public String getQuesIds() {
		return quesIds;
	}

	public void setQuesIds(String quesIds) {
		this.quesIds = quesIds;
	}

	public String getSubmitAnswer() {
		return submitAnswer;
	}

	public void setSubmitAnswer(String submitAnswer) {
		this.submitAnswer = submitAnswer;
	}

	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
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
		return "Test [testId=" + testId + ", quesTypeName=" + quesTypeName + ", grade=" + grade + ", quesIds=" + quesIds
				+ ", submitAnswer=" + submitAnswer + ", answer=" + answer + ", creator=" + creator + ", createDate="
				+ createDate + ", updater=" + updater + ", updateDate=" + updateDate + "]";
	}

	
	
	
	
}
