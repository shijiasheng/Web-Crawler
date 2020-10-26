package com.tongji.model;

import java.util.ArrayList;
import java.util.List;

public class ProductdataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductdataExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andReleaseDateIsNull() {
            addCriterion("`release date` is null");
            return (Criteria) this;
        }

        public Criteria andReleaseDateIsNotNull() {
            addCriterion("`release date` is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseDateEqualTo(String value) {
            addCriterion("`release date` =", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateNotEqualTo(String value) {
            addCriterion("`release date` <>", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateGreaterThan(String value) {
            addCriterion("`release date` >", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateGreaterThanOrEqualTo(String value) {
            addCriterion("`release date` >=", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateLessThan(String value) {
            addCriterion("`release date` <", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateLessThanOrEqualTo(String value) {
            addCriterion("`release date` <=", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateLike(String value) {
            addCriterion("`release date` like", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateNotLike(String value) {
            addCriterion("`release date` not like", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateIn(List<String> values) {
            addCriterion("`release date` in", values, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateNotIn(List<String> values) {
            addCriterion("`release date` not in", values, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateBetween(String value1, String value2) {
            addCriterion("`release date` between", value1, value2, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateNotBetween(String value1, String value2) {
            addCriterion("`release date` not between", value1, value2, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andGenresIsNull() {
            addCriterion("genres is null");
            return (Criteria) this;
        }

        public Criteria andGenresIsNotNull() {
            addCriterion("genres is not null");
            return (Criteria) this;
        }

        public Criteria andGenresEqualTo(String value) {
            addCriterion("genres =", value, "genres");
            return (Criteria) this;
        }

        public Criteria andGenresNotEqualTo(String value) {
            addCriterion("genres <>", value, "genres");
            return (Criteria) this;
        }

        public Criteria andGenresGreaterThan(String value) {
            addCriterion("genres >", value, "genres");
            return (Criteria) this;
        }

        public Criteria andGenresGreaterThanOrEqualTo(String value) {
            addCriterion("genres >=", value, "genres");
            return (Criteria) this;
        }

        public Criteria andGenresLessThan(String value) {
            addCriterion("genres <", value, "genres");
            return (Criteria) this;
        }

        public Criteria andGenresLessThanOrEqualTo(String value) {
            addCriterion("genres <=", value, "genres");
            return (Criteria) this;
        }

        public Criteria andGenresLike(String value) {
            addCriterion("genres like", value, "genres");
            return (Criteria) this;
        }

        public Criteria andGenresNotLike(String value) {
            addCriterion("genres not like", value, "genres");
            return (Criteria) this;
        }

        public Criteria andGenresIn(List<String> values) {
            addCriterion("genres in", values, "genres");
            return (Criteria) this;
        }

        public Criteria andGenresNotIn(List<String> values) {
            addCriterion("genres not in", values, "genres");
            return (Criteria) this;
        }

        public Criteria andGenresBetween(String value1, String value2) {
            addCriterion("genres between", value1, value2, "genres");
            return (Criteria) this;
        }

        public Criteria andGenresNotBetween(String value1, String value2) {
            addCriterion("genres not between", value1, value2, "genres");
            return (Criteria) this;
        }

        public Criteria andDirectorIsNull() {
            addCriterion("director is null");
            return (Criteria) this;
        }

        public Criteria andDirectorIsNotNull() {
            addCriterion("director is not null");
            return (Criteria) this;
        }

        public Criteria andDirectorEqualTo(String value) {
            addCriterion("director =", value, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorNotEqualTo(String value) {
            addCriterion("director <>", value, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorGreaterThan(String value) {
            addCriterion("director >", value, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorGreaterThanOrEqualTo(String value) {
            addCriterion("director >=", value, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorLessThan(String value) {
            addCriterion("director <", value, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorLessThanOrEqualTo(String value) {
            addCriterion("director <=", value, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorLike(String value) {
            addCriterion("director like", value, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorNotLike(String value) {
            addCriterion("director not like", value, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorIn(List<String> values) {
            addCriterion("director in", values, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorNotIn(List<String> values) {
            addCriterion("director not in", values, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorBetween(String value1, String value2) {
            addCriterion("director between", value1, value2, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorNotBetween(String value1, String value2) {
            addCriterion("director not between", value1, value2, "director");
            return (Criteria) this;
        }

        public Criteria andProducersIsNull() {
            addCriterion("producers is null");
            return (Criteria) this;
        }

        public Criteria andProducersIsNotNull() {
            addCriterion("producers is not null");
            return (Criteria) this;
        }

        public Criteria andProducersEqualTo(String value) {
            addCriterion("producers =", value, "producers");
            return (Criteria) this;
        }

        public Criteria andProducersNotEqualTo(String value) {
            addCriterion("producers <>", value, "producers");
            return (Criteria) this;
        }

        public Criteria andProducersGreaterThan(String value) {
            addCriterion("producers >", value, "producers");
            return (Criteria) this;
        }

        public Criteria andProducersGreaterThanOrEqualTo(String value) {
            addCriterion("producers >=", value, "producers");
            return (Criteria) this;
        }

        public Criteria andProducersLessThan(String value) {
            addCriterion("producers <", value, "producers");
            return (Criteria) this;
        }

        public Criteria andProducersLessThanOrEqualTo(String value) {
            addCriterion("producers <=", value, "producers");
            return (Criteria) this;
        }

        public Criteria andProducersLike(String value) {
            addCriterion("producers like", value, "producers");
            return (Criteria) this;
        }

        public Criteria andProducersNotLike(String value) {
            addCriterion("producers not like", value, "producers");
            return (Criteria) this;
        }

        public Criteria andProducersIn(List<String> values) {
            addCriterion("producers in", values, "producers");
            return (Criteria) this;
        }

        public Criteria andProducersNotIn(List<String> values) {
            addCriterion("producers not in", values, "producers");
            return (Criteria) this;
        }

        public Criteria andProducersBetween(String value1, String value2) {
            addCriterion("producers between", value1, value2, "producers");
            return (Criteria) this;
        }

        public Criteria andProducersNotBetween(String value1, String value2) {
            addCriterion("producers not between", value1, value2, "producers");
            return (Criteria) this;
        }

        public Criteria andActorIsNull() {
            addCriterion("actor is null");
            return (Criteria) this;
        }

        public Criteria andActorIsNotNull() {
            addCriterion("actor is not null");
            return (Criteria) this;
        }

        public Criteria andActorEqualTo(String value) {
            addCriterion("actor =", value, "actor");
            return (Criteria) this;
        }

        public Criteria andActorNotEqualTo(String value) {
            addCriterion("actor <>", value, "actor");
            return (Criteria) this;
        }

        public Criteria andActorGreaterThan(String value) {
            addCriterion("actor >", value, "actor");
            return (Criteria) this;
        }

        public Criteria andActorGreaterThanOrEqualTo(String value) {
            addCriterion("actor >=", value, "actor");
            return (Criteria) this;
        }

        public Criteria andActorLessThan(String value) {
            addCriterion("actor <", value, "actor");
            return (Criteria) this;
        }

        public Criteria andActorLessThanOrEqualTo(String value) {
            addCriterion("actor <=", value, "actor");
            return (Criteria) this;
        }

        public Criteria andActorLike(String value) {
            addCriterion("actor like", value, "actor");
            return (Criteria) this;
        }

        public Criteria andActorNotLike(String value) {
            addCriterion("actor not like", value, "actor");
            return (Criteria) this;
        }

        public Criteria andActorIn(List<String> values) {
            addCriterion("actor in", values, "actor");
            return (Criteria) this;
        }

        public Criteria andActorNotIn(List<String> values) {
            addCriterion("actor not in", values, "actor");
            return (Criteria) this;
        }

        public Criteria andActorBetween(String value1, String value2) {
            addCriterion("actor between", value1, value2, "actor");
            return (Criteria) this;
        }

        public Criteria andActorNotBetween(String value1, String value2) {
            addCriterion("actor not between", value1, value2, "actor");
            return (Criteria) this;
        }

        public Criteria andSupportingActorsIsNull() {
            addCriterion("`supporting actors` is null");
            return (Criteria) this;
        }

        public Criteria andSupportingActorsIsNotNull() {
            addCriterion("`supporting actors` is not null");
            return (Criteria) this;
        }

        public Criteria andSupportingActorsEqualTo(String value) {
            addCriterion("`supporting actors` =", value, "supportingActors");
            return (Criteria) this;
        }

        public Criteria andSupportingActorsNotEqualTo(String value) {
            addCriterion("`supporting actors` <>", value, "supportingActors");
            return (Criteria) this;
        }

        public Criteria andSupportingActorsGreaterThan(String value) {
            addCriterion("`supporting actors` >", value, "supportingActors");
            return (Criteria) this;
        }

        public Criteria andSupportingActorsGreaterThanOrEqualTo(String value) {
            addCriterion("`supporting actors` >=", value, "supportingActors");
            return (Criteria) this;
        }

        public Criteria andSupportingActorsLessThan(String value) {
            addCriterion("`supporting actors` <", value, "supportingActors");
            return (Criteria) this;
        }

        public Criteria andSupportingActorsLessThanOrEqualTo(String value) {
            addCriterion("`supporting actors` <=", value, "supportingActors");
            return (Criteria) this;
        }

        public Criteria andSupportingActorsLike(String value) {
            addCriterion("`supporting actors` like", value, "supportingActors");
            return (Criteria) this;
        }

        public Criteria andSupportingActorsNotLike(String value) {
            addCriterion("`supporting actors` not like", value, "supportingActors");
            return (Criteria) this;
        }

        public Criteria andSupportingActorsIn(List<String> values) {
            addCriterion("`supporting actors` in", values, "supportingActors");
            return (Criteria) this;
        }

        public Criteria andSupportingActorsNotIn(List<String> values) {
            addCriterion("`supporting actors` not in", values, "supportingActors");
            return (Criteria) this;
        }

        public Criteria andSupportingActorsBetween(String value1, String value2) {
            addCriterion("`supporting actors` between", value1, value2, "supportingActors");
            return (Criteria) this;
        }

        public Criteria andSupportingActorsNotBetween(String value1, String value2) {
            addCriterion("`supporting actors` not between", value1, value2, "supportingActors");
            return (Criteria) this;
        }

        public Criteria andMediaFormatIsNull() {
            addCriterion("`media format` is null");
            return (Criteria) this;
        }

        public Criteria andMediaFormatIsNotNull() {
            addCriterion("`media format` is not null");
            return (Criteria) this;
        }

        public Criteria andMediaFormatEqualTo(String value) {
            addCriterion("`media format` =", value, "mediaFormat");
            return (Criteria) this;
        }

        public Criteria andMediaFormatNotEqualTo(String value) {
            addCriterion("`media format` <>", value, "mediaFormat");
            return (Criteria) this;
        }

        public Criteria andMediaFormatGreaterThan(String value) {
            addCriterion("`media format` >", value, "mediaFormat");
            return (Criteria) this;
        }

        public Criteria andMediaFormatGreaterThanOrEqualTo(String value) {
            addCriterion("`media format` >=", value, "mediaFormat");
            return (Criteria) this;
        }

        public Criteria andMediaFormatLessThan(String value) {
            addCriterion("`media format` <", value, "mediaFormat");
            return (Criteria) this;
        }

        public Criteria andMediaFormatLessThanOrEqualTo(String value) {
            addCriterion("`media format` <=", value, "mediaFormat");
            return (Criteria) this;
        }

        public Criteria andMediaFormatLike(String value) {
            addCriterion("`media format` like", value, "mediaFormat");
            return (Criteria) this;
        }

        public Criteria andMediaFormatNotLike(String value) {
            addCriterion("`media format` not like", value, "mediaFormat");
            return (Criteria) this;
        }

        public Criteria andMediaFormatIn(List<String> values) {
            addCriterion("`media format` in", values, "mediaFormat");
            return (Criteria) this;
        }

        public Criteria andMediaFormatNotIn(List<String> values) {
            addCriterion("`media format` not in", values, "mediaFormat");
            return (Criteria) this;
        }

        public Criteria andMediaFormatBetween(String value1, String value2) {
            addCriterion("`media format` between", value1, value2, "mediaFormat");
            return (Criteria) this;
        }

        public Criteria andMediaFormatNotBetween(String value1, String value2) {
            addCriterion("`media format` not between", value1, value2, "mediaFormat");
            return (Criteria) this;
        }

        public Criteria andRunTimeIsNull() {
            addCriterion("`run time` is null");
            return (Criteria) this;
        }

        public Criteria andRunTimeIsNotNull() {
            addCriterion("`run time` is not null");
            return (Criteria) this;
        }

        public Criteria andRunTimeEqualTo(String value) {
            addCriterion("`run time` =", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeNotEqualTo(String value) {
            addCriterion("`run time` <>", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeGreaterThan(String value) {
            addCriterion("`run time` >", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeGreaterThanOrEqualTo(String value) {
            addCriterion("`run time` >=", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeLessThan(String value) {
            addCriterion("`run time` <", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeLessThanOrEqualTo(String value) {
            addCriterion("`run time` <=", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeLike(String value) {
            addCriterion("`run time` like", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeNotLike(String value) {
            addCriterion("`run time` not like", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeIn(List<String> values) {
            addCriterion("`run time` in", values, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeNotIn(List<String> values) {
            addCriterion("`run time` not in", values, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeBetween(String value1, String value2) {
            addCriterion("`run time` between", value1, value2, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeNotBetween(String value1, String value2) {
            addCriterion("`run time` not between", value1, value2, "runTime");
            return (Criteria) this;
        }

        public Criteria andMpaaRatingIsNull() {
            addCriterion("`MPAA rating` is null");
            return (Criteria) this;
        }

        public Criteria andMpaaRatingIsNotNull() {
            addCriterion("`MPAA rating` is not null");
            return (Criteria) this;
        }

        public Criteria andMpaaRatingEqualTo(String value) {
            addCriterion("`MPAA rating` =", value, "mpaaRating");
            return (Criteria) this;
        }

        public Criteria andMpaaRatingNotEqualTo(String value) {
            addCriterion("`MPAA rating` <>", value, "mpaaRating");
            return (Criteria) this;
        }

        public Criteria andMpaaRatingGreaterThan(String value) {
            addCriterion("`MPAA rating` >", value, "mpaaRating");
            return (Criteria) this;
        }

        public Criteria andMpaaRatingGreaterThanOrEqualTo(String value) {
            addCriterion("`MPAA rating` >=", value, "mpaaRating");
            return (Criteria) this;
        }

        public Criteria andMpaaRatingLessThan(String value) {
            addCriterion("`MPAA rating` <", value, "mpaaRating");
            return (Criteria) this;
        }

        public Criteria andMpaaRatingLessThanOrEqualTo(String value) {
            addCriterion("`MPAA rating` <=", value, "mpaaRating");
            return (Criteria) this;
        }

        public Criteria andMpaaRatingLike(String value) {
            addCriterion("`MPAA rating` like", value, "mpaaRating");
            return (Criteria) this;
        }

        public Criteria andMpaaRatingNotLike(String value) {
            addCriterion("`MPAA rating` not like", value, "mpaaRating");
            return (Criteria) this;
        }

        public Criteria andMpaaRatingIn(List<String> values) {
            addCriterion("`MPAA rating` in", values, "mpaaRating");
            return (Criteria) this;
        }

        public Criteria andMpaaRatingNotIn(List<String> values) {
            addCriterion("`MPAA rating` not in", values, "mpaaRating");
            return (Criteria) this;
        }

        public Criteria andMpaaRatingBetween(String value1, String value2) {
            addCriterion("`MPAA rating` between", value1, value2, "mpaaRating");
            return (Criteria) this;
        }

        public Criteria andMpaaRatingNotBetween(String value1, String value2) {
            addCriterion("`MPAA rating` not between", value1, value2, "mpaaRating");
            return (Criteria) this;
        }

        public Criteria andSubtitlesIsNull() {
            addCriterion("subtitles is null");
            return (Criteria) this;
        }

        public Criteria andSubtitlesIsNotNull() {
            addCriterion("subtitles is not null");
            return (Criteria) this;
        }

        public Criteria andSubtitlesEqualTo(String value) {
            addCriterion("subtitles =", value, "subtitles");
            return (Criteria) this;
        }

        public Criteria andSubtitlesNotEqualTo(String value) {
            addCriterion("subtitles <>", value, "subtitles");
            return (Criteria) this;
        }

        public Criteria andSubtitlesGreaterThan(String value) {
            addCriterion("subtitles >", value, "subtitles");
            return (Criteria) this;
        }

        public Criteria andSubtitlesGreaterThanOrEqualTo(String value) {
            addCriterion("subtitles >=", value, "subtitles");
            return (Criteria) this;
        }

        public Criteria andSubtitlesLessThan(String value) {
            addCriterion("subtitles <", value, "subtitles");
            return (Criteria) this;
        }

        public Criteria andSubtitlesLessThanOrEqualTo(String value) {
            addCriterion("subtitles <=", value, "subtitles");
            return (Criteria) this;
        }

        public Criteria andSubtitlesLike(String value) {
            addCriterion("subtitles like", value, "subtitles");
            return (Criteria) this;
        }

        public Criteria andSubtitlesNotLike(String value) {
            addCriterion("subtitles not like", value, "subtitles");
            return (Criteria) this;
        }

        public Criteria andSubtitlesIn(List<String> values) {
            addCriterion("subtitles in", values, "subtitles");
            return (Criteria) this;
        }

        public Criteria andSubtitlesNotIn(List<String> values) {
            addCriterion("subtitles not in", values, "subtitles");
            return (Criteria) this;
        }

        public Criteria andSubtitlesBetween(String value1, String value2) {
            addCriterion("subtitles between", value1, value2, "subtitles");
            return (Criteria) this;
        }

        public Criteria andSubtitlesNotBetween(String value1, String value2) {
            addCriterion("subtitles not between", value1, value2, "subtitles");
            return (Criteria) this;
        }

        public Criteria andStudioIsNull() {
            addCriterion("studio is null");
            return (Criteria) this;
        }

        public Criteria andStudioIsNotNull() {
            addCriterion("studio is not null");
            return (Criteria) this;
        }

        public Criteria andStudioEqualTo(String value) {
            addCriterion("studio =", value, "studio");
            return (Criteria) this;
        }

        public Criteria andStudioNotEqualTo(String value) {
            addCriterion("studio <>", value, "studio");
            return (Criteria) this;
        }

        public Criteria andStudioGreaterThan(String value) {
            addCriterion("studio >", value, "studio");
            return (Criteria) this;
        }

        public Criteria andStudioGreaterThanOrEqualTo(String value) {
            addCriterion("studio >=", value, "studio");
            return (Criteria) this;
        }

        public Criteria andStudioLessThan(String value) {
            addCriterion("studio <", value, "studio");
            return (Criteria) this;
        }

        public Criteria andStudioLessThanOrEqualTo(String value) {
            addCriterion("studio <=", value, "studio");
            return (Criteria) this;
        }

        public Criteria andStudioLike(String value) {
            addCriterion("studio like", value, "studio");
            return (Criteria) this;
        }

        public Criteria andStudioNotLike(String value) {
            addCriterion("studio not like", value, "studio");
            return (Criteria) this;
        }

        public Criteria andStudioIn(List<String> values) {
            addCriterion("studio in", values, "studio");
            return (Criteria) this;
        }

        public Criteria andStudioNotIn(List<String> values) {
            addCriterion("studio not in", values, "studio");
            return (Criteria) this;
        }

        public Criteria andStudioBetween(String value1, String value2) {
            addCriterion("studio between", value1, value2, "studio");
            return (Criteria) this;
        }

        public Criteria andStudioNotBetween(String value1, String value2) {
            addCriterion("studio not between", value1, value2, "studio");
            return (Criteria) this;
        }

        public Criteria andItemModelNumberIsNull() {
            addCriterion("`Item model number` is null");
            return (Criteria) this;
        }

        public Criteria andItemModelNumberIsNotNull() {
            addCriterion("`Item model number` is not null");
            return (Criteria) this;
        }

        public Criteria andItemModelNumberEqualTo(String value) {
            addCriterion("`Item model number` =", value, "itemModelNumber");
            return (Criteria) this;
        }

        public Criteria andItemModelNumberNotEqualTo(String value) {
            addCriterion("`Item model number` <>", value, "itemModelNumber");
            return (Criteria) this;
        }

        public Criteria andItemModelNumberGreaterThan(String value) {
            addCriterion("`Item model number` >", value, "itemModelNumber");
            return (Criteria) this;
        }

        public Criteria andItemModelNumberGreaterThanOrEqualTo(String value) {
            addCriterion("`Item model number` >=", value, "itemModelNumber");
            return (Criteria) this;
        }

        public Criteria andItemModelNumberLessThan(String value) {
            addCriterion("`Item model number` <", value, "itemModelNumber");
            return (Criteria) this;
        }

        public Criteria andItemModelNumberLessThanOrEqualTo(String value) {
            addCriterion("`Item model number` <=", value, "itemModelNumber");
            return (Criteria) this;
        }

        public Criteria andItemModelNumberLike(String value) {
            addCriterion("`Item model number` like", value, "itemModelNumber");
            return (Criteria) this;
        }

        public Criteria andItemModelNumberNotLike(String value) {
            addCriterion("`Item model number` not like", value, "itemModelNumber");
            return (Criteria) this;
        }

        public Criteria andItemModelNumberIn(List<String> values) {
            addCriterion("`Item model number` in", values, "itemModelNumber");
            return (Criteria) this;
        }

        public Criteria andItemModelNumberNotIn(List<String> values) {
            addCriterion("`Item model number` not in", values, "itemModelNumber");
            return (Criteria) this;
        }

        public Criteria andItemModelNumberBetween(String value1, String value2) {
            addCriterion("`Item model number` between", value1, value2, "itemModelNumber");
            return (Criteria) this;
        }

        public Criteria andItemModelNumberNotBetween(String value1, String value2) {
            addCriterion("`Item model number` not between", value1, value2, "itemModelNumber");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableIsNull() {
            addCriterion("`Date First Available` is null");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableIsNotNull() {
            addCriterion("`Date First Available` is not null");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableEqualTo(String value) {
            addCriterion("`Date First Available` =", value, "dateFirstAvailable");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableNotEqualTo(String value) {
            addCriterion("`Date First Available` <>", value, "dateFirstAvailable");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableGreaterThan(String value) {
            addCriterion("`Date First Available` >", value, "dateFirstAvailable");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableGreaterThanOrEqualTo(String value) {
            addCriterion("`Date First Available` >=", value, "dateFirstAvailable");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableLessThan(String value) {
            addCriterion("`Date First Available` <", value, "dateFirstAvailable");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableLessThanOrEqualTo(String value) {
            addCriterion("`Date First Available` <=", value, "dateFirstAvailable");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableLike(String value) {
            addCriterion("`Date First Available` like", value, "dateFirstAvailable");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableNotLike(String value) {
            addCriterion("`Date First Available` not like", value, "dateFirstAvailable");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableIn(List<String> values) {
            addCriterion("`Date First Available` in", values, "dateFirstAvailable");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableNotIn(List<String> values) {
            addCriterion("`Date First Available` not in", values, "dateFirstAvailable");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableBetween(String value1, String value2) {
            addCriterion("`Date First Available` between", value1, value2, "dateFirstAvailable");
            return (Criteria) this;
        }

        public Criteria andDateFirstAvailableNotBetween(String value1, String value2) {
            addCriterion("`Date First Available` not between", value1, value2, "dateFirstAvailable");
            return (Criteria) this;
        }

        public Criteria andImdbIsNull() {
            addCriterion("IMDb is null");
            return (Criteria) this;
        }

        public Criteria andImdbIsNotNull() {
            addCriterion("IMDb is not null");
            return (Criteria) this;
        }

        public Criteria andImdbEqualTo(String value) {
            addCriterion("IMDb =", value, "imdb");
            return (Criteria) this;
        }

        public Criteria andImdbNotEqualTo(String value) {
            addCriterion("IMDb <>", value, "imdb");
            return (Criteria) this;
        }

        public Criteria andImdbGreaterThan(String value) {
            addCriterion("IMDb >", value, "imdb");
            return (Criteria) this;
        }

        public Criteria andImdbGreaterThanOrEqualTo(String value) {
            addCriterion("IMDb >=", value, "imdb");
            return (Criteria) this;
        }

        public Criteria andImdbLessThan(String value) {
            addCriterion("IMDb <", value, "imdb");
            return (Criteria) this;
        }

        public Criteria andImdbLessThanOrEqualTo(String value) {
            addCriterion("IMDb <=", value, "imdb");
            return (Criteria) this;
        }

        public Criteria andImdbLike(String value) {
            addCriterion("IMDb like", value, "imdb");
            return (Criteria) this;
        }

        public Criteria andImdbNotLike(String value) {
            addCriterion("IMDb not like", value, "imdb");
            return (Criteria) this;
        }

        public Criteria andImdbIn(List<String> values) {
            addCriterion("IMDb in", values, "imdb");
            return (Criteria) this;
        }

        public Criteria andImdbNotIn(List<String> values) {
            addCriterion("IMDb not in", values, "imdb");
            return (Criteria) this;
        }

        public Criteria andImdbBetween(String value1, String value2) {
            addCriterion("IMDb between", value1, value2, "imdb");
            return (Criteria) this;
        }

        public Criteria andImdbNotBetween(String value1, String value2) {
            addCriterion("IMDb not between", value1, value2, "imdb");
            return (Criteria) this;
        }

        public Criteria andAudioLanguagesIsNull() {
            addCriterion("`audio languages` is null");
            return (Criteria) this;
        }

        public Criteria andAudioLanguagesIsNotNull() {
            addCriterion("`audio languages` is not null");
            return (Criteria) this;
        }

        public Criteria andAudioLanguagesEqualTo(String value) {
            addCriterion("`audio languages` =", value, "audioLanguages");
            return (Criteria) this;
        }

        public Criteria andAudioLanguagesNotEqualTo(String value) {
            addCriterion("`audio languages` <>", value, "audioLanguages");
            return (Criteria) this;
        }

        public Criteria andAudioLanguagesGreaterThan(String value) {
            addCriterion("`audio languages` >", value, "audioLanguages");
            return (Criteria) this;
        }

        public Criteria andAudioLanguagesGreaterThanOrEqualTo(String value) {
            addCriterion("`audio languages` >=", value, "audioLanguages");
            return (Criteria) this;
        }

        public Criteria andAudioLanguagesLessThan(String value) {
            addCriterion("`audio languages` <", value, "audioLanguages");
            return (Criteria) this;
        }

        public Criteria andAudioLanguagesLessThanOrEqualTo(String value) {
            addCriterion("`audio languages` <=", value, "audioLanguages");
            return (Criteria) this;
        }

        public Criteria andAudioLanguagesLike(String value) {
            addCriterion("`audio languages` like", value, "audioLanguages");
            return (Criteria) this;
        }

        public Criteria andAudioLanguagesNotLike(String value) {
            addCriterion("`audio languages` not like", value, "audioLanguages");
            return (Criteria) this;
        }

        public Criteria andAudioLanguagesIn(List<String> values) {
            addCriterion("`audio languages` in", values, "audioLanguages");
            return (Criteria) this;
        }

        public Criteria andAudioLanguagesNotIn(List<String> values) {
            addCriterion("`audio languages` not in", values, "audioLanguages");
            return (Criteria) this;
        }

        public Criteria andAudioLanguagesBetween(String value1, String value2) {
            addCriterion("`audio languages` between", value1, value2, "audioLanguages");
            return (Criteria) this;
        }

        public Criteria andAudioLanguagesNotBetween(String value1, String value2) {
            addCriterion("`audio languages` not between", value1, value2, "audioLanguages");
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