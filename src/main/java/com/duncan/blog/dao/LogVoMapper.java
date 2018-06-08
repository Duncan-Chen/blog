package com.duncan.blog.dao;

import org.springframework.stereotype.Component;

import com.duncan.blog.model.vo.LogVo;

@Component
public interface LogVoMapper {

	int insert(LogVo logVo);

}
