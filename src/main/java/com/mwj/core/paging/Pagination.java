package com.mwj.core.paging;

import com.mwj.core.utils.CalendarUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.SingularAttribute;
import java.util.*;

public class Pagination<T> implements Specification<T> {

    /**
     * 属性分隔符
     */
    private static final String PROPERTY_SEPARATOR = ".";


    /**
     * 偏移量，即从哪一条开始查询
     */
    private int offset;

    /**
     * 每页查询数量
     */
    private int limit = 20;


    /**
     * 排序字段
     */
    private String sort;

    /**
     * 排序类型 asc desc
     */
    private String order;


    private HashMap<String, Object> data;

    /**
     * 与条件信息
     */
    private List<Filter> filters;

    /**
     * 或条件信息
     */
    private List<Filter> orFilters;

    /**
     * 自定义的与条件
     */
    private List<FilterDescriptor> conditions;

    /**
     * 自定义的或条件
     */
    private List<FilterDescriptor> orConditions;

    /**
     * 自定义抓取
     */
    private List<Fetcher<T>> fetchs;

    /**
     * 排序字段
     */
    private List<SortOrder> sortOrders;

    /**
     * 最终生成的条件断定
     */
    private Predicate finalPredicate;

    public Pagination() {
    }

    public Pagination(int offset) {
        this.offset = offset;
    }

    public Pagination(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (finalPredicate == null) {
            Predicate restrictions = builder.and(toAndPredicate(root, query, builder));
            //如果没有或条件，不进去条件匹配
            if (CollectionUtils.isNotEmpty(orFilters) || CollectionUtils.isNotEmpty(orConditions)) {
                restrictions = builder.and(restrictions, toOrPredicate(root, query, builder));
            }
            finalPredicate = restrictions;
        }
        //根据返回类型判断是否为查询条数
        if (query.getResultType() != Long.class) {
            List<Order> orders = toOrders(root, builder);
            if (orders.size() > 0) {
                query.orderBy(orders);
            }
            if (this.fetchs != null) {
                for (Fetcher<T> fetch : fetchs) {
                    fetch.apply(root);
                }
            }
        }
        return finalPredicate;
    }


    public void addFilterOfAnd(Filter filter) {
        if (filters == null) {
            filters = new ArrayList<>();
        }
        filters.add(filter);
    }

    public void addFilterOfAnd(String field, Object value) {
        Filter filter = new Filter(field, value);
        addFilterOfAnd(filter);
    }

    public void addFilterOfAnd(String field, Object value, Operator operator) {
        Filter filter = new Filter(field, value, operator);
        addFilterOfAnd(filter);
    }

    public void addFilterOfAnd(String field, Object value, Operator operator, boolean ignoreCase) {
        Filter filter = new Filter(field, value, operator, ignoreCase);
        addFilterOfAnd(filter);
    }

    public void addFilterOfAnd(Object value, SingularAttribute... attributes) {
        Filter filter = new Filter(value, attributes);
        addFilterOfAnd(filter);
    }

    public void addFilterOfAnd(Object value, Operator operator, SingularAttribute... attributes) {
        Filter filter = new Filter(value, operator, attributes);
        addFilterOfAnd(filter);
    }

    public void addFilterOfAnd(Object value, Operator operator, boolean ignoreCase, SingularAttribute... attributes) {
        Filter filter = new Filter(value, operator, ignoreCase, attributes);
        addFilterOfAnd(filter);
    }

    /**
     * 添加与条件
     *
     * @param condition
     */
    public void addConditionOfAnd(FilterDescriptor condition) {
        if (conditions == null) {
            conditions = new ArrayList<>();
        }
        conditions.add(condition);
    }


    public void addFilterOfOr(Filter filter) {
        if (orFilters == null) {
            orFilters = new ArrayList<>();
        }
        orFilters.add(filter);
    }

    public void addFilterOfOr(String field, Object value) {
        Filter filter = new Filter(field, value);
        addFilterOfOr(filter);
    }

    public void addFilterOfOr(String field, Object value, Operator operator) {
        Filter filter = new Filter(field, value, operator);
        addFilterOfOr(filter);
    }

    public void addFilterOfOr(String field, Object value, Operator operator, boolean ignoreCase) {
        Filter filter = new Filter(field, value, operator, ignoreCase);
        addFilterOfOr(filter);
    }

    public void addFilterOfOr(Object value, SingularAttribute... attributes) {
        Filter filter = new Filter(value, attributes);
        addFilterOfOr(filter);
    }

    public void addFilterOfOr(Object value, Operator operator, SingularAttribute... attributes) {
        Filter filter = new Filter(value, operator, attributes);
        addFilterOfOr(filter);
    }

    public void addFilterOfOr(Object value, Operator operator, boolean ignoreCase, SingularAttribute... attributes) {
        Filter filter = new Filter(value, operator, ignoreCase, attributes);
        addFilterOfOr(filter);
    }

