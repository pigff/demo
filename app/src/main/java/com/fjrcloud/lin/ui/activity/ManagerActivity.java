package com.fjrcloud.lin.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fjrcloud.lin.R;
import com.fjrcloud.lin.model.bean.Manager;
import com.fjrcloud.lin.ui.base.BaseActivity;
import com.fjrcloud.lin.util.GlideCircleTransform;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_manager)
public class ManagerActivity extends BaseActivity {

    @ViewInject(R.id.manager_rv)
    private RecyclerView mRecyclerView;

    private List<Manager> mManagers;
    private ManagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initAdapter();
        initView();
        initListener();
    }

    private void initListener() {

    }

    private void initView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initAdapter() {
        mAdapter = new ManagerAdapter(R.layout.card_manager_item, mManagers);
    }

    private void initData() {
        mManagers = new ArrayList<>();
        mManagers.add(new Manager("小王", "村长", "26", "18060715985", "村口", R.mipmap.image));
        mManagers.add(new Manager("小王", "村长", "26", "18060715985", "村口", R.mipmap.image));
        mManagers.add(new Manager("小王", "村长", "26", "18060715985", "村口", R.mipmap.image));
        mManagers.add(new Manager("小王", "村长", "26", "18060715985", "村口", R.mipmap.image));
        mManagers.add(new Manager("小王", "村长", "26", "18060715985", "村口", R.mipmap.image));
        mManagers.add(new Manager("小王", "村长", "26", "18060715985", "村口", R.mipmap.image));
    }

    class ManagerAdapter extends BaseQuickAdapter<Manager, BaseViewHolder> {

        public ManagerAdapter(int layoutResId, List<Manager> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, Manager manager) {
            baseViewHolder.setText(R.id.card_name, manager.getDuty() + ":  " + manager.getName())
                    .setText(R.id.card_age, manager.getAge())
                    .setText(R.id.card_area, manager.getArea())
                    .setText(R.id.card_phone_num, manager.getNum())
                    .addOnClickListener(R.id.card_phone_call);
            Glide.with(mContext).load(manager.getPic()).transform(new GlideCircleTransform(mContext)).
                    into((ImageView) baseViewHolder.getView(R.id.card_portrait));
        }
    }
}
