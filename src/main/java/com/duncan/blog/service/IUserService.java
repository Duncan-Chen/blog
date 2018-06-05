package com.duncan.blog.service;

import com.duncan.blog.model.vo.UserVo;

public interface IUserService {
	
	/**
	 * 通过uid查找对象
	 * @param uid
	 * @return
	 */
	UserVo queryUserById(Integer uid);
	
}
