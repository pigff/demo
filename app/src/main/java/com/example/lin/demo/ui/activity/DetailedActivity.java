package com.example.lin.demo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lin.demo.R;
import com.example.lin.demo.bean.News;
import com.example.lin.demo.ui.base.BaseActivity;
import com.example.lin.demo.util.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_detailed)
public class DetailedActivity extends BaseActivity {

    private String mTitle;

    @ViewInject(R.id.detailed_title)
    private TextView mDetailedTitle;

    @ViewInject(R.id.detailed_content)
    private TextView mContent;
    private News mNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    private void initView() {
        setTitle(mTitle);
        mDetailedTitle.setText(mNews.getTitle());
        mContent.setText(mNews.getContent());
    }

    private void initData() {
        Intent intent = getIntent();
        mTitle = intent.getStringExtra(Constant.TITLE);
        Bundle bundle = intent.getBundleExtra(Constant.BUNDLE);
        mNews = (News) bundle.getSerializable(Constant.BEAN);
    }
}
