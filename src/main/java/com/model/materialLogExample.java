package com.model;

import java.util.ArrayList;
import java.util.List;

public class materialLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public materialLogExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
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

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSysMaterialLogIdIsNull() {
            addCriterion("sys_material_log_id is null");
            return (Criteria) this;
        }

        public Criteria andSysMaterialLogIdIsNotNull() {
            addCriterion("sys_material_log_id is not null");
            return (Criteria) this;
        }

        public Criteria andSysMaterialLogIdEqualTo(String value) {
            addCriterion("sys_material_log_id =", value, "sysMaterialLogId");
            return (Criteria) this;
        }

        public Criteria andSysMaterialLogIdNotEqualTo(String value) {
            addCriterion("sys_material_log_id <>", value, "sysMaterialLogId");
            return (Criteria) this;
        }

        public Criteria andSysMaterialLogIdGreaterThan(String value) {
            addCriterion("sys_material_log_id >", value, "sysMaterialLogId");
            return (Criteria) this;
        }

        public Criteria andSysMaterialLogIdGreaterThanOrEqualTo(String value) {
            addCriterion("sys_material_log_id >=", value, "sysMaterialLogId");
            return (Criteria) this;
        }

        public Criteria andSysMaterialLogIdLessThan(String value) {
            addCriterion("sys_material_log_id <", value, "sysMaterialLogId");
            return (Criteria) this;
        }

        public Criteria andSysMaterialLogIdLessThanOrEqualTo(String value) {
            addCriterion("sys_material_log_id <=", value, "sysMaterialLogId");
            return (Criteria) this;
        }

        public Criteria andSysMaterialLogIdLike(String value) {
            addCriterion("sys_material_log_id like", value, "sysMaterialLogId");
            return (Criteria) this;
        }

        public Criteria andSysMaterialLogIdNotLike(String value) {
            addCriterion("sys_material_log_id not like", value, "sysMaterialLogId");
            return (Criteria) this;
        }

        public Criteria andSysMaterialLogIdIn(List<String> values) {
            addCriterion("sys_material_log_id in", values, "sysMaterialLogId");
            return (Criteria) this;
        }

        public Criteria andSysMaterialLogIdNotIn(List<String> values) {
            addCriterion("sys_material_log_id not in", values, "sysMaterialLogId");
            return (Criteria) this;
        }

        public Criteria andSysMaterialLogIdBetween(String value1, String value2) {
            addCriterion("sys_material_log_id between", value1, value2, "sysMaterialLogId");
            return (Criteria) this;
        }

        public Criteria andSysMaterialLogIdNotBetween(String value1, String value2) {
            addCriterion("sys_material_log_id not between", value1, value2, "sysMaterialLogId");
            return (Criteria) this;
        }

        public Criteria andAccountLoginIdIsNull() {
            addCriterion("account_login_id is null");
            return (Criteria) this;
        }

        public Criteria andAccountLoginIdIsNotNull() {
            addCriterion("account_login_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccountLoginIdEqualTo(String value) {
            addCriterion("account_login_id =", value, "accountLoginId");
            return (Criteria) this;
        }

        public Criteria andAccountLoginIdNotEqualTo(String value) {
            addCriterion("account_login_id <>", value, "accountLoginId");
            return (Criteria) this;
        }

        public Criteria andAccountLoginIdGreaterThan(String value) {
            addCriterion("account_login_id >", value, "accountLoginId");
            return (Criteria) this;
        }

        public Criteria andAccountLoginIdGreaterThanOrEqualTo(String value) {
            addCriterion("account_login_id >=", value, "accountLoginId");
            return (Criteria) this;
        }

        public Criteria andAccountLoginIdLessThan(String value) {
            addCriterion("account_login_id <", value, "accountLoginId");
            return (Criteria) this;
        }

        public Criteria andAccountLoginIdLessThanOrEqualTo(String value) {
            addCriterion("account_login_id <=", value, "accountLoginId");
            return (Criteria) this;
        }

        public Criteria andAccountLoginIdLike(String value) {
            addCriterion("account_login_id like", value, "accountLoginId");
            return (Criteria) this;
        }

        public Criteria andAccountLoginIdNotLike(String value) {
            addCriterion("account_login_id not like", value, "accountLoginId");
            return (Criteria) this;
        }

        public Criteria andAccountLoginIdIn(List<String> values) {
            addCriterion("account_login_id in", values, "accountLoginId");
            return (Criteria) this;
        }

        public Criteria andAccountLoginIdNotIn(List<String> values) {
            addCriterion("account_login_id not in", values, "accountLoginId");
            return (Criteria) this;
        }

        public Criteria andAccountLoginIdBetween(String value1, String value2) {
            addCriterion("account_login_id between", value1, value2, "accountLoginId");
            return (Criteria) this;
        }

        public Criteria andAccountLoginIdNotBetween(String value1, String value2) {
            addCriterion("account_login_id not between", value1, value2, "accountLoginId");
            return (Criteria) this;
        }

        public Criteria andMaterialLogDateIsNull() {
            addCriterion("material_log_date is null");
            return (Criteria) this;
        }

        public Criteria andMaterialLogDateIsNotNull() {
            addCriterion("material_log_date is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialLogDateEqualTo(String value) {
            addCriterion("material_log_date =", value, "materialLogDate");
            return (Criteria) this;
        }

        public Criteria andMaterialLogDateNotEqualTo(String value) {
            addCriterion("material_log_date <>", value, "materialLogDate");
            return (Criteria) this;
        }

        public Criteria andMaterialLogDateGreaterThan(String value) {
            addCriterion("material_log_date >", value, "materialLogDate");
            return (Criteria) this;
        }

        public Criteria andMaterialLogDateGreaterThanOrEqualTo(String value) {
            addCriterion("material_log_date >=", value, "materialLogDate");
            return (Criteria) this;
        }

        public Criteria andMaterialLogDateLessThan(String value) {
            addCriterion("material_log_date <", value, "materialLogDate");
            return (Criteria) this;
        }

        public Criteria andMaterialLogDateLessThanOrEqualTo(String value) {
            addCriterion("material_log_date <=", value, "materialLogDate");
            return (Criteria) this;
        }

        public Criteria andMaterialLogDateLike(String value) {
            addCriterion("material_log_date like", value, "materialLogDate");
            return (Criteria) this;
        }

        public Criteria andMaterialLogDateNotLike(String value) {
            addCriterion("material_log_date not like", value, "materialLogDate");
            return (Criteria) this;
        }

        public Criteria andMaterialLogDateIn(List<String> values) {
            addCriterion("material_log_date in", values, "materialLogDate");
            return (Criteria) this;
        }

        public Criteria andMaterialLogDateNotIn(List<String> values) {
            addCriterion("material_log_date not in", values, "materialLogDate");
            return (Criteria) this;
        }

        public Criteria andMaterialLogDateBetween(String value1, String value2) {
            addCriterion("material_log_date between", value1, value2, "materialLogDate");
            return (Criteria) this;
        }

        public Criteria andMaterialLogDateNotBetween(String value1, String value2) {
            addCriterion("material_log_date not between", value1, value2, "materialLogDate");
            return (Criteria) this;
        }

        public Criteria andMaterialLogTypeIsNull() {
            addCriterion("material_log_type is null");
            return (Criteria) this;
        }

        public Criteria andMaterialLogTypeIsNotNull() {
            addCriterion("material_log_type is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialLogTypeEqualTo(String value) {
            addCriterion("material_log_type =", value, "materialLogType");
            return (Criteria) this;
        }

        public Criteria andMaterialLogTypeNotEqualTo(String value) {
            addCriterion("material_log_type <>", value, "materialLogType");
            return (Criteria) this;
        }

        public Criteria andMaterialLogTypeGreaterThan(String value) {
            addCriterion("material_log_type >", value, "materialLogType");
            return (Criteria) this;
        }

        public Criteria andMaterialLogTypeGreaterThanOrEqualTo(String value) {
            addCriterion("material_log_type >=", value, "materialLogType");
            return (Criteria) this;
        }

        public Criteria andMaterialLogTypeLessThan(String value) {
            addCriterion("material_log_type <", value, "materialLogType");
            return (Criteria) this;
        }

        public Criteria andMaterialLogTypeLessThanOrEqualTo(String value) {
            addCriterion("material_log_type <=", value, "materialLogType");
            return (Criteria) this;
        }

        public Criteria andMaterialLogTypeLike(String value) {
            addCriterion("material_log_type like", value, "materialLogType");
            return (Criteria) this;
        }

        public Criteria andMaterialLogTypeNotLike(String value) {
            addCriterion("material_log_type not like", value, "materialLogType");
            return (Criteria) this;
        }

        public Criteria andMaterialLogTypeIn(List<String> values) {
            addCriterion("material_log_type in", values, "materialLogType");
            return (Criteria) this;
        }

        public Criteria andMaterialLogTypeNotIn(List<String> values) {
            addCriterion("material_log_type not in", values, "materialLogType");
            return (Criteria) this;
        }

        public Criteria andMaterialLogTypeBetween(String value1, String value2) {
            addCriterion("material_log_type between", value1, value2, "materialLogType");
            return (Criteria) this;
        }

        public Criteria andMaterialLogTypeNotBetween(String value1, String value2) {
            addCriterion("material_log_type not between", value1, value2, "materialLogType");
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

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
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

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}