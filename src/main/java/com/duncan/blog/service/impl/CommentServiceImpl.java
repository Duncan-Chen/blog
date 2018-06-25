package com.duncan.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duncan.blog.dao.CommentVoMapper;
import com.duncan.blog.model.bo.CommentBo;
import com.duncan.blog.service.ICommentService;
import com.github.pagehelper.PageInfo;

@Service
public class CommentServiceImpl implements ICommentService {

	@Resource
	private CommentVoMapper commentDao;
	
	@Override
	public PageInfo<CommentBo> getComments(Integer cid, int page, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

}
