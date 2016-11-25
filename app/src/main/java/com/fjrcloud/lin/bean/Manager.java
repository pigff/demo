package com.fjrcloud.lin.bean;

/**
 * Created by lin on 2016/11/24.
 */
public class Manager {

    private String name;

    private String duty;

    private String age;

    private String num;

    private String area;

    private Integer pic;

    public Manager(String name, String duty, String age, String num, String area, Integer pic) {
        this.name = name;
        this.duty = duty;
        this.age = age;
        this.num = num;
        this.area = area;
        this.pic = pic;
    }

    public Manager() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getPic() {
        return pic;
    }

    public void setPic(Integer pic) {
        this.pic = pic;
    }
}
