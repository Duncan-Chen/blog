package com.duncan.blog.model.bo;

import java.util.List;

import com.duncan.blog.model.vo.CommentVo;

public class CommentBo extends CommentVo {

	private static final long serialVersionUID = 1L;
	private int levels;
	private List<CommentVo> children;

	public CommentBo(CommentVo commentVo) {
		setCoid(commentVo.getCoid());
		setCid(commentVo.getCid());
		setCreated(commentVo.getCreated());
		setAuthor(commentVo.getAuthor());
		setAuthorId(commentVo.getAuthorId());
		setOwnerId(commentVo.getOwnerId());
		setEmail(commentVo.getEmail());
		setUrl(commentVo.getUrl());
		setAgent(commentVo.getAgent());
		setIp(commentVo.getIp());
		setContent(commentVo.getContent());
	}

	public int getLevels() {
		return levels;
	}

	public void setLevels(int levels) {
		this.levels = levels;
	}

	public List<CommentVo> getChildren() {
		return children;
	}

	public void setChildren(List<CommentVo> children) {
		this.children = children;
	}

}
