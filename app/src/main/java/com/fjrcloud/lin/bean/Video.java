package com.fjrcloud.lin.bean;

import java.io.Serializable;

/**
 * Created by lin on 2016/11/21.
 */
public class Video implements Serializable{

    private Integer img;

    private String name;

    public Video(Integer img, String name) {
        this.img = img;
        this.name = name;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
