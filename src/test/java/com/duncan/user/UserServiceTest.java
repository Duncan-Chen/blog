package com.duncan.user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.duncan.blog.model.vo.UserVo;
import com.duncan.blog.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	private IUserService userService;
	
	@Test
	public void testSelectByPrimaryKey() {
		UserVo user = this.userService.queryUserById(1);
		System.out.println(user.getUsername());
	}
}
