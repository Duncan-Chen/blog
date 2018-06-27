package com.duncan.blog.model.vo;

import java.io.Serializable;

/**
 * 评论实体
 */
public class CommentVo implements Serializable {

	private static final long serialVersionUID = 1L;
	// comment表主键
	private Integer coid;
	// content表主键
	private Integer cid;
	// 评论创建的时间戳
	private Integer created;
	// 评论作者
	private String author;
	// 评论所属用户id
	private Integer authorId;
	// 评论所属内容作者id
	private Integer ownerId;
	// 邮件
	private String email;
	// 网址
	private String url;
	// ip地址
	private String ip;
	// 客户端
	private String agent;
	// 类型
	private String type;
	// 状态
	private String status;
	// 父级评论
	private Integer parent;
	// 内容
	private String content;

	public Integer getCoid() {
		return coid;
	}

	public void setCoid(Integer coid) {
		this.coid = coid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getCreated() {
		return created;
	}

	public void setCreated(Integer created) {
		this.created = created;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
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

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
