package com.fjrcloud.lin.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fjrcloud.lin.R;
import com.fjrcloud.lin.adapter.TownListAdapter;
import com.fjrcloud.lin.bean.Town;
import com.fjrcloud.lin.ui.activity.TownGridActivity;
import com.fjrcloud.lin.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TownFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TownFragment extends Fragment {

    private List<Town> mTowns;
    private TownListAdapter mAdapter3;
    private ListView mListView;
    private EditText mEditText;
    private ImageButton mSearchBtn;

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
        mEditText = (EditText) view.findViewById(R.id.search_edit);
        mSearchBtn = (ImageButton) view.findViewById(R.id.search_btn);
        initData();
        initAdapter();
        initView();
        initListener();
        return view;
    }

    private void initListener() {
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        InputMethodManager imm = (InputMethodManager) v
                                .getContext().getSystemService(
                                        Context.INPUT_METHOD_SERVICE);
                        if (imm.isActive()) {
                            imm.hideSoftInputFromWindow(
                                    v.getApplicationWindowToken(), 0);
                        }
                        Toast.makeText(getActivity(), "你点了search", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });
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
        mAdapter3 = new TownListAdapter(mTowns, getActivity());
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
