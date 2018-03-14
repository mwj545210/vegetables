package com.mwj.vegetable.service;

import com.mwj.vegetable.entry.Vegetable;

import java.util.List;

/**
 * Created by MWJ on 2017/12/25.
 */
public interface IVegetableService {

    Vegetable addVegetables(Vegetable vegetable);

    List<Vegetable> findAllVegetables();

    void delVegetablesByIds(List<Vegetable> vegetable);

    Vegetable findVegetableById(int id);

//    List<Vegetable> findAllByCode(String code);
}
