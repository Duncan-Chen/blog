package com.duncan.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duncan.blog.dao.LogVoMapper;
import com.duncan.blog.model.vo.LogVo;
import com.duncan.blog.service.ILogService;
import com.duncan.blog.utils.DateKit;

@Service
public class ILogServiceImpl implements ILogService {
	
	@Resource
	private LogVoMapper logVoMapper;

	@Override
	public void insertLog(String action, String data, String ip,
			Integer authorId) {
		LogVo logVo = new LogVo();
		logVo.setAction(action);
		logVo.setData(data);
		logVo.setIp(ip);
		logVo.setAuthorId(authorId);
		logVo.setCreated(DateKit.getCurrentUnixTime());
		logVoMapper.insert(logVo);
	}

}
