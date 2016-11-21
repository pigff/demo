package com.example.lin.demo;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

import org.xutils.x;

/**
 * Created by lin on 2016/11/19.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }

}
