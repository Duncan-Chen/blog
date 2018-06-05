package com.duncan.blog.model.vo;

import java.io.Serializable;

/**
 * 内容
 * @author Administrator
 *
 */
public class ContentVo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * post表主键
	 */
	private Integer cid;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 缩略名
	 */
	private String slug;
	
	/**
	 * 生成的GMT，unix时间戳
	 */
	private Integer created;
	
	/**
	 * 修改时的GMT， unix时间戳
	 */
	private Integer modified;
	
	/**
	 * 所属用户id
	 */
	private Integer authorId;
	
	/**
	 * 类型
	 */
	private String type;
	
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 标签列表
	 */
	private String tags;
	
	/**
	 * 分类列表
	 */
	private String categories;
	
	/**
	 * 点击次数
	 */
	private Integer hits;
	
	/**
	 * 所属评论数
	 */
	private Integer commentsNum;
	
	/**
	 * 是否允许评论
	 */
	private Boolean allowComment;
	
	/**
	 * 是否允许ping
	 */
	private Boolean allowPing;
	
	/**
	 * 是否允许聚合
	 */
	private Boolean allowFeed;
	
	/**
	 * 文字
	 */
	private String content;

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Integer getCreated() {
		return created;
	}

	public void setCreated(Integer created) {
		this.created = created;
	}

	public Integer getModified() {
		return modified;
	}

	public void setModified(Integer modified) {
		this.modified = modified;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public Integer getCommentsNum() {
		return commentsNum;
	}

	public void setCommentsNum(Integer commentsNum) {
		this.commentsNum = commentsNum;
	}

	public Boolean getAllowComment() {
		return allowComment;
	}

	public void setAllowComment(Boolean allowComment) {
		this.allowComment = allowComment;
	}

	public Boolean getAllowPing() {
		return allowPing;
	}

	public void setAllowPing(Boolean allowPing) {
		this.allowPing = allowPing;
	}

	public Boolean getAllowFeed() {
		return allowFeed;
	}

	public void setAllowFeed(Boolean allowFeed) {
		this.allowFeed = allowFeed;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
