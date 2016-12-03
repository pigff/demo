package com.fjrcloud.lin.ui.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fjrcloud.lin.R;
import com.fjrcloud.lin.adapter.MainFragmentAdapter;
import com.fjrcloud.lin.model.bean.VersionBean;
import com.fjrcloud.lin.model.domain.Update;
import com.fjrcloud.lin.ui.base.BaseActivity;
import com.fjrcloud.lin.util.Constant;
import com.fjrcloud.lin.util.DisplayUtil;
import com.fjrcloud.lin.util.FileUtil;
import com.fjrcloud.lin.util.IntentUtil;
import com.fjrcloud.lin.util.custom_view.NoScrollPager;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewInject(R.id.main_tabs)
    private TabLayout mTabLayout;

    @ViewInject(R.id.main_viewpager)
    private NoScrollPager mViewPager;

    @ViewInject(R.id.titlebar_backbutton)
    private LinearLayout mBackBtn;

    @ViewInject(R.id.titlebar_group)
    private RelativeLayout mRelativeLayout;

    private boolean mFirst = true;

    List<String> mTitles;
    List<Integer> mImageIds;
    List<Integer> mImageOnIds;
    private Callback.Cancelable mCancelable;
    private VersionBean.Version mVersion;
    private ProgressDialog mDownloadDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initViewPager();
        for (int i = 0; i < mTitles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(getTabView(i)));
        }

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                View v = tab.getCustomView();
                ImageView imageView = (ImageView) v.findViewById(R.id.iv_tab);
                imageView.setImageResource(mImageOnIds.get(position));
                TextView textView = (TextView) v.findViewById(R.id.iv_text);
                textView.setTextColor(Color.rgb(59, 186, 255));
                mViewPager.setCurrentItem(tab.getPosition(), false);
                if (position == 1 || position == 2) {
                    mRelativeLayout.setVisibility(View.GONE);
                } else {
                    mRelativeLayout.setVisibility(View.VISIBLE);
                    setTitle(mTitles.get(position));
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View v = tab.getCustomView();
                ImageView cImageView = (ImageView) v.findViewById(R.id.iv_tab);
                cImageView.setImageResource(mImageIds.get(tab.getPosition()));
                TextView cTextView = (TextView) v.findViewById(R.id.iv_text);
                cTextView.setTextColor(Color.rgb(149, 152, 167));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        checkVision(new Update().new GetVersion());
    }


    private void initViewPager() {
        MainFragmentAdapter mainFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager(), mTitles);
        mViewPager.setAdapter(mainFragmentAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPager.setOffscreenPageLimit(3);
    }

    private void init() {
//        getActionToolBar().setNavigationIcon(null);
        mBackBtn.setVisibility(View.GONE);
        mTitles = new ArrayList<>();
        mTitles.add("首页");
        mTitles.add("美丽乡村");
        mTitles.add("平安家园");
        mTitles.add("个人中心");

        mImageIds = new ArrayList<>();
        mImageIds.add(R.mipmap.icon_home_off);
        mImageIds.add(R.mipmap.icon_by_town_off);
        mImageIds.add(R.mipmap.icon_safe_home_off);
        mImageIds.add(R.mipmap.icon_center_off);

        mImageOnIds = new ArrayList<>();
        mImageOnIds.add(R.mipmap.icon_home_on);
        mImageOnIds.add(R.mipmap.icon_by_town_on);
        mImageOnIds.add(R.mipmap.icon_safe_home_on);
        mImageOnIds.add(R.mipmap.icon_center_on);

        setTitle(mTitles.get(0));
    }

    public View getTabView(int position) {
        View v = LayoutInflater.from(this).inflate(R.layout.design_tab_layout, null);
        ImageView imageView = (ImageView) v.findViewById(R.id.iv_tab);
        TextView textView = (TextView) v.findViewById(R.id.iv_text);
        textView.setText(mTitles.get(position));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, DisplayUtil.sp2px(this, 12.0f));
        if (mFirst) {
            textView.setTextColor(Color.rgb(59, 186, 255));
            imageView.setImageResource(mImageOnIds.get(position));
            mFirst = false;
        } else {
            imageView.setImageResource(mImageIds.get(position));
            textView.setTextColor(Color.rgb(149, 152, 167));
        }
        return v;
    }

    private void checkVision(RequestParams params) {
        x.http().post(params, new Callback.CommonCallback<VersionBean>() {
            @Override
            public void onSuccess(VersionBean result) {
                mVersion = result.getData();
                if (FileUtil.getVersionCode(MainActivity.this) != -1 &&
                        FileUtil.getVersionCode(MainActivity.this) < mVersion.getCode()) {
                    downloadApk();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    /**
     * 弹出下载请求
     */
    private void downloadApk() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT);
//        builder.setTitle("最新版本："+updateBean.getCode());
//        builder.setMessage(updateBean.getDes());
//        builder.setMessage("发现新版本，请更新！\n版本号：" + mDataEntity.getCode());
        builder.setMessage("发现新版本，请更新！");
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                PermissionGen.with(MainActivity.this)
                        .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .addRequestCode(Constant.PERMISSION_CODE)
                        .request();
            }
        });

        builder.setNegativeButton("以后再说", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // 设置取消的监听, 用户点击返回键时会触发
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = Constant.PERMISSION_CODE)
    private void permissionSuccess() {
        String url = Constant.SERVICE_HOST + mVersion.getFilePath();
        RequestParams params = new RequestParams(url);
        params.setSaveFilePath(FileUtil.getSaveFilePath(MainActivity.this) + url.substring(url.lastIndexOf("/")));
        params.setAutoRename(true);
        downloadFile(params);
    }

    @PermissionFail(requestCode = Constant.PERMISSION_CODE)
    private void permissionFailure() {
        Toast.makeText(this, R.string.permission_failure, Toast.LENGTH_SHORT).show();
    }

    /**
     * 下载文件
     *
     */
    private void downloadFile(RequestParams params) {
        mCancelable = x.http().get(params,
                new Callback.ProgressCallback<File>() {
                    @Override
                    public void onWaiting() {

                    }

                    @Override
                    public void onStarted() {
                        mDownloadDialog = new ProgressDialog(MainActivity.this);
                        // downloadDialog.setIcon(R.drawable.ic_launcher);
                        mDownloadDialog.setTitle("下载中请等待");
                        mDownloadDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        mDownloadDialog.setCanceledOnTouchOutside(false);
                        mDownloadDialog.setButton2("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                mCancelable.cancel();
                            }
                        });
                        mDownloadDialog.show();
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isDownloading) {
                        mDownloadDialog.setMax((int) total);
                        mDownloadDialog.setProgress((int) current);
                        mDownloadDialog.setProgressNumberFormat(current / (1024 * 1024) + "M/" + total / (1024 * 1024) + "M");
                    }

                    @Override
                    public void onSuccess(File result) {
                        Intent intent = IntentUtil.getStallIntent(result);
                        startActivityForResult(intent, 0);// 如果用户取消安装的话, 会返回结果,回调方法onActivityResult
                        mDownloadDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Toast.makeText(MainActivity.this, R.string.download_error, Toast.LENGTH_SHORT).show();
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
