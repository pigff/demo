package com.fjrcloud.lin.ui.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.lin.App;
import com.fjrcloud.lin.R;
import com.fjrcloud.lin.model.bean.CameraBean;
import com.fjrcloud.lin.model.bean.TokenBean;
import com.fjrcloud.lin.model.bean.TownBean;
import com.fjrcloud.lin.model.domain.YsCamera;
import com.fjrcloud.lin.model.domain.YsTown;
import com.fjrcloud.lin.ui.base.BaseActivity;
import com.fjrcloud.lin.util.Constant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_town_grid)
public class TownGridActivity extends BaseActivity {

    @ViewInject(R.id.town_grid)
    private RecyclerView mRecyclerView;
    @ViewInject(R.id.loading_group)
    private LinearLayout mLoadingGroup;
    @ViewInject(R.id.loading_img)
    private ImageView mImageView;
    @ViewInject(R.id.town_tip_tv)
    private TextView mTipTv;
    private List<CameraBean.Camera> mVideoAttr;
    private GridAdapter mGridAdapter;
    private TownBean.Town mTown;
    private String mAccessToken;
    private AnimationDrawable mDrawalbe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        getData();
        initAdapter();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDrawalbe = (AnimationDrawable) mImageView.getDrawable();
        mDrawalbe.start();
    }

    private void initView() {
        setTitle(mTown.getName());
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mGridAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent2Video = new Intent(TownGridActivity.this, VideoActivity.class);
                intent2Video.putExtra(Constant.BEAN, mVideoAttr.get(i));
                startActivity(intent2Video);
            }
        });

    }

    private void initAdapter() {
        mGridAdapter = new GridAdapter(R.layout.img_item, mVideoAttr);
    }

    private void initData() {
        mTown = (TownBean.Town) getIntent().getSerializableExtra(Constant.BEAN);
        mVideoAttr = new ArrayList<>();
    }

    private void getToken(RequestParams params) {
        x.http().post(params, new Callback.CommonCallback<TokenBean>() {
            @Override
            public void onSuccess(TokenBean result) {
                mAccessToken = result.getData().getAccessToken();
                App.getOpenSDK().setAccessToken(mAccessToken);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(TownGridActivity.this, R.string.unknow_error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
//                getCamera(new YsTown().new FindCameraByArea(mTown.getId()));
                getCamera(new YsTown().new FindCameraByArea(mTown.getId()));
            }
        });
    }

    private void getCamera(RequestParams params) {
        x.http().post(params, new Callback.CommonCallback<CameraBean>() {
            @Override
            public void onSuccess(CameraBean result) {
                if (result.getData().size() > 0) {
                    for (int i = 0; i < result.getData().size(); i++) {
                        mVideoAttr.add(result.getData().get(i));
                    }
                    mGridAdapter.notifyDataSetChanged();
                } else {
                    mTipTv.setVisibility(View.VISIBLE);
                }
                loadviewGone();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(TownGridActivity.this, R.string.unknow_error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void loadviewGone() {
        if (mDrawalbe.isRunning()) {
            mDrawalbe.stop();
        }
        mLoadingGroup.setVisibility(View.GONE);
    }

    public void getData() {
//        if (App.getOpenSDK().getEZAccessToken() == null) {
//            getToken(new YsCamera().new GetYsToken());
//        } else {
//            getCamera(new YsCamera().new GetCamera());
//        }
        getToken(new YsCamera().new GetYsToken());
    }

    class GridAdapter extends BaseQuickAdapter<CameraBean.Camera, BaseViewHolder> {

        public GridAdapter(int layoutResId, List<CameraBean.Camera> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, CameraBean.Camera camera) {
            baseViewHolder.setText(R.id.grid_image_title, camera.getName())
                    .addOnClickListener(R.id.img_group);
            Glide.with(mContext).load(camera.getImg()).error(R.mipmap.no_img).
                    into((ImageView) baseViewHolder.getView(R.id.grid_image));
            if (!TextUtils.equals(camera.getStatus(), "在线")) {
                baseViewHolder.setVisible(R.id.camer_online_tip, true);
                baseViewHolder.getView(R.id.img_group).setEnabled(false);
            }
        }

    }
}
