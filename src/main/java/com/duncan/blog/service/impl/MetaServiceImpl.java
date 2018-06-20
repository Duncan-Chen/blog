package com.duncan.blog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duncan.blog.dao.MetaVoMapper;
import com.duncan.blog.exception.TipException;
import com.duncan.blog.model.vo.MetaVo;
import com.duncan.blog.model.vo.MetaVoExample;
import com.duncan.blog.model.vo.RelationshipVoKey;
import com.duncan.blog.service.IMetaService;
import com.duncan.blog.service.IRelationshipService;

@Service
public class MetaServiceImpl implements IMetaService {
	
	@Resource
	private MetaVoMapper metaVoDao;
	
	@Resource
	private IRelationshipService relationshipService;

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

	@Override
	@Transactional
	public void saveMetas(Integer cid, String names, String type) {
		if (null == cid) {
			throw new TipException("关联的文章ID不能为空");
		}
		if (StringUtils.isNotBlank(names) && StringUtils.isNotBlank(type)) {
			String[] strArr = StringUtils.split(names, ",");
			for (String name : strArr) {
				this.saveOrUpdate(cid, name, type);
			}
		}
	}

	private void saveOrUpdate(Integer cid, String name, String type) {
		MetaVoExample example = new MetaVoExample();
		example.createCriteria().andNameEqualTo(name).andTypeEqualTo(type);
		List<MetaVo> metaVos = this.metaVoDao.selectByExample(example);
		int mid;
		MetaVo metaVo;
		if (metaVos.size() == 0) {
			metaVo = new MetaVo();
			metaVo.setName(name);
			metaVo.setSlug(name);
			metaVo.setType(type);
			this.metaVoDao.insertSelective(metaVo);
			mid = metaVo.getMid();
		} else if (metaVos.size() == 1) {
			metaVo = metaVos.get(0);
			mid = metaVo.getMid();
		} else {
			throw new TipException("查询到多条记录");
		}
		if (0 != mid) {
			long count = this.relationshipService.countById(cid, mid);
			if (count == 0) {
				RelationshipVoKey relationship = new RelationshipVoKey();
				relationship.setMid(mid);
				relationship.setCid(cid);
				this.relationshipService.insertVo(relationship);
			}
		}
		
	}
	
	

}
