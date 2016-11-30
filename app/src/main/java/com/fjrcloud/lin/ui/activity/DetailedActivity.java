package com.fjrcloud.lin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.fjrcloud.lin.R;
import com.fjrcloud.lin.model.bean.NewsBean;
import com.fjrcloud.lin.model.bean.ResponseBean;
import com.fjrcloud.lin.model.domain.Article;
import com.fjrcloud.lin.ui.base.BaseActivity;
import com.fjrcloud.lin.util.Constant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_detailed)
public class DetailedActivity extends BaseActivity {


    @ViewInject(R.id.detailed_title)
    private TextView mDetailedTitle;
    @ViewInject(R.id.detailed_check_count)
    private TextView mCountTv;
    @ViewInject(R.id.detailed_web)
    private WebView mWebView;
    private NewsBean.DataEntity.News mNews;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
        addCount(new Article().new AddViewCount(mNews.getId()));
    }

    private void initView() {
        setTitle(mTitle);
        if (mNews != null) {
            if (!TextUtils.isEmpty(mNews.getViews())) {
                mCountTv.setText(String.valueOf(Integer.parseInt(mNews.getViews()) + 1));
            } else {
                mCountTv.setText("1");
            }
            mDetailedTitle.setText(mNews.getTitle());
            mWebView.getSettings().setDomStorageEnabled(true);
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.loadData(mNews.getContent(), "text/html; charset=utf-8", "utf-8");
            mWebView.setWebViewClient(new MyWebViewClient());
        } else {
            Toast.makeText(this, R.string.unknow_error, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    //Web视图
    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private void initData() {
        Intent intent = getIntent();
        mNews = (NewsBean.DataEntity.News) intent.getSerializableExtra(Constant.BEAN);
        mTitle = intent.getStringExtra(Constant.TITLE);
    }

//    public void jump(View view) {
//        Intent intent = new Intent(this, ManagerActivity.class);
//        startActivity(intent);
//    }
    private void addCount(RequestParams params) {
        x.http().post(params, new Callback.CommonCallback<ResponseBean>() {
            @Override
            public void onSuccess(ResponseBean result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
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
