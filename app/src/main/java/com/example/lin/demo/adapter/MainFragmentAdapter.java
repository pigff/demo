package com.example.lin.demo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lin.demo.ui.fragment.HomeFragment;
import com.example.lin.demo.ui.fragment.CenterFragment;
import com.example.lin.demo.ui.fragment.BeatyTownFragment;
import com.example.lin.demo.ui.fragment.SafaHomeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2016/6/22.
 */
public class MainFragmentAdapter extends FragmentPagerAdapter {

    private List<String> mTitleList = new ArrayList<>();


    public MainFragmentAdapter(FragmentManager fm, List<String> titleList) {
        super(fm);
        mTitleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return HomeFragment.newInstance();
            case 1:
                return BeatyTownFragment.newInstance();
            case 2:
                return SafaHomeFragment.newInstance();
            case 3:
                return CenterFragment.newInstance();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return mTitleList.size();
    }

}