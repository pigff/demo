package com.fjrcloud.lin.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fjrcloud.lin.R;
import com.fjrcloud.lin.ui.base.BaseFragment;
import com.sevenheaven.segmentcontrol.SegmentControl;


public class SafaHomeFragment extends BaseFragment {

    private SegmentControl mSegmentControl;

    public SafaHomeFragment() {
        // Required empty public constructor
    }

    public static SafaHomeFragment newInstance() {
        SafaHomeFragment fragment = new SafaHomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_safe_home, container, false);
        mSegmentControl = (SegmentControl) view.findViewById(R.id.sf_home_segment_control);
        mIsFirstLoad = false;
        mIsPrepared = true;
        lazyLoad();
        return view;
    }

    private void initView() {
        mIsFirstLoad = true;
        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.sf_home_fragment_group, TownMapFragment.newInstance()).commit();
    }

    private void initListener() {
        mSegmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (index) {
                    case 0:
                        fragmentTransaction.replace(R.id.sf_home_fragment_group, TownMapFragment.newInstance());
                        break;
                    case 1:
                        fragmentTransaction.replace(R.id.sf_home_fragment_group, TownFragment.newInstance());
                        break;
                }
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void lazyLoad() {
        if (mIsFirstLoad || !mIsVisible || !mIsPrepared) {
            return;
        }
        initView();
        initListener();
    }
}
