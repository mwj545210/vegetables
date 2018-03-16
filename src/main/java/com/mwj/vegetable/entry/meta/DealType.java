package com.mwj.vegetable.entry.meta;

/**
 * Created by MWJ on 2018/3/16.
 */
public enum DealType {

    EXPORT("出口"),//出口
    IMPORTATION("进口");//进口

    private String name;

    DealType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
