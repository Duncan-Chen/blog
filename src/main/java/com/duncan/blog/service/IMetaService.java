package com.duncan.blog.service;

import java.util.List;

import com.duncan.blog.model.vo.MetaVo;

public interface IMetaService {
	
	List<MetaVo> getMetas(String types);

	void saveMetas(Integer cid, String names, String type);
	
}
