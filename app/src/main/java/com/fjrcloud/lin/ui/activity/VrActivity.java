package com.fjrcloud.lin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.fjrcloud.lin.R;
import com.fjrcloud.lin.model.bean.TownBean;
import com.fjrcloud.lin.ui.base.BaseActivity;
import com.fjrcloud.lin.util.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_vr)
public class VrActivity extends BaseActivity {

    @ViewInject(R.id.vr_group)
    private FrameLayout mLayout;
    private WebView mWebView;
    private TownBean.Town mTown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    private void initView() {
        setTitle(mTown.getName());
        mWebView = new WebView(getApplicationContext());
        mWebView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mLayout.addView(mWebView);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//        mWebView.loadUrl("http://720yun.com/t/bcc2e98uaui?pano_id=1527234&from=singlemessage&isappinstalled=0");
        mWebView.loadUrl(mTown.getVrPath());
        mWebView.setWebViewClient(new MyWebViewClient());
    }

    private void initData() {
        Intent intent = getIntent();
        mTown = (TownBean.Town) intent.getSerializableExtra(Constant.BEAN);
    }

    //Web视图
    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        if (mWebView != null) {
            ViewParent parent = mWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebView);
            }
            mWebView.stopLoading();
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearHistory();
            mWebView.removeAllViews();
            mWebView.destroy();
        }
        super.onDestroy();
    }
}
