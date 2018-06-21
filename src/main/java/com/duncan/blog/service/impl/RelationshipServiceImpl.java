package com.duncan.blog.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.duncan.blog.dao.RelationshipVoMapper;
import com.duncan.blog.model.vo.RelationshipVoExample;
import com.duncan.blog.model.vo.RelationshipVoExample.Criteria;
import com.duncan.blog.model.vo.RelationshipVoKey;
import com.duncan.blog.service.IRelationshipService;

@Service
public class RelationshipServiceImpl implements IRelationshipService {
	private static final Logger LOGGER = LoggerFactory.getLogger(RelationshipServiceImpl.class);

	@Resource
	private RelationshipVoMapper relationshipDao;
	
	@Override
	public Long countById(Integer cid, Integer mid) {
		LOGGER.debug("enter countById method:cid={}, mid={}", cid, mid);
		RelationshipVoExample example = new RelationshipVoExample();
		Criteria createCriteria = example.createCriteria();
		if (cid != null) {
			createCriteria.andCidEqualTo(cid);
		}
		if (mid != null) {
			createCriteria.andMidEqualTo(mid);
		}
		long count = this.relationshipDao.countByExample(example);
		LOGGER.debug("exit countById method return count={}", count);
		return count;
	}

	@Override
	public void insertVo(RelationshipVoKey relationshipVoKey) {
		this.relationshipDao.insert(relationshipVoKey);
	}

	@Override
	public void deleteById(Integer cid, Integer mid) {
		RelationshipVoExample example = new RelationshipVoExample();
		Criteria criteria = example.createCriteria();
		if (cid != null) {
			criteria.andCidEqualTo(cid);
		}
		if (mid != null) {
			criteria.andMidEqualTo(mid);
		}
		this.relationshipDao.deleteByExample(example);
	}

}
