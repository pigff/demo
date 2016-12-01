package com.fjrcloud.lin.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fjrcloud.lin.R;
import com.fjrcloud.lin.adapter.MainFragmentAdapter;
import com.fjrcloud.lin.ui.base.BaseActivity;
import com.fjrcloud.lin.util.DisplayUtil;
import com.fjrcloud.lin.util.custom_view.NoScrollPager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewInject(R.id.main_tabs)
    private TabLayout mTabLayout;

    @ViewInject(R.id.main_viewpager)
    private NoScrollPager mViewPager;

    @ViewInject(R.id.titlebar_backbutton)
    private LinearLayout mBackBtn;

    @ViewInject(R.id.titlebar_group)
    private RelativeLayout mRelativeLayout;

    private boolean mFirst = true;

    List<String> mTitles;
    List<Integer> mImageIds;
    List<Integer> mImageOnIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initViewPager();
        for (int i = 0; i < mTitles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(getTabView(i)));
        }

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                View v = tab.getCustomView();
                ImageView imageView = (ImageView) v.findViewById(R.id.iv_tab);
                imageView.setImageResource(mImageOnIds.get(position));
                TextView textView = (TextView) v.findViewById(R.id.iv_text);
                textView.setTextColor(Color.rgb(59, 186, 255));
                mViewPager.setCurrentItem(tab.getPosition(), false);
                if (position == 1 || position == 2) {
                    mRelativeLayout.setVisibility(View.GONE);
                } else {
                    mRelativeLayout.setVisibility(View.VISIBLE);
                    setTitle(mTitles.get(position));
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View v = tab.getCustomView();
                ImageView cImageView = (ImageView) v.findViewById(R.id.iv_tab);
                cImageView.setImageResource(mImageIds.get(tab.getPosition()));
                TextView cTextView = (TextView) v.findViewById(R.id.iv_text);
                cTextView.setTextColor(Color.rgb(149, 152, 167));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void initViewPager() {
        MainFragmentAdapter mainFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager(), mTitles);
        mViewPager.setAdapter(mainFragmentAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPager.setOffscreenPageLimit(3);
    }

    private void init() {
//        getActionToolBar().setNavigationIcon(null);
        mBackBtn.setVisibility(View.GONE);
        mTitles = new ArrayList<>();
        mTitles.add("首页");
        mTitles.add("美丽乡村");
        mTitles.add("平安家园");
        mTitles.add("个人中心");

        mImageIds = new ArrayList<>();
        mImageIds.add(R.mipmap.icon_home_off);
        mImageIds.add(R.mipmap.icon_by_town_off);
        mImageIds.add(R.mipmap.icon_safe_home_off);
        mImageIds.add(R.mipmap.icon_center_off);

        mImageOnIds = new ArrayList<>();
        mImageOnIds.add(R.mipmap.icon_home_on);
        mImageOnIds.add(R.mipmap.icon_by_town_on);
        mImageOnIds.add(R.mipmap.icon_safe_home_on);
        mImageOnIds.add(R.mipmap.icon_center_on);

        setTitle(mTitles.get(0));
    }

    public View getTabView(int position) {
        View v = LayoutInflater.from(this).inflate(R.layout.design_tab_layout, null);
        ImageView imageView = (ImageView) v.findViewById(R.id.iv_tab);
        TextView textView = (TextView) v.findViewById(R.id.iv_text);
        textView.setText(mTitles.get(position));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, DisplayUtil.sp2px(this, 12.0f));
        if (mFirst) {
            textView.setTextColor(Color.rgb(59, 186, 255));
            imageView.setImageResource(mImageOnIds.get(position));
            mFirst = false;
        } else {
            imageView.setImageResource(mImageIds.get(position));
            textView.setTextColor(Color.rgb(149, 152, 167));
        }
        return v;
    }

}
