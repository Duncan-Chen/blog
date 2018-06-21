package com.duncan.content;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.duncan.blog.dao.ContentVoMapper;
import com.duncan.blog.model.vo.ContentVo;
import com.duncan.blog.model.vo.ContentVoExample;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContentDaoTest {
	
	@Resource
	private ContentVoMapper contentDao;
	
	@Test
	public void testCountByExample() {
		ContentVoExample example = new ContentVoExample();
		example.createCriteria().andTypeEqualTo("page").andSlugEqualTo("about");
		long count = this.contentDao.countByExample(example);
		Assert.assertEquals(count, 1);
	}
	
	@Test
	public void testSelectByPrimaryKey() {
		ContentVo content = this.contentDao.selectByPrimaryKey(1);
		Assert.assertEquals(content.getSlug(), "about");
	}
	
	@Test
	public void testdeleteByPrimaryKey() {
		int count = this.contentDao.deleteByPrimaryKey(6);
		Assert.assertEquals(count, 1);
	}
	
}
