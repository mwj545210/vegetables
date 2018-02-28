package com.mwj.logistics.dao;

import com.mwj.logistics.entry.Logistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by MWJ on 2017/12/25.
 */
public interface LogisticDao extends JpaRepository<Logistic, Integer>, JpaSpecificationExecutor<Logistic> {

}
