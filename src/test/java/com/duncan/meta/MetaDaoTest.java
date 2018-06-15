package com.duncan.meta;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.duncan.blog.dao.MetaVoMapper;
import com.duncan.blog.model.vo.MetaVo;
import com.duncan.blog.model.vo.MetaVoExample;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MetaDaoTest {
	
	@Autowired
	private MetaVoMapper metaDao;
	
	@Test
	public void testSelectByExample() {
		MetaVoExample example = new MetaVoExample();
		example.setOrderByClause("sort desc, mid desc");
		example.createCriteria().andTypeEqualTo("link");
		List<MetaVo> metas = this.metaDao.selectByExample(example);
		Assert.assertEquals(metas.size(), 1);
	}
	
}
