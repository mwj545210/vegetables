package com.mwj.vegetable.dao;


import com.mwj.vegetable.entry.Vegetable;
import com.mwj.vegetable.entry.meta.DealType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by MWJ on 2017/12/25.
 */
public interface VegetableDao extends JpaRepository<Vegetable, Integer>, JpaSpecificationExecutor<Vegetable> {

    @Query("select vt from Vegetable vt where vt.vegetableName like  ?1 and vt.vegetableCode like  ?2")
    List<Vegetable> findAllByNameAndCode(String vegetableName, String vegetableCode);

    @Query("select vt from Vegetable vt where vt.vegetableName like  ?1 and vt.vegetableCode like  ?2 and vt.dealType = ?3")
    List<Vegetable> findAllByNameAndCodeAndType(String vegetableName, String vegetableCode, DealType dealType);
}
