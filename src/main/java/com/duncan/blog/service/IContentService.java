package com.duncan.blog.service;

import com.duncan.blog.model.vo.ContentVo;
import com.duncan.blog.model.vo.ContentVoExample;
import com.github.pagehelper.PageInfo;

public interface IContentService {

	/**
	 * 发布文章
	 * @param contentVo
	 * @return
	 */
	String publish(ContentVo contentVo);

	PageInfo<ContentVo> getArticlesWithPage(ContentVoExample example, int page, int limit);

}
