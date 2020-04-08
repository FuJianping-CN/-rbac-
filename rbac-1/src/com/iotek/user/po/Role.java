package com.iotek.user.po;

import java.util.Date;

public class Role {
	private Integer roleId;
	private String roleName;
	private String roleContent;
	private Integer creator;
	private Date createDate;
	private Integer updater;
	private Date updateDate;
	public Role() {
		// TODO Auto-generated constructor stub
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleContent() {
		return roleContent;
	}
	public void setRoleContent(String roleContent) {
		this.roleContent = roleContent;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	
	public Integer getUpdater() {
		return updater;
	}
	public void setUpdater(Integer updater) {
		this.updater = updater;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName
				+ ", roleContent=" + roleContent + ", creator=" + creator
				+ ", createDate=" + createDate + ", updater=" + updater
				+ ", updateDate=" + updateDate + "]";
	}
	
}
