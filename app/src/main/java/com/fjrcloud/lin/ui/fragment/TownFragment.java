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
import com.fjrcloud.lin.model.bean.TownBean;
import com.fjrcloud.lin.model.domain.YsTown;
import com.fjrcloud.lin.ui.activity.TownGridActivity;
import com.fjrcloud.lin.util.Constant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TownFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TownFragment extends Fragment {

    private List<TownBean.Town> mTowns;
    private TownListAdapter mAdapter;
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
        getData();
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
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), TownGridActivity.class);
                intent.putExtra(Constant.BEAN, mTowns.get(position));
                startActivity(intent);
            }
        });
    }

    private void initAdapter() {
        mAdapter = new TownListAdapter(mTowns, getActivity());
    }

    private void initData() {
        mTowns = new ArrayList<>();
    }


    public void getData() {
        getTown(new YsTown().new FindTownByParent(null));
    }

    private void getTown(RequestParams params) {
        x.http().post(params, new Callback.CommonCallback<TownBean>() {
            @Override
            public void onSuccess(TownBean result) {
                for (int i = 0; i < result.getData().size(); i++) {
                    mTowns.add(result.getData().get(i));
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(), R.string.unknow_error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
