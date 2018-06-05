package com.duncan.blog.model.vo;

import java.util.ArrayList;
import java.util.List;

public class UserVoExample {
	protected String orderByClause;
	protected boolean distinct;
	protected List<Criteria> oredCriteria;
	private Integer limit;
	private Integer offset;
	
	public UserVoExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public boolean isDistinct() {
		return distinct;
	}
	
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}
	
	public void or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
	}
	
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	
	protected static class GeneratedCriteria {
		private List<Criterion> criterion;
		
	}
	
	public static class Criteria extends GeneratedCriteria {
		
	}
	
	public static class Criterion {
		
	}
	
}
