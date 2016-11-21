package com.example.lin.demo.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lin.demo.R;
import com.sevenheaven.segmentcontrol.SegmentControl;


public class ThirdFragment extends Fragment {

    private SegmentControl mSegmentControl;

    public ThirdFragment() {
        // Required empty public constructor
    }

    public static ThirdFragment newInstance() {
        ThirdFragment fragment = new ThirdFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        mSegmentControl = (SegmentControl) view.findViewById(R.id.third_segment_control);
        initView();
        initListener();
        return view;
    }

    private void initView() {
        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.third_fragment_group, TownMapFragment.newInstance()).commit();
    }

    private void initListener() {
        mSegmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (index) {
                    case 0:
                        fragmentTransaction.replace(R.id.third_fragment_group, TownMapFragment.newInstance());
                        break;
                    case 1:
                        fragmentTransaction.replace(R.id.third_fragment_group, TownFragment.newInstance());
                        break;
                }
                fragmentTransaction.commit();
            }
        });
    }

}
