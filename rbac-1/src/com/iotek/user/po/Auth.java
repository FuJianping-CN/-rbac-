package com.iotek.user.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Auth {
//	private Integer id;
//	private String name;
	private Integer authId;
	private String authName;
	private String authUrl;
	private Integer authParentRoot;
	private String authIsRoot;
	private Integer creator;
	private Date createDate;
	private Integer updater;
	private Date updateDate;
	private String icon;
	private boolean open=true;
	private List<Auth> children=new ArrayList();
	public Auth() {
		// TODO Auto-generated constructor stub
	}
//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
	
	public String getAuthUrl() {
		return authUrl;
	}
	public Integer getAuthId() {
		return authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}
	public Integer getAuthParentRoot() {
		return authParentRoot;
	}
	public void setAuthParentRoot(Integer authParentRoot) {
		this.authParentRoot = authParentRoot;
	}
	public String getAuthIsRoot() {
		return authIsRoot;
	}
	public void setAuthIsRoot(String authIsRoot) {
		this.authIsRoot = authIsRoot;
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

	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public List<Auth> getChildren() {
		return children;
	}
	public void setChildren(List<Auth> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Auth [authId=" + authId + ", authName=" + authName + ", authUrl=" + authUrl + ", authParentRoot="
				+ authParentRoot + ", authIsRoot=" + authIsRoot + ", creator=" + creator + ", createDate=" + createDate
				+ ", updater=" + updater + ", updateDate=" + updateDate + ", icon=" + icon + ", open=" + open
				+ ", children=" + children + "]";
	}
	
	
}
