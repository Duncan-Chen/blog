package com.duncan.relationship;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.duncan.blog.dao.RelationshipVoMapper;
import com.duncan.blog.model.vo.RelationshipVoExample;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RelationshipDaoTest {
	
	@Resource
	private RelationshipVoMapper relationshipDao;
	
	@Test
	public void testDeleteByExample() {
		RelationshipVoExample example = new RelationshipVoExample();
		example.createCriteria().andCidEqualTo(6);
		this.relationshipDao.deleteByExample(example);
	}
	
}
