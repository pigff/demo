package com.fjrcloud.lin.model.domain;

import com.fjrcloud.lin.util.Constant;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;
import org.xutils.http.app.DefaultParamsBuilder;

/**
 * Created by lin on 2016/11/28.
 */
public class YsTown {

    /**
     * 获取村的列表   parentId null
     */
    @HttpRequest(
            host = Constant.SERVICE_HOST,
            path = Constant.AREA + "findByParent",
            builder = DefaultParamsBuilder.class)
    public class FindTownByParent extends RequestParams {

        private Integer parentId;

        public FindTownByParent(Integer parentId) {
            super();
            this.parentId = parentId;
        }
    }

    /**
     * 获取村下的摄像机列表
     */
    @HttpRequest(
            host = Constant.SERVICE_HOST,
            path = Constant.CAMERA + "findByArea",
            builder = DefaultParamsBuilder.class)
    public class FindCameraByArea extends RequestParams {

        private Integer areaId;

        public FindCameraByArea(Integer areaId) {
            super();
            this.areaId = areaId;
        }
    }

}
