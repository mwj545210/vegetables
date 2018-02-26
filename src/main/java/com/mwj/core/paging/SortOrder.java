package com.mwj.core.paging;

import org.springframework.data.domain.Sort;

import javax.persistence.metamodel.SingularAttribute;

public class SortOrder {


    private String property;

    private SingularAttribute[] attributes;

    private Sort.Direction direction;

    public SortOrder() {
    }

    public SortOrder(Sort.Direction direction, String property) {
        this.property = property;
        this.direction = direction;
    }

    public SortOrder(Sort.Direction direction, SingularAttribute... attributes) {
        this.direction = direction;
        this.attributes = attributes;
    }

    public Sort.Direction getDirection() {
        return direction;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public SingularAttribute[] getAttributes() {
        return attributes;
    }

    public void setAttributes(SingularAttribute[] attributes) {
        this.attributes = attributes;
    }
}
