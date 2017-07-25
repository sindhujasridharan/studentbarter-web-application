package com.db.studentbarter.uibean;

import java.io.Serializable;
import java.util.List;

public class UserRoleBean implements Serializable {

	private static final long serialVersionUID = -4724015435011386948L;
	private Long userId;
	private String userName;
	private List<String> existingRoles;
	private List<String> newRolesToAdd;
	
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<String> getExistingRoles() {
		return existingRoles;
	}
	public void setExistingRoles(List<String> existingRoles) {
		this.existingRoles = existingRoles;
	}
	public List<String> getNewRolesToAdd() {
		return newRolesToAdd;
	}
	public void setNewRolesToAdd(List<String> newRolesToAdd) {
		this.newRolesToAdd = newRolesToAdd;
	}
	

}
