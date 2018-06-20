package com.duncan.blog.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.duncan.blog.model.vo.MetaVo;
import com.duncan.blog.model.vo.MetaVoExample;

@Component
public interface MetaVoMapper {
	
	List<MetaVo> selectByExample(MetaVoExample example);
	int insertSelective(MetaVo metaVo);
	
}
