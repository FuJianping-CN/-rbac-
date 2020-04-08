package com.iotek.user.po;

import java.util.Date;

public class Category {

	private Integer categId;
	private String categName;
	private String categContent;
	private Integer creator;
	private Date createDate;
	private Integer updater;
	private Date updateDate;
	public Integer getCategId() {
		return categId;
	}
	public void setCategId(Integer categId) {
		this.categId = categId;
	}
	public String getCategName() {
		return categName;
	}
	public void setCategName(String categName) {
		this.categName = categName;
	}
	public String getCategContent() {
		return categContent;
	}
	public void setCategContent(String categContent) {
		this.categContent = categContent;
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
		return "Category [categId=" + categId + ", categName=" + categName + ", categContent=" + categContent
				+ ", creator=" + creator + ", createDate=" + createDate + ", updater=" + updater + ", updateDate="
				+ updateDate + "]";
	}
	
	
}
