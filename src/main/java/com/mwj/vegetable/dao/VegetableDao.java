package com.mwj.vegetable.dao;


import com.mwj.vegetable.entry.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by MWJ on 2017/12/25.
 */
public interface VegetableDao extends JpaRepository<Vegetable, Integer>, JpaSpecificationExecutor<Vegetable> {

}
