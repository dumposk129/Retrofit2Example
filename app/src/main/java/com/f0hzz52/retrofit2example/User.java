package com.f0hzz52.retrofit2example;

import com.google.gson.annotations.Expose;

public class User {
    @Expose
    private Integer id;
    @Expose
    private String login;

    @Expose
    private String name;

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }
}
