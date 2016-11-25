package com.fjrcloud.lin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.lin.R;
import com.fjrcloud.lin.bean.Video;
import com.fjrcloud.lin.ui.base.BaseActivity;
import com.fjrcloud.lin.util.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_town_grid)
public class TownGridActivity extends BaseActivity {

    @ViewInject(R.id.town_grid)
    private RecyclerView mRecyclerView;
    private List<Video> mVideoAttr;
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
                intent2Video.putExtra(Constant.BEAN, mVideoAttr.get(i));
                startActivity(intent2Video);
            }
        });

    }

    private void initAdapter() {
        mGridAdapter = new GridAdapter(R.layout.img_item, mVideoAttr);
    }

    private void initData() {
        mTitle = getIntent().getStringExtra(Constant.BEAN);
        mVideoAttr = new ArrayList<>();
        mVideoAttr.add(new Video(R.mipmap.image, "八字路口(流畅)"));
        mVideoAttr.add(new Video(R.mipmap.image, "马路口(流畅)"));
        mVideoAttr.add(new Video(R.mipmap.image, "十字路口(流畅)"));
        mVideoAttr.add(new Video(R.mipmap.image, "学校路口(流畅)"));
        mVideoAttr.add(new Video(R.mipmap.image, "集市路口(流畅)"));
        mVideoAttr.add(new Video(R.mipmap.image, "乡村路口(流畅)"));
        mVideoAttr.add(new Video(R.mipmap.image, "乡村路口(流畅)"));
        mVideoAttr.add(new Video(R.mipmap.image, "乡村路口(流畅)"));
    }

    class GridAdapter extends BaseQuickAdapter<Video, BaseViewHolder> {

        public GridAdapter(int layoutResId, List<Video> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, Video video) {
            baseViewHolder.setImageResource(R.id.grid_image, video.getImg())
                    .setText(R.id.grid_image_title, video.getName())
                    .addOnClickListener(R.id.img_group);
        }
    }
}
