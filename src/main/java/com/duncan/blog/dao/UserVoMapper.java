package com.duncan.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.duncan.blog.model.vo.UserVo;
import com.duncan.blog.model.vo.UserVoExample;

@Component
public interface UserVoMapper {
	long countByExample(UserVoExample example);
	
	int deleteByExample(UserVoExample example);
	
	int deleteByPrimaryKey(Integer uid);
	
	int insert(UserVo record);
	
	int insertSelective(UserVo record);
	
	List<UserVo> selectByUserVoExample(UserVoExample example);
	
	UserVo selectByPrimaryKey(Integer uid);
	
	int updateByExampleSelective(@Param("record") UserVo record, @Param("example") UserVoExample example);
	
	int updateByExample(@Param("record") UserVo record, @Param("example") UserVoExample example);
	
	int updateByPrimaryKeySelective(UserVo record);
	
	int updateByPrimaryKey(UserVo record);
	
}
