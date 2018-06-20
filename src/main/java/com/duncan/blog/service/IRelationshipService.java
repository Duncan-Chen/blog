package com.duncan.blog.service;

import com.duncan.blog.model.vo.RelationshipVoKey;

public interface IRelationshipService {

	Long countById(Integer cid, Integer mid);

	void insertVo(RelationshipVoKey relationshipVoKey);

}
