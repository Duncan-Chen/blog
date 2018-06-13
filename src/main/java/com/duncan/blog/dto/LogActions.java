package com.duncan.blog.dto;

public enum LogActions {
	
	LOGIN("登录后台"), UP_INFO("修改个人信息"), UP_PWD("修改密码");
	
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
