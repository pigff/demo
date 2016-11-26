package com.fjrcloud.lin.model.domain;

import com.fjrcloud.lin.util.Constant;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;
import org.xutils.http.app.DefaultParamsBuilder;

/**
 * Created by lin on 2016/11/26.
 */
public class YsCamera {

    /**
     * 获取我的诉求
     */
    @HttpRequest(
            host = Constant.SERVICE_HOST,
            path = Constant.YS + "getYsToken",
            builder = DefaultParamsBuilder.class)
    public class GetYsToken extends RequestParams {

        public GetYsToken() {
            super();
        }
    }

    /**
     * 获取我的诉求
     */
    @HttpRequest(
            host = Constant.SERVICE_HOST,
            path = Constant.YS + "getCameraList",
            builder = DefaultParamsBuilder.class)
    public class GetCamera extends RequestParams {

        public GetCamera() {
            super();
        }
    }
}
