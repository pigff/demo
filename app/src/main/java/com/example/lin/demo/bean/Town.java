package com.example.lin.demo.bean;

import java.io.Serializable;

/**
 * Created by lin on 2016/11/21.
 */
public class Town implements Serializable{

    private String name;

    public Town(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
