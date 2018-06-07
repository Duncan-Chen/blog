package com.duncan.user;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.duncan.blog.dao.UserVoMapper;
import com.duncan.blog.model.vo.UserVo;
import com.duncan.blog.model.vo.UserVoExample;
import com.duncan.blog.model.vo.UserVoExample.Criteria;
import com.duncan.blog.utils.TaleUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
	
	@Autowired
	private UserVoMapper userVoDao;
	
	@Test
	public void testCountByExample() {
		UserVoExample example = new UserVoExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo("admin");
		long count = this.userVoDao.countByExample(example);
		System.out.println(count);
	}
	
	@Test
	public void testSelectByExample() {
		UserVoExample example = new UserVoExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo("admin");
		String pwd = TaleUtils.md5Encode("adminadmin1234");
		criteria.andPasswordEqualTo(pwd);
		List<UserVo> users = this.userVoDao.selectByExample(example);
		System.out.println(users.size());
	}
	
}
