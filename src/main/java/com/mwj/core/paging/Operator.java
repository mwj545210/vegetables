package com.mwj.core.paging;

public enum Operator {

    /**
     * 等于
     */
    eq,

    /**
     * 日期等于
     */
    dateEq,
    /**
     * 不等于
     */
    ne,

    /**
     * 大于
     */
    gt,

    /**
     * 小于
     */
    lt,

    /**
     * 大于等于
     */
    ge,

    /**
     * 小于等于
     */
    le,
    /**
     * 日期小于等于
     */
    dateLe,
    startLike,
    endLike,
    /**
     * 类似
     */
    like,

    /**
     * 包含
     */
    in,

    /**
     * 为Null
     */
    isNull,

    /**
     * 不为Null
     */
    isNotNull;
}
