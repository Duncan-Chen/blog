package com.duncan.blog.service;

import com.duncan.blog.model.vo.ContentVo;

public interface IContentService {

	/**
	 * 发布文章
	 * @param contentVo
	 * @return
	 */
	String publish(ContentVo contentVo);

}
