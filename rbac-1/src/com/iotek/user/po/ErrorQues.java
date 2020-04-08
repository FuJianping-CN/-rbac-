package com.iotek.user.po;

import java.sql.Timestamp;

/**
 * ¥ÌÃ‚
 * @author ∏∂Ω®∆Ω
 *
 */
public class ErrorQues {

	private Integer errorId;
	private Integer quesId;
	private String submitAnswer;
	private Integer creator;
	private Timestamp createDate;
	private Integer updater;
	private Timestamp updateDate;
	
	private Question question;
	private QuesType quesType;
	

	public QuesType getQuesType() {
		return quesType;
	}

	public void setQuesType(QuesType quesType) {
		this.quesType = quesType;
	}

	public ErrorQues() {
		super();
	}
	
	public ErrorQues(Integer quesId, String submitAnswer, Integer creator, Timestamp createDate) {
		super();
		this.quesId = quesId;
		this.submitAnswer = submitAnswer;
		this.creator = creator;
		this.createDate = createDate;
	}

	public Integer getErrorId() {
		return errorId;
	}
	public void setErrorId(Integer errorId) {
		this.errorId = errorId;
	}
	public Integer getQuesId() {
		return quesId;
	}
	public void setQuesId(Integer quesId) {
		this.quesId = quesId;
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
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Integer getUpdater() {
		return updater;
	}
	public void setUpdater(Integer updater) {
		this.updater = updater;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
	
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "ErrorQues [errorId=" + errorId + ", quesId=" + quesId + ", submitAnswer=" + submitAnswer + ", creator="
				+ creator + ", createDate=" + createDate + ", updater=" + updater + ", updateDate=" + updateDate
				+ ", question=" + question + ", quesType=" + quesType + "]";
	}

	
	
	
	
}
