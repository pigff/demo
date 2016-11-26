package com.fjrcloud.lin.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.fjrcloud.lin.R;
import com.fjrcloud.lin.ui.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by lin on 2016/10/10.
 */
@ContentView(R.layout.activity_show_image)
public class ShowImageActivity extends BaseActivity {

    @ViewInject(R.id.show_big_image)
    private PhotoView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("查看图片");
        Intent intent = getIntent();
        String imgUrl = intent.getStringExtra(CheckPicActivity.INTENT_SELECTED_PICTURE);
        Glide.with(this).load(imgUrl).fitCenter()
                .error(R.mipmap.error_image).into(mImageView);

    }
}
