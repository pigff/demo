package com.fjrcloud.lin.response_parser;

import android.util.Log;

import com.google.gson.Gson;

import org.xutils.http.app.ResponseParser;
import org.xutils.http.request.UriRequest;

import java.lang.reflect.Type;

/**
 * Author: Trun
 * Date: 2016-08-06
 * Description: JsonResponseParser.java
 */
public class JsonResponseParser implements ResponseParser {

    private String url;

    @Override
    public void checkResponse(UriRequest request) throws Throwable {
        url = request.getRequestUri();
    }

    @Override
    public Object parse(Type resultType, Class<?> resultClass, String result) throws Throwable {

        Log.i("resultType:", resultType.toString());
        Log.i("resultClass:", resultClass.toString());
        Log.i("result:", result);

        Gson gson = new Gson();
        Object resultBean = gson.fromJson(result, resultClass);

        Log.v("resultBean:", resultBean.toString());

        return resultBean;
    }
}