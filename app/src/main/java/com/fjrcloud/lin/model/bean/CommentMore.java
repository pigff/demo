package com.fjrcloud.lin.model.bean;

import java.io.Serializable;

/**
 * Created by lin on 2016/12/6.
 */
public class CommentMore implements Serializable{

    private String title;
    // 0：表示表扬  1：表示批评
    private int comment;

    public CommentMore(String title, int comment) {
        this.title = title;
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }
}
