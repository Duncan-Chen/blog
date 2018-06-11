package com.duncan.blog.controller;

import javax.servlet.http.HttpServletRequest;

import com.duncan.blog.model.vo.UserVo;
import com.duncan.blog.utils.MapCache;
import com.duncan.blog.utils.TaleUtils;

public abstract class BaseController {
	
	protected MapCache cache = MapCache.single();
	
	public UserVo user(HttpServletRequest request) {
		return TaleUtils.getLoginUser(request);
	}
	
}
