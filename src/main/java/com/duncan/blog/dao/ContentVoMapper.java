package com.duncan.blog.dao;

import com.duncan.blog.model.vo.ContentVo;
import com.duncan.blog.model.vo.ContentVoExample;

public interface ContentVoMapper {

	long countByExample(ContentVoExample example);

	int insert(ContentVo contentVo);

}
