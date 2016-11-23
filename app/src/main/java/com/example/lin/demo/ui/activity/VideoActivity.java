package com.example.lin.demo.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lin.demo.R;
import com.example.lin.demo.bean.Video;
import com.example.lin.demo.ui.base.BaseActivity;
import com.example.lin.demo.util.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
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

    @Event(value = {R.id.capture, R.id.upload, R.id.policy})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.capture:
                Toast.makeText(this, "抓拍", Toast.LENGTH_SHORT).show();
                break;
            case R.id.upload:
                Toast.makeText(this, "上传", Toast.LENGTH_SHORT).show();
                break;
            case R.id.policy:
                Toast.makeText(this, "报警", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private void initView() {
        setTitle(mVideo.getName());
        mImageView.setImageResource(mVideo.getImg());
    }

    private void initData() {
        mVideo = (Video) getIntent().getSerializableExtra(Constant.BEAN);
    }
}
