package com.fjrcloud.lin.model.bean;

/**
 * Created by lin on 2016/12/6.
 */
public class CommentBean {

    private String title;
    private String date;

    public CommentBean(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
