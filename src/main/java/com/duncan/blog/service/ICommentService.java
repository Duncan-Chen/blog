package com.duncan.blog.service;

import com.duncan.blog.model.bo.CommentBo;
import com.github.pagehelper.PageInfo;

public interface ICommentService {

	/**
	 * 获取文章下的评论
	 * @param cid
	 * @param page
	 * @param limit
	 * @return
	 */
	PageInfo<CommentBo> getComments(Integer cid, int page, int limit);
	
}
