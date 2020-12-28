package com.tongji.model;

import java.util.ArrayList;
import java.util.List;

public class MovieExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MovieExample() {
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

        public Criteria andMovieIdIsNull() {
            addCriterion("movie_id is null");
            return (Criteria) this;
        }

        public Criteria andMovieIdIsNotNull() {
            addCriterion("movie_id is not null");
            return (Criteria) this;
        }

        public Criteria andMovieIdEqualTo(Integer value) {
            addCriterion("movie_id =", value, "movieId");
            return (Criteria) this;
        }

        public Criteria andMovieIdNotEqualTo(Integer value) {
            addCriterion("movie_id <>", value, "movieId");
            return (Criteria) this;
        }

        public Criteria andMovieIdGreaterThan(Integer value) {
            addCriterion("movie_id >", value, "movieId");
            return (Criteria) this;
        }

        public Criteria andMovieIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("movie_id >=", value, "movieId");
            return (Criteria) this;
        }

        public Criteria andMovieIdLessThan(Integer value) {
            addCriterion("movie_id <", value, "movieId");
            return (Criteria) this;
        }

        public Criteria andMovieIdLessThanOrEqualTo(Integer value) {
            addCriterion("movie_id <=", value, "movieId");
            return (Criteria) this;
        }

        public Criteria andMovieIdIn(List<Integer> values) {
            addCriterion("movie_id in", values, "movieId");
            return (Criteria) this;
        }

        public Criteria andMovieIdNotIn(List<Integer> values) {
            addCriterion("movie_id not in", values, "movieId");
            return (Criteria) this;
        }

        public Criteria andMovieIdBetween(Integer value1, Integer value2) {
            addCriterion("movie_id between", value1, value2, "movieId");
            return (Criteria) this;
        }

        public Criteria andMovieIdNotBetween(Integer value1, Integer value2) {
            addCriterion("movie_id not between", value1, value2, "movieId");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(String value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(String value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(String value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(String value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(String value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLike(String value) {
            addCriterion("product_id like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotLike(String value) {
            addCriterion("product_id not like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<String> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<String> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(String value1, String value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(String value1, String value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andTimeIdIsNull() {
            addCriterion("time_id is null");
            return (Criteria) this;
        }

        public Criteria andTimeIdIsNotNull() {
            addCriterion("time_id is not null");
            return (Criteria) this;
        }

        public Criteria andTimeIdEqualTo(Integer value) {
            addCriterion("time_id =", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdNotEqualTo(Integer value) {
            addCriterion("time_id <>", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdGreaterThan(Integer value) {
            addCriterion("time_id >", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("time_id >=", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdLessThan(Integer value) {
            addCriterion("time_id <", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdLessThanOrEqualTo(Integer value) {
            addCriterion("time_id <=", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdIn(List<Integer> values) {
            addCriterion("time_id in", values, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdNotIn(List<Integer> values) {
            addCriterion("time_id not in", values, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdBetween(Integer value1, Integer value2) {
            addCriterion("time_id between", value1, value2, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("time_id not between", value1, value2, "timeId");
            return (Criteria) this;
        }

        public Criteria andRunTimeIsNull() {
            addCriterion("run_time is null");
            return (Criteria) this;
        }

        public Criteria andRunTimeIsNotNull() {
            addCriterion("run_time is not null");
            return (Criteria) this;
        }

        public Criteria andRunTimeEqualTo(String value) {
            addCriterion("run_time =", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeNotEqualTo(String value) {
            addCriterion("run_time <>", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeGreaterThan(String value) {
            addCriterion("run_time >", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeGreaterThanOrEqualTo(String value) {
            addCriterion("run_time >=", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeLessThan(String value) {
            addCriterion("run_time <", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeLessThanOrEqualTo(String value) {
            addCriterion("run_time <=", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeLike(String value) {
            addCriterion("run_time like", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeNotLike(String value) {
            addCriterion("run_time not like", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeIn(List<String> values) {
            addCriterion("run_time in", values, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeNotIn(List<String> values) {
            addCriterion("run_time not in", values, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeBetween(String value1, String value2) {
            addCriterion("run_time between", value1, value2, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeNotBetween(String value1, String value2) {
            addCriterion("run_time not between", value1, value2, "runTime");
            return (Criteria) this;
        }

        public Criteria andReleaseDateIsNull() {
            addCriterion("release_date is null");
            return (Criteria) this;
        }

        public Criteria andReleaseDateIsNotNull() {
            addCriterion("release_date is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseDateEqualTo(String value) {
            addCriterion("release_date =", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateNotEqualTo(String value) {
            addCriterion("release_date <>", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateGreaterThan(String value) {
            addCriterion("release_date >", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateGreaterThanOrEqualTo(String value) {
            addCriterion("release_date >=", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateLessThan(String value) {
            addCriterion("release_date <", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateLessThanOrEqualTo(String value) {
            addCriterion("release_date <=", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateLike(String value) {
            addCriterion("release_date like", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateNotLike(String value) {
            addCriterion("release_date not like", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateIn(List<String> values) {
            addCriterion("release_date in", values, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateNotIn(List<String> values) {
            addCriterion("release_date not in", values, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateBetween(String value1, String value2) {
            addCriterion("release_date between", value1, value2, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateNotBetween(String value1, String value2) {
            addCriterion("release_date not between", value1, value2, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableIsNull() {
            addCriterion("date_first_available is null");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableIsNotNull() {
            addCriterion("date_first_available is not null");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableEqualTo(String value) {
            addCriterion("date_first_available =", value, "dateFirstAvailable");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableNotEqualTo(String value) {
            addCriterion("date_first_available <>", value, "dateFirstAvailable");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableGreaterThan(String value) {
            addCriterion("date_first_available >", value, "dateFirstAvailable");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableGreaterThanOrEqualTo(String value) {
            addCriterion("date_first_available >=", value, "dateFirstAvailable");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableLessThan(String value) {
            addCriterion("date_first_available <", value, "dateFirstAvailable");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableLessThanOrEqualTo(String value) {
            addCriterion("date_first_available <=", value, "dateFirstAvailable");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableLike(String value) {
            addCriterion("date_first_available like", value, "dateFirstAvailable");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableNotLike(String value) {
            addCriterion("date_first_available not like", value, "dateFirstAvailable");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableIn(List<String> values) {
            addCriterion("date_first_available in", values, "dateFirstAvailable");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableNotIn(List<String> values) {
            addCriterion("date_first_available not in", values, "dateFirstAvailable");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableBetween(String value1, String value2) {
            addCriterion("date_first_available between", value1, value2, "dateFirstAvailable");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableNotBetween(String value1, String value2) {
            addCriterion("date_first_available not between", value1, value2, "dateFirstAvailable");
            return (Criteria) this;
        }

        public Criteria andStarIsNull() {
            addCriterion("star is null");
            return (Criteria) this;
        }

        public Criteria andStarIsNotNull() {
            addCriterion("star is not null");
            return (Criteria) this;
        }

        public Criteria andStarEqualTo(Double value) {
            addCriterion("star =", value, "star");
            return (Criteria) this;
        }

        public Criteria andStarNotEqualTo(Double value) {
            addCriterion("star <>", value, "star");
            return (Criteria) this;
        }

        public Criteria andStarGreaterThan(Double value) {
            addCriterion("star >", value, "star");
            return (Criteria) this;
        }

        public Criteria andStarGreaterThanOrEqualTo(Double value) {
            addCriterion("star >=", value, "star");
            return (Criteria) this;
        }

        public Criteria andStarLessThan(Double value) {
            addCriterion("star <", value, "star");
            return (Criteria) this;
        }

        public Criteria andStarLessThanOrEqualTo(Double value) {
            addCriterion("star <=", value, "star");
            return (Criteria) this;
        }

        public Criteria andStarIn(List<Double> values) {
            addCriterion("star in", values, "star");
            return (Criteria) this;
        }

        public Criteria andStarNotIn(List<Double> values) {
            addCriterion("star not in", values, "star");
            return (Criteria) this;
        }

        public Criteria andStarBetween(Double value1, Double value2) {
            addCriterion("star between", value1, value2, "star");
            return (Criteria) this;
        }

        public Criteria andStarNotBetween(Double value1, Double value2) {
            addCriterion("star not between", value1, value2, "star");
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