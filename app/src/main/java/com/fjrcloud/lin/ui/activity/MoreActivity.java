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
import com.fjrcloud.lin.model.bean.Category;
import com.fjrcloud.lin.model.bean.Multi;
import com.fjrcloud.lin.model.bean.News;
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
        Category[] imgs = new Category[]{new Category("xixi", R.mipmap.banner_3), new Category("xixi", R.mipmap.banner_3)
                , new Category("xixi", R.mipmap.banner_3), new Category("xixi", R.mipmap.banner_3)};
        News news1 = new News("鱼溪龙眼", "龙眼（学名：Dimocarpus longan Lour.），又称桂圆，益智。常绿乔木，高通常10余米；小枝粗壮，被微柔毛....", R.mipmap.m_5);
        News news2 = new News("一都枇杷", "一都枇杷是福建省福州市福清市一都镇的特产。一都枇杷以“果大、色艳、肉厚...", R.mipmap.m_3);
        News news3 = new News("嘉儒蛤", "嘉儒蛤是福建福州福清的特产。嘉儒蛤 福建省福清市,北纬25°41′43.89″至25°29′33.58″,东经119°27′42.73″...", R.mipmap.m_1);
        News news4 = new News("福清海蛎饼", "福清是沿海城市，而海蛎饼则是福清的汉族小吃。海蛎饼，以新鲜海蛎为主要原料，搭配上紫菜...", R.mipmap.m_7);
        mMultis.add(new Multi(Multi.BIG_BANNER, imgs));
        mMultis.add(new Multi(Multi.TEXT_IMG));
        mMultis.add(new Multi(Multi.NEWS_LEFT, news1));
        mMultis.add(new Multi(Multi.NEWS_LEFT, news2));
        mMultis.add(new Multi(Multi.NEWS_LEFT, news3));
        mMultis.add(new Multi(Multi.NEWS_LEFT, news4));
    }
}
