package com.duncan.blog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.duncan.blog.dao.UserVoMapper;
import com.duncan.blog.exception.TipException;
import com.duncan.blog.model.vo.UserVo;
import com.duncan.blog.model.vo.UserVoExample;
import com.duncan.blog.model.vo.UserVoExample.Criteria;
import com.duncan.blog.service.IUserService;
import com.duncan.blog.utils.TaleUtils;

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

	@Override
	public UserVo login(String username, String password) {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			throw new TipException("用户名和密码不能为空");
		}
		UserVoExample example = new UserVoExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		long count = this.userDao.countByExample(example);
		if (count < 1) {
			throw new TipException("不存在该用户");
		}
		String pwd = TaleUtils.md5Encode(username + password);
		criteria.andPasswordEqualTo(pwd);
		List<UserVo> userVos = this.userDao.selectByExample(example);
		if (userVos.size() != 1) {
			throw new TipException("用户名或密码错误");
		}
		
		return userVos.get(0);
	}

}
