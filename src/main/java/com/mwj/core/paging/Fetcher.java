package com.mwj.core.paging;

import javax.persistence.criteria.Root;


public interface Fetcher<T> {

    void apply(Root<T> root);
}
