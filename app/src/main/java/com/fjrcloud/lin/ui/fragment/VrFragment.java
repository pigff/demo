package com.fjrcloud.lin.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.fjrcloud.lin.R;
import com.fjrcloud.lin.adapter.TownListAdapter;
import com.fjrcloud.lin.model.bean.TownBean;
import com.fjrcloud.lin.model.domain.YsTown;
import com.fjrcloud.lin.ui.activity.VrActivity;
import com.fjrcloud.lin.util.Constant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


public class VrFragment extends Fragment {

    private ListView mListView;
    private List<TownBean.Town> mTowns;
    private TownListAdapter mAdapter;

    public VrFragment() {
        // Required empty public constructor
    }

    public static VrFragment newInstance() {
        VrFragment fragment = new VrFragment();
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
        View view = inflater.inflate(R.layout.fragment_vr, container, false);
        mListView = (ListView) view.findViewById(R.id.vr_town_lv);
        initData();
        initAdapter();
        initView();
        initListener();
        getData();
        return view;
    }

    private void getData() {
        getTown(new YsTown().new FindTownByParent(null));
    }

    private void initListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), VrActivity.class);
                intent.putExtra(Constant.BEAN, mTowns.get(position));
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mListView.setAdapter(mAdapter);
    }

    private void initAdapter() {
        mAdapter = new TownListAdapter(mTowns, getActivity());
    }

    private void initData() {
        mTowns = new ArrayList<>();
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


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
