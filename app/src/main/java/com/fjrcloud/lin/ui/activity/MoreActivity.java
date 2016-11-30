package com.fjrcloud.lin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.lin.R;
import com.fjrcloud.lin.adapter.MultiAdapter;
import com.fjrcloud.lin.model.bean.CategoryBean;
import com.fjrcloud.lin.model.bean.Multi;
import com.fjrcloud.lin.ui.base.BaseActivity;
import com.fjrcloud.lin.util.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_more)
public class MoreActivity extends BaseActivity {


    @ViewInject(R.id.more_rv)
    private RecyclerView mRecyclerView;
    private String mTitle;
    private List<Multi> mMultis;
    private MultiAdapter mMultiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initAdapter();
        initView();
        initListener();
    }

    private void initListener() {
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                int type = baseQuickAdapter.getItemViewType(i);
                switch (type) {
                    case Multi.NEWS_LEFT:
                        Intent intent = new Intent(MoreActivity.this, DetailedActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(Constant.BEAN, ((Multi) baseQuickAdapter.getItem(i)).getNews());
                        intent.putExtra(Constant.TITLE, mTitle);
                        intent.putExtra(Constant.BUNDLE, bundle);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private void initAdapter() {
        mMultiAdapter = new MultiAdapter(mMultis);
    }

    private void initView() {
        setTitle(mTitle);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mMultiAdapter);
    }

    private void initData() {
        mTitle = getIntent().getStringExtra(Constant.TITLE);
        mMultis = new ArrayList<>();
        CategoryBean.Category[] imgs = new CategoryBean.Category[]{new CategoryBean.Category("xixi", "http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1112/28/c11/10084076_10084076_1325087736046.jpg"),
                new CategoryBean.Category("xixi", "http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1112/28/c11/10084076_10084076_1325087736046.jpg"),
                new CategoryBean.Category("xixi", "http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1112/28/c11/10084076_10084076_1325087736046.jpg"),
                new CategoryBean.Category("xixi", "http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1112/28/c11/10084076_10084076_1325087736046.jpg")};
        mMultis.add(new Multi(Multi.BIG_BANNER, imgs));
        mMultis.add(new Multi(Multi.TEXT_IMG));

    }
}
