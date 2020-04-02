package com.anz.ws.request.model;

public class ServiceRequest {
	private String userName;
	private String userLogin;
	private String parentFolderId;
	private String childFolderName;
	private String collaborationByUserLogin;
	public String getParentFolderId() {
		return parentFolderId;
	}
	public void setParentFolderId(String parentFolderId) {
		this.parentFolderId = parentFolderId;
	}
	public String getChildFolderName() {
		return childFolderName;
	}
	public void setChildFolderName(String childFolderName) {
		this.childFolderName = childFolderName;
	}
	public String getCollaborationByUserLogin() {
		return collaborationByUserLogin;
	}
	public void setCollaborationByUserLogin(String collaborationByUserLogin) {
		this.collaborationByUserLogin = collaborationByUserLogin;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

}