    /**
     * 添加或条件
     *
     * @param condition
     */
    public void addConditionOfOr(FilterDescriptor condition) {
        if (orConditions == null) {
            orConditions = new ArrayList<>();
        }
        orConditions.add(condition);
    }

    /**
     * 装配与条件
     *
     * @param root
     * @param builder
     * @return
     */
    private Predicate toAndPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Predicate restrictions = builder.conjunction();
        //加入自定义条件
        if (conditions != null) {
            for (FilterDescriptor andCriteria : conditions) {
                Predicate customPredicate = andCriteria.build(root, query, builder);
                if (customPredicate != null) {
                    restrictions = builder.and(restrictions, customPredicate);
                }
            }
        }
        if (root == null || CollectionUtils.isEmpty(filters)) {
            return restrictions;
        }
        for (Filter filter : filters) {
            Predicate predicate = restrictByFilter(root, builder, filter);
            if (predicate != null) {
                restrictions = builder.and(restrictions, predicate);
            }
        }
        return restrictions;
    }

    /**
     * 根据filter装配基本条件
     *
     * @param root
     * @param builder
     * @param filter
     * @return
     */
    private Predicate restrictByFilter(Root<T> root, CriteriaBuilder builder, Filter filter) {
        Predicate predicate = null;
        String property = filter.getField();
        Operator operator = filter.getOperator();
        Object value = filter.getValue();
        boolean ignoreCase = filter.isIgnoreCase();
        Path<?> path = getPath(root, filter.getAttributes());
        if (path == null) {
            path = getPath(root, property);
        }
        if (path == null) {
            return predicate;
        }
        //类型转换
        if (value instanceof String) {
            String s = (String) value;
            value = convertStringType(path.getJavaType(), s);
        }
        //根据运算符生成相应条件
        switch (operator) {
            case eq:
                if (value != null) {
                    if (ignoreCase && value instanceof String) {
                        predicate = builder.equal(builder.lower((Path<String>) path), ((String) value).toLowerCase());
                    } else {
                        predicate = builder.equal(path, value);
                    }
                } else {
                    predicate = path.isNull();
                }
                break;
            case dateEq:
                Calendar calendar = Calendar.getInstance();
                calendar.setTime((Date) value);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                Date startTime = calendar.getTime();
                calendar.add(Calendar.DATE, 1);
                Date endTime = calendar.getTime();
                predicate = builder.between((Path<Date>) path, startTime, endTime);
                break;
            case ne:
                if (value != null) {
                    if (ignoreCase && value instanceof String) {
                        predicate = builder.notEqual(builder.lower((Path<String>) path), ((String) value).toLowerCase());
                    } else {
                        predicate = builder.notEqual(path, value);
                    }
                } else {
                    predicate = path.isNotNull();
                }
                break;
            case gt:
                if (value instanceof Number) {
                    predicate = builder.gt((Path<Number>) path, (Number) value);
                } else {
                    predicate = builder.greaterThan((Path) path, (Comparable) value);
                }
                break;
            case lt:
                if (value instanceof Number) {
                    predicate = builder.lt((Path<Number>) path, (Number) value);
                } else {
                    predicate = builder.lessThan((Path) path, (Comparable) value);
                }
                break;
            case ge:
                if (value instanceof Number) {
                    predicate = builder.ge((Path<Number>) path, (Number) value);
                } else {
                    predicate = builder.greaterThanOrEqualTo((Path) path, (Comparable) value);
                }
                break;
            case le:
                if (value instanceof Number) {
                    predicate = builder.le((Path<Number>) path, (Number) value);
                } else {
                    predicate = builder.lessThanOrEqualTo((Path) path, (Comparable) value);
                }
                break;
            case dateLe:
                Calendar ca = Calendar.getInstance();
                ca.setTime((Date) value);
                ca.set(Calendar.HOUR_OF_DAY, 23);
                ca.set(Calendar.MINUTE, 59);
                ca.set(Calendar.SECOND, 59);
                predicate = builder.lessThanOrEqualTo((Path<Date>) path, ca.getTime());
                break;
            case startLike:
                if (value instanceof String) {
                    if (ignoreCase) {
                        predicate = builder.like(builder.lower((Path<String>) path), ((String) value).toLowerCase() + "%");
                    } else {
                        predicate = builder.like((Path<String>) path, (String) value);
                    }
                }
                break;
            case endLike:
                if (value instanceof String) {
                    if (ignoreCase) {
                        predicate = builder.like(builder.lower((Path<String>) path), "%" + ((String) value).toLowerCase());
                    } else {
                        predicate = builder.like((Path<String>) path, (String) value);
                    }
                }
                break;
            case like:
                if (value instanceof String) {
                    if (ignoreCase) {
                        predicate = builder.like(builder.lower((Path<String>) path), "%" + ((String) value).toLowerCase() + "%");
                    } else {
                        predicate = builder.like((Path<String>) path, (String) value);
                    }
                }
                break;
            case in:
                predicate = path.in(value);
                break;
            case isNull:
                predicate = path.isNull();
                break;
            case isNotNull:
                predicate = path.isNotNull();
                break;
        }
        return predicate;
    }

    /**
     * 装配或条件
     *
     * @param root
     * @param builder
     * @return
     */
    private Predicate toOrPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Predicate restrictions = builder.disjunction();
        //加入自定义条件
        if (orConditions != null) {
            for (FilterDescriptor orCriteria : orConditions) {
                Predicate customPredicate = orCriteria.build(root, query, builder);
                if (customPredicate != null) {
                    restrictions = builder.or(restrictions, customPredicate);
                }
            }
        }
        if (root == null || CollectionUtils.isEmpty(orFilters)) {
            return restrictions;
        }
        for (Filter filter : orFilters) {
            Predicate predicate = restrictByFilter(root, builder, filter);
            if (predicate != null) {
                restrictions = builder.or(restrictions, predicate);
            }
        }
        return restrictions;
    }

    private List<Order> toOrders(Root<T> root, CriteriaBuilder criteriaBuilder) {
        List<Order> orderList = new ArrayList<Order>();
        if (StringUtils.isNotBlank(sort) && ("asc".equals(order) || "desc".equals(order))) {
            Path<?> path = getPath(root, sort);
            if (path != null) {
                switch (order) {
                    case "asc":
                        orderList.add(criteriaBuilder.asc(path));
                        break;
                    case "desc":
                        orderList.add(criteriaBuilder.desc(path));
                        break;
                }
            }
        }
        if (root == null || CollectionUtils.isEmpty(sortOrders)) {
            return orderList;
        }
        for (SortOrder order : sortOrders) {
            String property = order.getProperty();
            Sort.Direction direction = order.getDirection();
            Path<?> path = getPath(root, order.getAttributes());
            if (path == null) {
                path = getPath(root, property);
            }
            if (path == null || direction == null) {
                continue;
            }
            switch (direction) {
                case ASC:
                    orderList.add(criteriaBuilder.asc(path));
                    break;
                case DESC:
                    orderList.add(criteriaBuilder.desc(path));
                    break;
            }
        }
        return orderList;
    }

    /**
     * 添加排序字段
     *
     * @param sortOrder
     */
    public void addSortOrder(SortOrder sortOrder) {
        if (sortOrders == null) {
            sortOrders = new ArrayList<>();
        }
        sortOrders.add(sortOrder);
    }

    /**
     * 添加抓取
     *
     * @param fetch
     */
    public void addFetch(Fetcher fetch) {
        if (fetchs == null) {
            fetchs = new ArrayList<>();
        }
        fetchs.add(fetch);
    }

    /**
     * 创建分页请求.
     */
    public PageRequest getPageRequest() {
        return new PageRequest(offset / limit, this.limit);
    }

    /**
     * 解析出字段的对象树类型
     *
     * @param path
     * @param propertyPath
     * @param <X>
     * @return
     */
    private <X> Path<X> getPath(Path<?> path, String propertyPath) {
        if (path == null || StringUtils.isEmpty(propertyPath)) {
            return (Path<X>) path;
        }
        String property = StringUtils.substringBefore(propertyPath, PROPERTY_SEPARATOR);
        return getPath(path.get(property), StringUtils.substringAfter(propertyPath, PROPERTY_SEPARATOR));
    }

    private <X> Path<?> getPath(Path<?> root, SingularAttribute[] attributes) {
        if (attributes == null) {
            return null;
        }
        Path<?> path = root;
        for (SingularAttribute attribute : attributes) {
            path = path.get(attribute);
        }
        return path;
    }

    /**
     * 将字符串类型进行转换
     *
     * @param type
     * @param s
     * @return
     */
    private Object convertStringType(Class type, String s) {
        Object value = s;
        try {
            if (type == double.class || type == Double.class) {
                value = Double.parseDouble(s);
            } else if (type == float.class || type == Float.class) {
                value = Float.parseFloat(s);
            } else if (type == long.class || type == Long.class) {
                value = Long.parseLong(s);
            } else if (type == int.class || type == Integer.class) {
                value = Integer.parseInt(s);
            } else if (type == short.class || type == Short.class) {
                value = Short.parseShort(s);
            } else if (type == boolean.class || type == Boolean.class) {
                value = Boolean.parseBoolean(s);
            } else if (type == Date.class) {
                value = CalendarUtils.attemptParseDate(s);
            } else if (type.isEnum()) {
                value = Enum.valueOf(type, s);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return value;
    }


    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public List<Filter> getOrFilters() {
        return orFilters;
    }

    public void setOrFilters(List<Filter> orFilters) {
        this.orFilters = orFilters;
    }
}
