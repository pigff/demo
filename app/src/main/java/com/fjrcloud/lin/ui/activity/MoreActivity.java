package com.fjrcloud.lin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.lin.R;
import com.fjrcloud.lin.adapter.MultiAdapter;
import com.fjrcloud.lin.model.bean.CategoryBean;
import com.fjrcloud.lin.model.bean.Multi;
import com.fjrcloud.lin.model.bean.NewsBean;
import com.fjrcloud.lin.model.domain.Article;
import com.fjrcloud.lin.ui.base.BaseActivity;
import com.fjrcloud.lin.util.Constant;
import com.fjrcloud.lin.util.HtmlUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_more)
public class MoreActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener{


    @ViewInject(R.id.more_rv)
    private RecyclerView mRecyclerView;
    private String mTitle;
    private List<Multi> mMultis;
    private MultiAdapter mMultiAdapter;
    private int mId;
    private int mPageNum;
    private int mPageSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initAdapter();
        initView();
        initListener();
        getData();
    }

    private void initListener() {
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                int type = baseQuickAdapter.getItemViewType(i);
                switch (type) {
                    case Multi.NEWS_LEFT:
                        Intent intent = new Intent(MoreActivity.this, DetailedActivity.class);
                        intent.putExtra(Constant.BEAN, ((Multi) baseQuickAdapter.getItem(i)).getNews());
                        intent.putExtra(Constant.TITLE, mTitle);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private void initAdapter() {
        mMultiAdapter = new MultiAdapter(mMultis);
        mMultiAdapter.setOnLoadMoreListener(this);
        mMultiAdapter.setEnableLoadMore(true);
    }

    private void initView() {
        setTitle(mTitle);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mMultiAdapter);

    }

    private void initData() {
        Intent intent = getIntent();
        mTitle = intent.getStringExtra(Constant.TITLE);
        mId = intent.getIntExtra(Constant.ID, -1);
        mPageNum = 0;
        mPageSize = 10;
        mMultis = new ArrayList<>();
        CategoryBean.Category[] imgs = new CategoryBean.Category[]{new CategoryBean.Category("xixi", "http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1112/28/c11/10084076_10084076_1325087736046.jpg"),
                new CategoryBean.Category("xixi", "http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1112/28/c11/10084076_10084076_1325087736046.jpg"),
                new CategoryBean.Category("xixi", "http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1112/28/c11/10084076_10084076_1325087736046.jpg"),
                new CategoryBean.Category("xixi", "http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1112/28/c11/10084076_10084076_1325087736046.jpg")};
        mMultis.add(new Multi(Multi.BIG_BANNER, imgs));
        mMultis.add(new Multi(Multi.TEXT_IMG));
    }

    private void getData() {
        if (mId != -1) {
            getArticlesByCg(new Article().new FindArticlesByCg(mId, mPageNum, mPageSize));
        } else {
            getAllArticles(new Article().new FindAllArticles(mPageNum, mPageSize));
        }
    }

    private void getAllArticles(RequestParams params) {
        x.http().post(params, new Callback.CommonCallback<NewsBean>() {

            @Override
            public void onSuccess(NewsBean result) {
                setData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(MoreActivity.this, R.string.unknow_error, Toast.LENGTH_SHORT).show();
                mMultiAdapter.loadMoreFail();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void setData(NewsBean result) {
        List<Multi> multis = new ArrayList<>();
        for (int i = 0; i < result.getData().getContent().size(); i++) {
            result.getData().getContent().get(i).
                    setContent(HtmlUtil.getTextFromHtml(result.getData().getContent().get(i).getContent()));
            multis.add(new Multi(Multi.NEWS_LEFT, result.getData().getContent().get(i)));
        }
        mMultiAdapter.addData(multis);
        if (result.getData().getContent().size() == mPageSize) {
            mMultiAdapter.loadMoreComplete();
        } else {
            mMultiAdapter.loadMoreEnd();
        }
    }

    private void getArticlesByCg(RequestParams params) {
        x.http().post(params, new Callback.CommonCallback<NewsBean>() {

            @Override
            public void onSuccess(NewsBean result) {
                setData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(MoreActivity.this, R.string.unknow_error, Toast.LENGTH_SHORT).show();
                mMultiAdapter.loadMoreFail();
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
    public void onLoadMoreRequested() {
        mPageNum++;
        getData();
    }
}
