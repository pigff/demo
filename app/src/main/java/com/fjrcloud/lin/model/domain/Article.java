package com.fjrcloud.lin.model.domain;

import com.fjrcloud.lin.util.Constant;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;
import org.xutils.http.app.DefaultParamsBuilder;

/**
 * Created by lin on 2016/11/30.
 */
public class Article {

    /**
     * 获取新闻分类
     */
    @HttpRequest(
            host = Constant.SERVICE_HOST,
            path = Constant.ARTICLE_CATEGORY + "findAll",
            builder = DefaultParamsBuilder.class)
    public class FindAllCategory extends RequestParams {

        public FindAllCategory() {
            super();
        }
    }

    /**
     * 根据分类获取新闻
     */
    @HttpRequest(
            host = Constant.SERVICE_HOST,
            path = Constant.ARTICLE + "findByCategory",
            builder = DefaultParamsBuilder.class)
    public class FindArticlesByCg extends RequestParams {

        private Integer articleCategoryId;
        private Integer pageNum;
        private Integer pageSize;

        public FindArticlesByCg(Integer articleCategoryId, Integer pageNum,
                                Integer pageSize) {
            super();
            this.articleCategoryId = articleCategoryId;
            this.pageNum = pageNum;
            this.pageSize = pageSize;
        }
    }

    /**
     * 获取单条新闻
     */
    @HttpRequest(
            host = Constant.SERVICE_HOST,
            path = Constant.ARTICLE + "findById",
            builder = DefaultParamsBuilder.class)
    public class FindArticleById extends RequestParams {
        private Integer articleId;

        public FindArticleById(Integer articleId) {
            super();
            this.articleId = articleId;
        }
    }

    /**
     * 获取所有新闻
     */
    @HttpRequest(
            host = Constant.SERVICE_HOST,
            path = Constant.ARTICLE + "findAll",
            builder = DefaultParamsBuilder.class)
    public class FindAllArticles extends RequestParams {

        private Integer pageNum;

        private Integer pageSize;

        public FindAllArticles(Integer pageNum, Integer pageSize) {
            super();
            this.pageNum = pageNum;
            this.pageSize = pageSize;
        }
    }

    /**
     * 获取所有新闻
     */
    @HttpRequest(
            host = Constant.SERVICE_HOST,
            path = Constant.ARTICLE + "updateViews",
            builder = DefaultParamsBuilder.class)
    public class AddViewCount extends RequestParams {

        private Integer articleId;


        public AddViewCount(Integer articleId) {
            super();
            this.articleId = articleId;
        }
    }
}
