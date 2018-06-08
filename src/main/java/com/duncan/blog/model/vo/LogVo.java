package com.duncan.blog.model.vo;

import java.io.Serializable;

/**
 * Log日志
 */
public class LogVo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 日志主键
	 */
	private Integer id;
	/**
	 * 产生的操作
	 */
	private String action;
	/**
	 * 产生的数据
	 */
	private String data;
	/**
	 * 日志产生的ip
	 */
	private String ip;
	/**
	 * 操作人的id
	 */
	private Integer authorId;
	/**
	 * 操作时间
	 */
	private Integer created;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public Integer getCreated() {
		return created;
	}

	public void setCreated(Integer created) {
		this.created = created;
	}
}
