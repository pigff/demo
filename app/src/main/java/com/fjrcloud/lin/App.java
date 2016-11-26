package com.fjrcloud.lin;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.EzvizAPI;

import org.xutils.x;

/**
 * Created by lin on 2016/11/19.
 */
public class App extends Application {

    private static App instance = null;
    public static String APP_KEY = "2c4440c39a164604b8ec6ada8a1c5de2";
    public static String APP_PUSH_SECRETE = "84db09c066925eb007cd5eefb9e564a1";
    public static String API_URL = "https://open.ys7.com";
    public static String WEB_URL = "https://auth.ys7.com";

    @Override
    public void onCreate() {
        super.onCreate();
        EZOpenSDK.initLib(this, APP_KEY, "");
        EzvizAPI.getInstance().setServerUrl(API_URL, WEB_URL);
        instance = this;
        SDKInitializer.initialize(this);
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }

    public static App getInstance() {
        if (instance == null) {
            return new App();
        }
        return instance;
    }

    public static EZOpenSDK getOpenSDK() {
        return EZOpenSDK.getInstance();
    }


}
