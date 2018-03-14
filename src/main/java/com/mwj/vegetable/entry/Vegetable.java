package com.mwj.vegetable.entry;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by MWJ on 2017/12/25.
 */
@Table
@Entity
public class Vegetable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "VEGETABLE_ID", unique = true, nullable = false, insertable = true, updatable = true)
    private int vegetableId;

    @Column(name = "VEGETABLE_NAME", unique = false, nullable = false, insertable = true, updatable = true)
    private String vegetableName;

    @Column(name = "VEGETABLE_CODE", unique = false, nullable = false, insertable = true, updatable = true)
    private String vegetableCode;

    @Column(name = "COMPANY_NAME", unique = false, nullable = false, insertable = true, updatable = true)
    private String companyName;

    @Column(name = "COUNTRY", unique = false, nullable = false, insertable = true, updatable = true)
    private String country;

    @Column(name = "PRICE", unique = false, nullable = false, insertable = true, updatable = true)
    private String price;

    @Column(name = "DEAL_TYPE", unique = false, nullable = false, insertable = true, updatable = true)
    private String dealType;

    public int getVegetableId() {
        return vegetableId;
    }

    public void setVegetableId(int vegetableId) {
        this.vegetableId = vegetableId;
    }

    public String getVegetableName() {
        return vegetableName;
    }

    public void setVegetableName(String vegetableName) {
        this.vegetableName = vegetableName;
    }

    public String getVegetableCode() {
        return vegetableCode;
    }

    public void setVegetableCode(String vegetableCode) {
        this.vegetableCode = vegetableCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }
}

