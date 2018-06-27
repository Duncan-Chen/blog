package com.duncan.comment;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.duncan.blog.dao.CommentVoMapper;
import com.duncan.blog.model.vo.CommentVo;
import com.duncan.blog.model.vo.CommentVoExample;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentDaoTest {
	
	@Resource
	private CommentVoMapper commentDao;
	
	@Test
	public void testSelectByExampleWithBLOB() {
		CommentVoExample example = new CommentVoExample();
		example.createCriteria().andCidEqualTo(1).andParentEqualTo(0).andStatusIsNotNull().andStatusEqualTo("approved");
		example.setOrderByClause("coid desc");
		List<CommentVo> comments = this.commentDao.selectByExampleWithBLOB(example);
		Assert.assertEquals(comments.size(), 1);
	}
	
}
