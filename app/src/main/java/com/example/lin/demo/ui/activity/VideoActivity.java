package com.example.lin.demo.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.lin.demo.R;
import com.example.lin.demo.bean.Video;
import com.example.lin.demo.ui.base.BaseActivity;
import com.example.lin.demo.util.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_video)
public class VideoActivity extends BaseActivity {

    @ViewInject(R.id.video_img)
    private ImageView mImageView;
    private Video mVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    private void initView() {
        setTitle(mVideo.getName());
        mImageView.setImageResource(mVideo.getImg());
    }

    private void initData() {
        mVideo = (Video) getIntent().getSerializableExtra(Constant.BEAN);
    }
}
