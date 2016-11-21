package com.example.lin.demo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.lin.demo.R;
import com.example.lin.demo.bean.Video;
import com.example.lin.demo.ui.base.BaseActivity;
import com.example.lin.demo.util.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_town_grid)
public class TownGridActivity extends BaseActivity {

    @ViewInject(R.id.town_grid)
    private RecyclerView mRecyclerView;
    private List<Video> mVideoName;
    private GridAdapter mGridAdapter;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initAdapter();
        initView();
    }

    private void initView() {
        setTitle(mTitle);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mGridAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent2Video = new Intent(TownGridActivity.this, VideoActivity.class);
               intent2Video.putExtra(Constant.BEAN, mVideoName.get(i));
                startActivity(intent2Video);
            }
        });

    }

    private void initAdapter() {
        mGridAdapter = new GridAdapter(R.layout.img_item, mVideoName);
    }

    private void initData() {
        mTitle = getIntent().getStringExtra(Constant.BEAN);
        mVideoName = new ArrayList<>();
        mVideoName.add(new Video(R.mipmap.third_2, "八字路口(流畅)"));
        mVideoName.add(new Video(R.mipmap.third_3, "马路口(流畅)"));
        mVideoName.add(new Video(R.mipmap.third_4, "十字路口(流畅)"));
        mVideoName.add(new Video(R.mipmap.third_5, "学校路口(流畅)"));
        mVideoName.add(new Video(R.mipmap.third_6, "集市路口(流畅)"));
        mVideoName.add(new Video(R.mipmap.third_7, "乡村路口(流畅)"));
        mVideoName.add(new Video(R.mipmap.third_8, "乡村路口(流畅)"));
        mVideoName.add(new Video(R.mipmap.third_9, "乡村路口(流畅)"));
    }

    class GridAdapter extends BaseQuickAdapter<Video> {

        public GridAdapter(int layoutResId, List<Video> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, Video video) {
            baseViewHolder.setImageResource(R.id.grid_image, video.getImg())
                    .addOnClickListener(R.id.img_group);
        }
    }
}
