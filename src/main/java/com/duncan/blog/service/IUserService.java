package com.duncan.blog.service;

import com.duncan.blog.model.vo.UserVo;

public interface IUserService {
	
	/**
	 * 通过uid查找对象
	 * @param uid
	 * @return
	 */
	UserVo queryUserById(Integer uid);

	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	UserVo login(String username, String password);

	/**
	 * 根据主键更新用户信息
	 * @param user
	 */
	void updateByUid(UserVo user);
	
}
