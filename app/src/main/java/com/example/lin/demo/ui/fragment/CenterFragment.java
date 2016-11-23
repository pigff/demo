package com.example.lin.demo.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.lin.demo.R;
import com.example.lin.demo.adapter.CenterGridAdapter;
import com.example.lin.demo.bean.Category;

import java.util.ArrayList;
import java.util.List;

public class CenterFragment extends Fragment {

    private GridView mGridView;
    private List<Category> mCategories;
    private CenterGridAdapter mAdapter;

    public CenterFragment() {

    }

    public static CenterFragment newInstance() {
        CenterFragment fragment = new CenterFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_center, container, false);
        mGridView = (GridView) view.findViewById(R.id.center_grid);
        initData();
        initAdapter();
        initView();
        return view;
    }

    private void initView() {
        mGridView.setAdapter(mAdapter);
    }

    private void initAdapter() {
        mAdapter = new CenterGridAdapter(getActivity(), mCategories);
    }

    private void initData() {
        mCategories = new ArrayList<>();
        Category category1 = new Category("头像设置", R.mipmap.icon_portrait);
        Category category2 = new Category("修改密码", R.mipmap.icon_psw);
        Category category3 = new Category("运动数据", R.mipmap.icon_sport);
        Category category4 = new Category("健康记录", R.mipmap.icon_health_record);
        Category category5 = new Category("健康检测", R.mipmap.icon_health_check);
        Category category6 = new Category("保险查询", R.mipmap.icon_check_insur);
        mCategories.add(category1);
        mCategories.add(category2);
        mCategories.add(category3);
        mCategories.add(category4);
        mCategories.add(category5);
        mCategories.add(category6);
    }

}
