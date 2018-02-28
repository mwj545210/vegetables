package com.mwj.logistics.service;

import com.mwj.logistics.entry.Logistic;

import java.util.List;

/**
 * Created by MWJ on 2017/12/25.
 */
public interface ILogisticService {

    Logistic addLogistics(Logistic logistic);

    List<Logistic> findAllLogistics();

    void delLogisticsByIds(List<Logistic> logistics);

    Logistic findLogisticById(int id);

    List<Logistic> findAllByCode(String code);
}
