package com.example.lin.demo.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lin.demo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TownFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TownFragment extends Fragment {

    public TownFragment() {
        // Required empty public constructor
    }


    public static TownFragment newInstance() {
        TownFragment fragment = new TownFragment();
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
        return inflater.inflate(R.layout.fragment_town, container, false);
    }

}
