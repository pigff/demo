package com.example.lin.demo.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lin.demo.R;
import com.example.lin.demo.adapter.ListAdapter3;
import com.example.lin.demo.bean.Town;
import com.example.lin.demo.ui.activity.TownGridActivity;
import com.example.lin.demo.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TownFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TownFragment extends Fragment {

    private List<Town> mTowns;
    private ListAdapter3 mAdapter3;
    private ListView mListView;

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
        View view = inflater.inflate(R.layout.fragment_town, container, false);
        mListView = (ListView) view.findViewById(R.id.town_lv);
        initData();
        initAdapter();
        initView();
        return view;
    }

    private void initView() {
        mListView.setAdapter(mAdapter3);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), TownGridActivity.class);
                intent.putExtra(Constant.BEAN, mTowns.get(position).getName());
                startActivity(intent);
            }
        });
    }

    private void initAdapter() {
        mAdapter3 = new ListAdapter3(mTowns, getActivity());
    }

    private void initData() {
        mTowns = new ArrayList<>();
        Town town1 = new Town("北垞村");
        Town town2 = new Town("薛港村");
        Town town3 = new Town("后安村");
        Town town4 = new Town("东进村");
        Town town5 = new Town("洋门村");
        mTowns.add(town1);
        mTowns.add(town2);
        mTowns.add(town3);
        mTowns.add(town4);
        mTowns.add(town5);
    }

}
