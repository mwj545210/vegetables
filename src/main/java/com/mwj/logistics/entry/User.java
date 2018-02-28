package com.mwj.logistics.entry;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by MWJ on 2017/12/25.
 */
@Table
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "USER_ID",unique = true, nullable = false, insertable = true, updatable = true)
    private int userId;

    @Column(name = "USER_NAME",unique = false, nullable = false, insertable = true, updatable = true)
    private String userName;

    @Column(name = "USER_PASSWORD",unique = false, nullable = false, insertable = true, updatable = true)
    private String password;

    @Column(name = "AUTHORITY",unique = false, nullable = false, insertable = true, updatable = true)
    private boolean authority;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthority() {
        return authority;
    }

    public void setAuthority(boolean authority) {
        this.authority = authority;
    }
}

