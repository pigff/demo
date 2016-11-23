package com.example.lin.demo.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lin on 2016/11/22.
 */
public class Multi implements MultiItemEntity, Serializable {

    public static final int BANNER = 1;
    public static final int CATEGORY = 2;
    public static final int BIG_IMG = 3;
    public static final int LIST = 4;
    public static final int DIVIDING = 5;

    private Integer[] bannerImgs;
    private List<Category> categories;
    private Integer bigImg;
    private News news;
    private int itemType;

    public Multi(int itemType, News news) {
        this.itemType = itemType;
        this.news = news;
    }

    public Multi(int itemType) {
        this.itemType = itemType;
    }

    public Multi(int itemType, Integer[] bannerImgs) {
        this.itemType = itemType;
        this.bannerImgs = bannerImgs;
    }

    public Multi(int itemType, List<Category> categories) {
        this.itemType = itemType;
        this.categories = categories;
    }

    public Multi(int itemType, Integer bigImg) {
        this.itemType = itemType;
        this.bigImg = bigImg;
    }

    public Integer[] getBannerImgs() {
        return bannerImgs;
    }

    public void setBannerImgs(Integer[] bannerImgs) {
        this.bannerImgs = bannerImgs;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Integer getBigImg() {
        return bigImg;
    }

    public void setBigImg(Integer bigImg) {
        this.bigImg = bigImg;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
