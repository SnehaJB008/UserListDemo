package com.samcaraschools.userlistdemo.module;

import java.util.List;

public class UserData {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<User> data;

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

}
