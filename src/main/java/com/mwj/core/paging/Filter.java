package com.mwj.core.paging;

import javax.persistence.metamodel.SingularAttribute;

public class Filter {

    private String field;
    private Object value;
    private Operator operator;
    private boolean ignoreCase;
    private SingularAttribute[] attributes;

    public Filter() {
        ignoreCase = true;
    }

    public Filter(String field, Object value) {
        this(field, value, Operator.eq);
    }

    public Filter(String field, Object value, Operator operator) {
        this(field, value, operator, true);
    }

    public Filter(String field, Object value, Operator operator, boolean ignoreCase) {
        this.field = field;
        this.value = value;
        this.operator = operator;
        this.ignoreCase = ignoreCase;
    }

    public Filter(Object value, SingularAttribute[] attributes) {
        this(value, Operator.eq, attributes);
    }

    public Filter(Object value, Operator operator, SingularAttribute[] attributes) {
        this(value, operator, true, attributes);
    }

    public Filter(Object value, Operator operator, boolean ignoreCase, SingularAttribute[] attributes) {
        this.value = value;
        this.operator = operator;
        this.ignoreCase = ignoreCase;
        this.attributes = attributes;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    public SingularAttribute[] getAttributes() {
        return attributes;
    }



}
