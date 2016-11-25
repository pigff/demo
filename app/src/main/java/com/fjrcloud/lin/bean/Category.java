package com.fjrcloud.lin.bean;

import java.io.Serializable;

/**
 * Created by lin on 2016/11/21.
 */
public class Category implements Serializable{

    private String name;

    private Integer img;

    public Category() {
    }

    public Category(String name, Integer img) {
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Category{" +
                "img=" + img +
                ", name='" + name + '\'' +
                '}';
    }
}
