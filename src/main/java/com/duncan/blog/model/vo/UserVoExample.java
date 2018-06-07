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
	
	protected abstract static class GeneratedCriteria {
		private List<Criterion> criterions;
		
		public GeneratedCriteria() {
			criterions = new ArrayList<Criterion>();
		}
		
		public boolean isValid() {
			return criterions.size() > 0;
		}
		
		public List<Criterion> getCriterions() {
			return criterions;
		}

		public void setCriterions(List<Criterion> criterions) {
			this.criterions = criterions;
		}

		private void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + "cannot be null");
			}
			criterions.add(new Criterion(condition, value));
		}
		
		public Criteria andUsernameEqualTo(String value) {
			addCriterion(" username = ", value, "username");
			return (Criteria) this;
		}
		
		public Criteria andPasswordEqualTo(String value) {
			addCriterion(" password = ", value, "password");
			return (Criteria) this;
		}
		
	}
	
	public static class Criteria extends GeneratedCriteria {
		protected Criteria() {
			super();
		}
	}
	
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;
		
		public String getCondition() {
			return condition;
		}
		
		public void setCondition(String condition) {
			this.condition = condition;
		}
		
		public Object getValue() {
			return value;
		}
		
		public void setValue(Object value) {
			this.value = value;
		}
		
		public Object getSecondValue() {
			return secondValue;
		}
		
		public void setSecondValue(Object secondValue) {
			this.secondValue = secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		public void setTypeHandler(String typeHandler) {
			this.typeHandler = typeHandler;
		}
		
		protected Criterion(String condition) {
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}
		
		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
		
		protected Criterion(String condition, Object value, String typeHandler) {
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}
		
		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}
		
	}
	
}
