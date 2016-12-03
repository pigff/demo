package com.fjrcloud.lin.model.domain;

import com.fjrcloud.lin.util.Constant;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;
import org.xutils.http.app.DefaultParamsBuilder;

/**
 * Created by lin on 2016/12/3.
 */
public class Update {

    /**
     * 应用更新
     */
    @HttpRequest(
            host = Constant.SERVICE_HOST,
            path = Constant.APP + "getVersion",
            builder = DefaultParamsBuilder.class)
    public class GetVersion extends RequestParams {

        public GetVersion() {
            super();
        }
    }
}
