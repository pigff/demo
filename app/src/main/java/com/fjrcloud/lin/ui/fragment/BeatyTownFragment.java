package com.fjrcloud.lin.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.fjrcloud.lin.R;
import com.sevenheaven.segmentcontrol.SegmentControl;


public class BeatyTownFragment extends Fragment {

    private SegmentControl mSegmentControl;
    private FrameLayout mLayout;


    public BeatyTownFragment() {
        // Required empty public constructor
    }

    public static BeatyTownFragment newInstance() {
        BeatyTownFragment fragment = new BeatyTownFragment();
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
        View view = inflater.inflate(R.layout.fragment_beauty_town, container, false);
        mSegmentControl = (SegmentControl) view.findViewById(R.id.by_town_segment_control);

        initView();
        initListener();
        return view;
    }

    private void initView() {
        FragmentManager manager = getChildFragmentManager();
        manager.beginTransaction().replace(R.id.by_town_fragment_group, JoyMapFragment.newInstance()).commit();
    }

    private void initListener() {
        mSegmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                FragmentManager manager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                switch (index) {
                    case 0:
                        fragmentTransaction.replace(R.id.by_town_fragment_group, JoyMapFragment.newInstance());
                        break;
                    case 1:
                        fragmentTransaction.replace(R.id.by_town_fragment_group, VrFragment.newInstance());
                        break;
                }
                fragmentTransaction.commit();
            }
        });
    }


}
