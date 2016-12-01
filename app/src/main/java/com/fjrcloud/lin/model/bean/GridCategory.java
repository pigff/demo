package com.fjrcloud.lin.model.bean;

/**
 * Created by lin on 2016/11/30.
 */
public class GridCategory {

    private String name;

    private int img;

    public GridCategory(String name, int img) {
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
