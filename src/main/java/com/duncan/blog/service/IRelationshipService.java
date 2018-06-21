package com.duncan.blog.service;

import com.duncan.blog.model.vo.RelationshipVoKey;

public interface IRelationshipService {

	/**
	 * 按照主键统计条数
	 * @param cid
	 * @param mid
	 * @return
	 */
	Long countById(Integer cid, Integer mid);
	
	/**
	 * 保存对象
	 * @param relationshipVoKey
	 */
	void insertVo(RelationshipVoKey relationshipVoKey);

	/**
	 * 按主键删除对象
	 * @param cid
	 * @param mid
	 */
	void deleteById(Integer cid, Integer mid);

}
