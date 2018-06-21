package com.duncan.blog.dao;

import org.springframework.stereotype.Component;

import com.duncan.blog.model.vo.RelationshipVoExample;
import com.duncan.blog.model.vo.RelationshipVoKey;

@Component
public interface RelationshipVoMapper {

	long countByExample(RelationshipVoExample example);

	
	int insert(RelationshipVoKey relationshipVoKey);


	int deleteByExample(RelationshipVoExample example);

}
