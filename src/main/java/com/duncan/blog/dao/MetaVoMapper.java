package com.duncan.blog.dao;

import java.util.List;

import com.duncan.blog.model.vo.MetaVo;
import com.duncan.blog.model.vo.MetaVoExample;

public interface MetaVoMapper {
	
	List<MetaVo> selectByExample(MetaVoExample example);
	
}
