package com.mwj.logistics.entry;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by MWJ on 2017/12/25.
 */
@Table
@Entity
public class Logistic {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "LOGISTIC_ID", unique = true, nullable = false, insertable = true, updatable = true)
    private int logisticId;

    @Column(name = "LOGISTIC_NAME", unique = false, nullable = false, insertable = true, updatable = true)
    private String logisticName;

    @Column(name = "LOGISTIC_CODE", unique = false, nullable = false, insertable = true, updatable = true)
    private String logisticCode;

    @Column(name = "LOGISTIC_COMPANY", unique = false, nullable = false, insertable = true, updatable = true)
    private String logisticCompany;

    @Column(name = "LOGISTIC_CONTENT", unique = false, nullable = false, insertable = true, updatable = true)
    private String logisticContent;

    public int getLogisticId() {
        return logisticId;
    }

    public void setLogisticId(int logisticId) {
        this.logisticId = logisticId;
    }

    public String getLogisticName() {
        return logisticName;
    }

    public void setLogisticName(String logisticName) {
        this.logisticName = logisticName;
    }

    public String getLogisticCode() {
        return logisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        this.logisticCode = logisticCode;
    }

    public String getLogisticCompany() {
        return logisticCompany;
    }

    public void setLogisticCompany(String logisticCompany) {
        this.logisticCompany = logisticCompany;
    }

    public String getLogisticContent() {
        return logisticContent;
    }

    public void setLogisticContent(String logisticContent) {
        this.logisticContent = logisticContent;
    }
}

