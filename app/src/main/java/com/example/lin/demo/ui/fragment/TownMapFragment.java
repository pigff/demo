package com.example.lin.demo.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lin.demo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TownMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TownMapFragment extends Fragment {

    public TownMapFragment() {
        // Required empty public constructor
    }


    public static TownMapFragment newInstance() {
        TownMapFragment fragment = new TownMapFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_town_map, container, false);
        return view;
    }

}