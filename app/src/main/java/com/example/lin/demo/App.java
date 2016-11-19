package com.example.lin.demo;

import android.app.Application;

import org.xutils.x;

/**
 * Created by lin on 2016/11/19.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }

}
