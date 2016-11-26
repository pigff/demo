package com.fjrcloud.lin.model.bean;


import com.fjrcloud.lin.response_parser.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Author: Trun
 * Date: 2016-08-11
 * Description: normal 请求返回Bean
 */

@HttpResponse(parser = JsonResponseParser.class)
public class ResponseBean {
    private String code;
    private String msg;
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
