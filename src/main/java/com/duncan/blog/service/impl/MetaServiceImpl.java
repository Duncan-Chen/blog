package com.duncan.blog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.duncan.blog.dao.MetaVoMapper;
import com.duncan.blog.model.vo.MetaVo;
import com.duncan.blog.model.vo.MetaVoExample;
import com.duncan.blog.service.IMetaService;

@Service
public class MetaServiceImpl implements IMetaService {
	
	@Resource
	private MetaVoMapper metaVoDao;

	@Override
	public List<MetaVo> getMetas(String types) {
		if (StringUtils.isNotBlank(types)) {
			MetaVoExample example = new MetaVoExample();
			example.setOrderByClause("sort desc, mid desc");
			example.createCriteria().andTypeEqualTo(types);
			return metaVoDao.selectByExample(example);
		}
		return null;
	}

}
