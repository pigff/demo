package com.fjrcloud.lin.ui.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.fjrcloud.lin.R;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by lin on 2016/11/19.
 */
public class BaseFragmentActivity extends FragmentActivity {

    @ViewInject(R.id.titlebar_title)
    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void setTitle(String title) {
        if (mTextView != null) {
            mTextView.setText(title);
        }
    }

    @Event(R.id.titlebar_backbutton)
    private void onClick(View view) {
        finish();
    }
}
