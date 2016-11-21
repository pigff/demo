package com.example.lin.demo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.lin.demo.R;
import com.example.lin.demo.adapter.ListAdapter2;
import com.example.lin.demo.bean.News;
import com.example.lin.demo.ui.base.BaseActivity;
import com.example.lin.demo.util.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

@ContentView(R.layout.activity_list)
public class ListActivity extends BaseActivity {

    private List<News> mNewses;

    @ViewInject(R.id.list_lv)
    private ListView mListView;

    @ViewInject(R.id.list_banner)
    private BGABanner mBanner;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    private void initView() {
        setTitle(mTitle);
        mListView.setFocusable(false);
        mBanner.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                ((ImageView) view).setScaleType(ImageView.ScaleType.FIT_XY);
                ((ImageView) view).setImageResource(((int) model));
            }
        });

        mBanner.setData(Arrays.asList(R.mipmap.image, R.mipmap.image, R.mipmap.image, R.mipmap.image), null);
        ListAdapter2 adapter2 = new ListAdapter2(mNewses, this);
        mListView.setAdapter(adapter2);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, DetailedActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.BEAN, mNewses.get(position));
                intent.putExtra(Constant.TITLE, mTitle);
                intent.putExtra(Constant.BUNDLE, bundle);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        mTitle = getIntent().getStringExtra(Constant.TITLE);
        mNewses = new ArrayList<>();
        News news1 = new News("标题1", "这是标题1", R.mipmap.image);
        News news2 = new News("标题2", "这是标题2", R.mipmap.image);
        News news3 = new News("标题3", "这是标题3", R.mipmap.image);
        News news4 = new News("标题4", "这是标题4", R.mipmap.image);
        mNewses.add(news1);
        mNewses.add(news2);
        mNewses.add(news3);
        mNewses.add(news4);
    }
}
