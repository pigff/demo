package com.fjrcloud.lin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.fjrcloud.lin.R;
import com.fjrcloud.lin.model.bean.NewsBean;
import com.fjrcloud.lin.ui.base.BaseActivity;
import com.fjrcloud.lin.util.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_detailed)
public class DetailedActivity extends BaseActivity {

    private String mTitle;

    @ViewInject(R.id.detailed_title)
    private TextView mDetailedTitle;

    @ViewInject(R.id.detailed_content)
    private TextView mContent;
    private NewsBean mNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    private void initView() {
        setTitle(mTitle);
        mDetailedTitle.setText("福清高山山羊");
//        mContent.setText(mNews.getContent());
    }

    private void initData() {
        Intent intent = getIntent();
        mTitle = intent.getStringExtra(Constant.TITLE);
//        Bundle bundle = intent.getBundleExtra(Constant.BUNDLE);
    }

//    public void jump(View view) {
//        Intent intent = new Intent(this, ManagerActivity.class);
//        startActivity(intent);
//    }

}
