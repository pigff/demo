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
import com.fjrcloud.lin.adapter.NewsMultiAdapter;
import com.fjrcloud.lin.model.bean.AdBean;
import com.fjrcloud.lin.model.bean.NewsMulti;
import com.fjrcloud.lin.model.bean.NewsBean;
import com.fjrcloud.lin.model.domain.Advertising;
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
public class MoreActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener {


    @ViewInject(R.id.more_rv)
    private RecyclerView mRecyclerView;
    private String mTitle;
    private List<NewsMulti> mMultis;
    private NewsMultiAdapter mMultiAdapter;
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
                    case NewsMulti.NEWS_LEFT:
                        Intent intent = new Intent(MoreActivity.this, DetailedActivity.class);
                        intent.putExtra(Constant.BEAN, ((NewsMulti) baseQuickAdapter.getItem(i)).getNews());
                        intent.putExtra(Constant.TITLE, mTitle);
                        intent.putExtra(Constant.CONTENT, ((NewsMulti) baseQuickAdapter.getItem(i)).getOgContent());
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private void initAdapter() {
        mMultiAdapter = new NewsMultiAdapter(mMultis);
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
        mPageSize = 16;
        mMultis = new ArrayList<>();
    }

    private void getData() {
        getAd(new Advertising().new FindAdByCategory(mId, 0, 8));
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
        List<NewsMulti> multis = new ArrayList<>();
        for (int i = 0; i < result.getData().getContent().size(); i++) {
            String ogContent = result.getData().getContent().get(i).getContent();
            result.getData().getContent().get(i).
                    setContent(HtmlUtil.getTextFromHtml(ogContent));
            multis.add(new NewsMulti(NewsMulti.NEWS_LEFT, result.getData().getContent().get(i), ogContent));
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

    private void getAd(RequestParams params) {
        x.http().post(params, new Callback.CommonCallback<AdBean>() {
            @Override
            public void onSuccess(AdBean result) {
                List<NewsMulti> multis = new ArrayList<NewsMulti>();
                NewsMulti multi = null;
                if (result.getData().getContent().size() > 0) {
                    multi = new NewsMulti(NewsMulti.BANNER, result.getData().getContent().
                            toArray(new AdBean.DataEntity.Ad[result.getData().getContent().size()]), NewsMulti.NORMAL_SIZE);
                } else {
                    multi = new NewsMulti(NewsMulti.BANNER,
                            new AdBean.DataEntity.Ad[]{new AdBean.DataEntity.Ad("广告位招租", "http://www.baosteelresources.com/baogang/new_web/images/top_yewu_02.jpg")}, NewsMulti.NORMAL_SIZE);
                }
                multis.add(multi);
                multis.add(new NewsMulti(NewsMulti.TEXT_IMG));
                mMultiAdapter.addData(multis);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                List<NewsMulti> multis = new ArrayList<NewsMulti>();
                NewsMulti multi = new NewsMulti(NewsMulti.BANNER,
                        new AdBean.DataEntity.Ad[]{new AdBean.DataEntity.Ad("广告位招租", "http://www.baosteelresources.com/baogang/new_web/images/top_yewu_02.jpg")}, NewsMulti.NORMAL_SIZE);
                multis.add(multi);
                multis.add(new NewsMulti(NewsMulti.TEXT_IMG));
                mMultiAdapter.addData(multis);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                if (mId != -1) {
                    getArticlesByCg(new Article().new FindArticlesByCg(mId, mPageNum, mPageSize));
                } else {
                    getAllArticles(new Article().new FindAllArticles(mPageNum, mPageSize));
                }
            }
        });
    }

    @Override
    public void onLoadMoreRequested() {
        mPageNum++;
        if (mId != -1) {
            getArticlesByCg(new Article().new FindArticlesByCg(mId, mPageNum, mPageSize));
        } else {
            getAllArticles(new Article().new FindAllArticles(mPageNum, mPageSize));
        }
    }
}
