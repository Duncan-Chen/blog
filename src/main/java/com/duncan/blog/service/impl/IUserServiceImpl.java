package com.duncan.blog.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.duncan.blog.dao.UserVoMapper;
import com.duncan.blog.model.vo.UserVo;
import com.duncan.blog.service.IUserService;

@Service
public class IUserServiceImpl implements IUserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(IUserServiceImpl.class);
	
	@Resource
	private UserVoMapper userDao;
	
	@Override
	public UserVo queryUserById(Integer uid) {
		UserVo userVo = null;
		if (uid != null) {
			userVo = this.userDao.selectByPrimaryKey(uid);
		}
		LOGGER.info("通过主键uid查询user成功");
		return userVo;
	}

}
