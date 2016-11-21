package com.example.lin.demo.bean;

import java.io.Serializable;

/**
 * Created by lin on 2016/11/21.
 */
public class News implements Serializable{

    private String title;

    private String content;

    private Integer img;

    private String time;

    public News(String title, String content, Integer img, String time) {
        this.title = title;
        this.content = content;
        this.img = img;
        this.time = time;
    }

    public News(String title, String content, Integer img) {
        this.title = title;
        this.content = content;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", img=" + img +
                ", time='" + time + '\'' +
                '}';
    }

}

