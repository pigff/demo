package com.fjrcloud.lin.ui.fragment;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fjrcloud.lin.R;
import com.fjrcloud.lin.adapter.CenterGridAdapter;
import com.fjrcloud.lin.model.bean.CategoryBean;
import com.fjrcloud.lin.ui.activity.ModifyPwdActivity;
import com.fjrcloud.lin.ui.activity.SelectPicActivity;
import com.fjrcloud.lin.util.Constant;
import com.fjrcloud.lin.util.FileUtil;
import com.fjrcloud.lin.util.GlideCircleTransform;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class CenterFragment extends Fragment {

    public static final int PIC_PHOTO = 01;
    public static final int PHOTO_REQUEST_CUT = 02;
    public static final int LOGIN_CODE = 100;

    private GridView mGridView;
    private List<CategoryBean> mCategories;
    private CenterGridAdapter mAdapter;
    private ImageView mImageView;
    private TextView mNoticeLogin;

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
        mImageView = (ImageView) view.findViewById(R.id.ec_head_image);
        mNoticeLogin = (TextView) view.findViewById(R.id.notice_login);
        initData();
        initAdapter();
        initView();
        initListener();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PIC_PHOTO) {
            if (data != null) {
                File file = new File(data.getStringExtra(SelectPicActivity.INTENT_SELECTED_PICTURE));
                Uri uri = Uri.fromFile(file);
                crop(uri);
            }
        } else if (requestCode == PHOTO_REQUEST_CUT && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                String path = FileUtil.getPath(bitmap, getActivity());
                if (path != null) {
//                    modifyPortrait(path);
                } else {
                    Toast.makeText(getActivity(), R.string.portrait_error, Toast.LENGTH_SHORT).show();
                }
            }
        } else if (requestCode == LOGIN_CODE && resultCode == Activity.RESULT_OK) {
//            mLogin.setVisibility(View.GONE);
//            mCompanyName.setVisibility(View.VISIBLE);
//            mCompanyName.setText(App.getInstance().getNickName());
//            mIntro.setVisibility(View.GONE);
//            mLoginOutBtn.setVisibility(View.VISIBLE);
        }
    }

    private void initView() {
        mGridView.setAdapter(mAdapter);
        Glide.with(this).load(R.mipmap.center_user_header_def).transform(new GlideCircleTransform(getActivity())).into(mImageView);
    }

    private void initAdapter() {
        mAdapter = new CenterGridAdapter(getActivity(), mCategories);
    }

    private void initData() {
        mCategories = new ArrayList<>();
//        CategoryBean category1 = new CategoryBean("头像设置", R.mipmap.icon_portrait);
//        CategoryBean category2 = new CategoryBean("修改密码", R.mipmap.icon_psw);
//        CategoryBean category3 = new CategoryBean("运动数据", R.mipmap.icon_sport);
//        CategoryBean category4 = new CategoryBean("健康记录", R.mipmap.icon_health_record);
//        CategoryBean category5 = new CategoryBean("健康检测", R.mipmap.icon_health_check);
//        CategoryBean category6 = new CategoryBean("保险查询", R.mipmap.icon_check_insur);
//        mCategories.add(category1);
//        mCategories.add(category2);
//        mCategories.add(category3);
//        mCategories.add(category4);
//        mCategories.add(category5);
//        mCategories.add(category6);
    }

    private void initListener() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        PermissionGen.with(CenterFragment.this)
                                .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                .addRequestCode(Constant.PERMISSION_CODE)
                                .request();
                        break;
                    case 1:
                        Intent intent2Modify = new Intent(getActivity(), ModifyPwdActivity.class);
                        startActivity(intent2Modify);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 裁剪图片
     */
    private void crop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");

        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

   @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = Constant.PERMISSION_CODE)
    private void permissionSuccess() {
        Intent intent2SelectPic = new Intent(getActivity(), SelectPicActivity.class);
        startActivityForResult(intent2SelectPic, PIC_PHOTO);
    }

    @PermissionFail(requestCode = Constant.PERMISSION_CODE)
    private void permissionFailure() {
        Toast.makeText(getActivity(), R.string.permission_failure, Toast.LENGTH_SHORT).show();
    }

}
