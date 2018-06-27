package com.duncan.blog.dto;

public enum Types {
	
	CSRF_TOKEN("csrf_token"), 
	CATEGORY("category"), 
	ARTICLE("post"), 
	TAG("tag"),
	HITS_FREQUENCY("hits:frequency");
	
	private Types(String type) {
		this.type = type;
	}

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
