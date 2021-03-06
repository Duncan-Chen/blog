package com.duncan.blog.controller;

import javax.servlet.http.HttpServletRequest;

import com.duncan.blog.model.vo.UserVo;
import com.duncan.blog.utils.MapCache;
import com.duncan.blog.utils.TaleUtils;

public abstract class BaseController {
	
	public static String THEME = "themes/default";
	protected MapCache cache = MapCache.single();
	
	public UserVo user(HttpServletRequest request) {
		return TaleUtils.getLoginUser(request);
	}
	
	public Integer getUid(HttpServletRequest request) {
		return this.user(request).getUid();
	}
	
	public String render_404() {
		return "comm/error_404";
	}
	
	public String render(String viewName) {
		return THEME + "/" + viewName;
	}
	
}
