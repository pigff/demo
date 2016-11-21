package com.example.lin.demo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lin.demo.ui.fragment.FirstFragment;
import com.example.lin.demo.ui.fragment.FourthFragment;
import com.example.lin.demo.ui.fragment.SecondFragment;
import com.example.lin.demo.ui.fragment.ThirdFragment;

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
                return FirstFragment.newInstance();
            case 1:
                return SecondFragment.newInstance();
            case 2:
                return ThirdFragment.newInstance();
            case 3:
                return FourthFragment.newInstance();
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