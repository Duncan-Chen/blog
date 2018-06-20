package com.duncan.blog.model.vo;

import java.io.Serializable;

public class RelationshipVoKey implements Serializable {

	private static final long serialVersionUID = 1L;
	private int mid;
	private int cid;

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

}
