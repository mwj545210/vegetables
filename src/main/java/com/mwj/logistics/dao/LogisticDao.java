package com.mwj.logistics.dao;

import com.mwj.logistics.entry.Logistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by MWJ on 2017/12/25.
 */
public interface LogisticDao extends JpaRepository<Logistic, Integer>, JpaSpecificationExecutor<Logistic> {

    List<Logistic> findByLogisticCodeContaining(String code);

}
