package com.duncan.blog.service;

public interface ILogService {

	void insertLog(String action, String data, String ip, Integer authorId);
	
}
