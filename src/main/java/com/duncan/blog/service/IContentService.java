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
	
	/**
	 * 获取分页的文章列表
	 * @param example
	 * @param page
	 * @param limit
	 * @return
	 */
	PageInfo<ContentVo> getArticlesWithPage(ContentVoExample example, int page, int limit);
	
	/**
	 * 根据文章id或slug获取文章
	 * @param id
	 * @return
	 */
	ContentVo getContent(String id);

	/**
	 * 根据主键删除文章
	 * @param cid
	 * @return
	 */
	String deleteByCid(int cid);

}
