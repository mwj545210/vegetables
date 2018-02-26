package com.mwj.core.paging;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public interface FilterDescriptor<T> {

    Predicate build(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder);

}
