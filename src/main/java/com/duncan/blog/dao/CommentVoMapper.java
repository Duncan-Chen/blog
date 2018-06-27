package com.duncan.blog.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.duncan.blog.model.vo.CommentVo;
import com.duncan.blog.model.vo.CommentVoExample;

@Component
public interface CommentVoMapper {

	/**
	 * 根据提供的条件查找评论
	 * @param example
	 * @return
	 */
	List<CommentVo> selectByExampleWithBLOB(CommentVoExample example);

}
