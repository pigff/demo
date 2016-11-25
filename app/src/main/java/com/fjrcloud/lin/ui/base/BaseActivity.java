package com.fjrcloud.lin.ui.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fjrcloud.lin.R;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by lin on 2016/11/19.
 */
public class BaseActivity extends Activity {

    @ViewInject(R.id.titlebar_title)
    private TextView mTitleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void setTitle(String title) {
        mTitleText.setText(title);
    }

    @Event(R.id.titlebar_backbutton)
    private void onClick(View view) {
        finish();
    }
}
