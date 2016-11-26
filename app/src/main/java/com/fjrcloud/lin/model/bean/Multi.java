package com.fjrcloud.lin.model.bean;

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
    public static final int NEWS_RIGHT = 4;
    public static final int DIVIDING = 5;
    public static final int TEXT_IMG = 6;
    public static final int NEWS_LEFT = 7;
    public static final int BIG_BANNER = 8;

    public static final int CATEGORY_SIZE = 1;
    public static final int NORMAL_SIZE = 4;

    private Category[] bannerImgs;
    private List<Category> categories;
    private Category category;
    private News news;
    private int itemType;
    private int spanSize;


    public Multi(int itemType, News news, int spanSize) {
        this.itemType = itemType;
        this.news = news;
        this.spanSize = spanSize;
    }

    public Multi(int itemType, News news) {
        this.itemType = itemType;
        this.news = news;
    }

    public Multi(int itemType, int spanSize) {
        this.itemType = itemType;
        this.spanSize = spanSize;
    }

    public Multi(int itemType) {
        this.itemType = itemType;
    }

    public Multi(int itemType, Category[] bannerImgs, int spanSize) {
        this.itemType = itemType;
        this.bannerImgs = bannerImgs;
        this.spanSize = spanSize;
    }

    public Multi(int itemType, Category[] bannerImgs) {
        this.itemType = itemType;
        this.bannerImgs = bannerImgs;
    }

    public Multi(int itemType, Category category, int spanSize) {
        this.itemType = itemType;
        this.category = category;
        this.spanSize = spanSize;
    }


    public Category[] getBannerImgs() {
        return bannerImgs;
    }

    public void setBannerImgs(Category[] bannerImgs) {
        this.bannerImgs = bannerImgs;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }
}
