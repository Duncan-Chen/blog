package com.duncan.blog.dto;

public enum LogActions {
	
	LOGIN("登录后台");
	
	private String action;

	LogActions(String action) {
		this.action = action;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
