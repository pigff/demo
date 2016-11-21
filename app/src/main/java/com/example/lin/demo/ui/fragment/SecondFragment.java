package com.example.lin.demo.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.lin.demo.R;
import com.sevenheaven.segmentcontrol.SegmentControl;


public class SecondFragment extends Fragment {

    private SegmentControl mSegmentControl;
    private FrameLayout mLayout;

    public SecondFragment() {
        // Required empty public constructor
    }

    public static SecondFragment newInstance() {
        SecondFragment fragment = new SecondFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        mSegmentControl = (SegmentControl) view.findViewById(R.id.second_segment_control);
        initView();
        initListener();
        return view;
    }

    private void initView() {
        FragmentManager manager = getChildFragmentManager();
        manager.beginTransaction().replace(R.id.second_fragment_group, JoyMapFragment.newInstance()).commit();
    }

    private void initListener() {
        mSegmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                FragmentManager manager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                switch (index) {
                    case 0:
                        fragmentTransaction.replace(R.id.second_fragment_group, JoyMapFragment.newInstance());
                        break;
                    case 1:
                        fragmentTransaction.replace(R.id.second_fragment_group, VrFragment.newInstance());
                        break;
                }
                fragmentTransaction.commit();
            }
        });
    }


}
