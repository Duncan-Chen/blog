package com.duncan.blog.dao;

import java.util.List;

import com.duncan.blog.model.vo.ContentVo;
import com.duncan.blog.model.vo.ContentVoExample;

public interface ContentVoMapper {

	long countByExample(ContentVoExample example);

	int insert(ContentVo contentVo);

	List<ContentVo> selectByExampleWithBlob(ContentVoExample example);

	ContentVo selectByPrimaryKey(Integer cid);

	int deleteByPrimaryKey(Integer cid);

	int updateByPrimaryKeySelective(ContentVo contentVo);

}
