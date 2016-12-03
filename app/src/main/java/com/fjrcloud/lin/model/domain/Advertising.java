package com.fjrcloud.lin.model.domain;

import com.fjrcloud.lin.util.Constant;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;
import org.xutils.http.app.DefaultParamsBuilder;

/**
 * Created by lin on 2016/12/3.
 */
public class Advertising {

    /**
     * 获取广告
     */
    @HttpRequest(
            host = Constant.SERVICE_HOST,
            path = Constant.AD + "findByCategory",
            builder = DefaultParamsBuilder.class)
    public class FindAdByCategory extends RequestParams {
        private Integer articleCategoryId;
        private Integer pageNum;
        private Integer pageSize;

        public FindAdByCategory(Integer articleCategoryId, Integer pageNum, Integer pageSize) {
            super();
            this.articleCategoryId = articleCategoryId;
            this.pageNum = pageNum;
            this.pageSize = pageSize;
        }
    }

}
