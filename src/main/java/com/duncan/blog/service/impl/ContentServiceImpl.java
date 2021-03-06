package com.duncan.blog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duncan.blog.constant.WebConst;
import com.duncan.blog.dao.ContentVoMapper;
import com.duncan.blog.dto.Types;
import com.duncan.blog.exception.TipException;
import com.duncan.blog.model.vo.ContentVo;
import com.duncan.blog.model.vo.ContentVoExample;
import com.duncan.blog.service.IContentService;
import com.duncan.blog.service.IMetaService;
import com.duncan.blog.service.IRelationshipService;
import com.duncan.blog.utils.DateKit;
import com.duncan.blog.utils.TaleUtils;
import com.duncan.blog.utils.Tools;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vdurmont.emoji.EmojiParser;

@Service
public class ContentServiceImpl implements IContentService {

	@Resource
	private ContentVoMapper contentDao;
	
	@Resource
	private IMetaService metaService;
	
	@Resource
	private IRelationshipService relationshipService;
	
	@Override
	@Transactional
	public String publish(ContentVo contentVo) {
		if (null == contentVo) {
			return "文章对象不能为空";
		}
		if (StringUtils.isBlank(contentVo.getTitle())) {
			return "文章标题不能为空";
		}
		if (StringUtils.isBlank(contentVo.getContent())) {
			return "文章内容不能为空";
		}
		int titleLength = contentVo.getTitle().length();
		if (titleLength > WebConst.MAX_TITLE_COUNT) {
			return "文章标题过长";
		}
		int contentLength = contentVo.getContent().length();
		if (contentLength > WebConst.MAX_TEXT_COUNT) {
			return "文章内容过长";
		}
		if (StringUtils.isNotBlank(contentVo.getSlug())) {
			if (contentVo.getSlug().length() < 5) {
				return "路径长度太短";
			}
			if (!TaleUtils.isPath(contentVo.getSlug())) {
				return "输入的路径不合法";
			}
			ContentVoExample example = new ContentVoExample();
			example.createCriteria().andTypeEqualTo(contentVo.getType()).andSlugEqualTo(contentVo.getSlug());
			long count = this.contentDao.countByExample(example);
			if (count > 0) {
				return "该路径已存在，请重新输入";
			}
		} else {
			contentVo.setSlug(null);
		}
		int time = DateKit.getCurrentUnixTime();
		contentVo.setCreated(time);
		contentVo.setModified(time);
		contentVo.setHits(0);
		contentVo.setCommentsNum(0);
		
		String tags = contentVo.getTags();
		String categories = contentVo.getCategories();
		this.contentDao.insert(contentVo);
		Integer cid = contentVo.getCid();
		this.metaService.saveMetas(cid, tags, Types.TAG.getType());
		this.metaService.saveMetas(cid, categories, Types.CATEGORY.getType());
		return WebConst.SUCCESS_RESULT;
	}

	@Override
	public PageInfo<ContentVo> getArticlesWithPage(ContentVoExample example,
			int page, int limit) {
		PageHelper.startPage(page, limit);
		List<ContentVo> contentVos = this.contentDao.selectByExampleWithBlob(example);
		return new PageInfo<>(contentVos);
	}
	
	@Override
	public ContentVo getContent(String id) {
		if (StringUtils.isNotBlank(id)) {
			if (Tools.isNumber(id)) {
				ContentVo contentVo = this.contentDao.selectByPrimaryKey(Integer.valueOf(id));
				return contentVo;
			} else {
				ContentVoExample example = new ContentVoExample();
				example.createCriteria().andSlugEqualTo(id);
				List<ContentVo> contentVos = this.contentDao.selectByExampleWithBlob(example);
				if (contentVos.size() > 1) {
					throw new TipException("query content by id and return is not one");
				}
				return contentVos.get(0);
			}
		}
		return null;
	}

	@Override
	@Transactional
	public String deleteByCid(int cid) {
		ContentVo contentVo = this.getContent(cid + "");
		if (null != contentVo) {
			this.contentDao.deleteByPrimaryKey(cid);
			this.relationshipService.deleteById(cid, null);
			return WebConst.SUCCESS_RESULT;
		}
		return "数据为空";
	}
	
	@Override
	@Transactional
	public String modify(ContentVo contentVo) {
		if (null == contentVo) {
			return "文章对象不能为空";
		}
		if (StringUtils.isBlank(contentVo.getTitle())) {
			return "文章标题不能为空";
		}
		if (StringUtils.isBlank(contentVo.getContent())) {
			return "文章内容不能为空";
		}
		int titleLength = contentVo.getTitle().length();
		if (titleLength > WebConst.MAX_TITLE_COUNT) {
			return "文章标题过长";
		}
		int contentLength = contentVo.getContent().length();
		if (contentLength > WebConst.MAX_TEXT_COUNT) {
			return "文章内容过长";
		}
		if (null == contentVo.getAuthorId()) {
			return "请登录后再发布文章";
		}
		if (StringUtils.isBlank(contentVo.getSlug())) {
			contentVo.setSlug(null);
		}
		Integer time = DateKit.getCurrentUnixTime();
		contentVo.setModified(time);
		Integer cid = contentVo.getCid();
		contentVo.setContent(EmojiParser.parseToAliases(contentVo.getContent()));
		this.contentDao.updateByPrimaryKeySelective(contentVo);
		this.relationshipService.deleteById(cid, null);
		this.metaService.saveMetas(cid, contentVo.getTags(), Types.TAG.getType());
		this.metaService.saveMetas(cid, contentVo.getCategories(), Types.CATEGORY.getType());
		return WebConst.SUCCESS_RESULT;
	}

	@Override
	public void updateContentByCid(ContentVo contentVo) {
		if (null != contentVo && contentVo.getCid() != null) {
			this.contentDao.updateByPrimaryKeySelective(contentVo);
		}
	}

}
