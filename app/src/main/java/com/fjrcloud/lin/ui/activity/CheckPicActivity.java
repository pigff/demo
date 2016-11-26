package com.fjrcloud.lin.ui.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fjrcloud.lin.R;
import com.fjrcloud.lin.ui.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2016/7/6.
 */
@ContentView(R.layout.activity_select_pic)
public class CheckPicActivity extends BaseActivity {

    public static final String INTENT_SELECTED_PICTURE = "pic_url";
    @ViewInject(R.id.user_image_gridview)
    private GridView mPicGridView;

    private Context mContext;

    private ContentResolver mContentResolver;

    private PictureAdapter mPictureAdapter;

    private ImageFloder mImageAll, mCurrentImageFolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }


    private void init() {
        setTitle("抓拍图片");
//        mDirPaths = new ArrayList<>();
        mImageAll = new ImageFloder();
        mCurrentImageFolder = mImageAll;
//        mDirPaths.add(mImageAll);
        mPictureAdapter = new PictureAdapter();
        mPicGridView.setAdapter(mPictureAdapter);
        mPicGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent2Choice = new Intent(CheckPicActivity.this, ShowImageActivity.class);
                intent2Choice.putExtra(INTENT_SELECTED_PICTURE, String.valueOf(mCurrentImageFolder.mImages.get(position).path));
                startActivity(intent2Choice);
            }
        });
        mContext = this;
        mContentResolver = getContentResolver();
//        getThumbnail();
        getImagePathFromSD();
//        mPictureAdapter.notifyDataSetChanged();
    }

    class PictureAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mCurrentImageFolder.mImages.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.post_grid_item_picture, null);
                holder = new ViewHolder();
                holder.mImageView = (ImageView) convertView.findViewById(R.id.post_grid_item_iv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            ImageItem item = mCurrentImageFolder.mImages.get(position);
            Glide.with(CheckPicActivity.this).load("file://" + item.path).centerCrop().override(200, 200).
                    error(R.mipmap.error_image).into(holder.mImageView);

            return convertView;
        }

        class ViewHolder {

            ImageView mImageView;

        }
    }

    private void getImagePathFromSD() {
        // 图片列表
        // 得到sd卡内image文件夹的路径   File.separator(/)
        String filePath = Environment.getExternalStorageDirectory().toString() + "/Town/CapturePicture" + File.separator;
        // 得到该路径文件夹下所有的文件
        File fileAll = new File(filePath);
        File[] files = fileAll.listFiles();
        if (files != null) {
            // 将所有的文件存入ArrayList中,并过滤所有图片格式的文件
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (checkIsImageFile(file.getPath())) {
                    mImageAll.mImages.add(new ImageItem(file.getPath()));
                }
            }
        }
    }

    private boolean checkIsImageFile(String fName) {
        boolean isImageFile = false;
        // 获取扩展名
        String FileEnd = fName.substring(fName.lastIndexOf(".") + 1,
                fName.length()).toLowerCase();
        if (FileEnd.equals("jpg") || FileEnd.equals("png") || FileEnd.equals("gif")
                || FileEnd.equals("jpeg") || FileEnd.equals("bmp")) {
            isImageFile = true;
        } else {
            isImageFile = false;
        }
        return isImageFile;
    }

    class ImageFloder {

        /**
         * 图片的文件夹路径
         */
        private String mDir;

        /**
         * 第一张图片的路径
         */
        private String mFirstImagePath;

        /**
         * 文件夹的名称
         */
        private String mName;

        public List<ImageItem> mImages = new ArrayList<>();


        public void setDir(String dir) {
            mDir = dir;
            int lastIndexOf = mDir.lastIndexOf("/");
            mName = dir.substring(lastIndexOf);
        }

        public String getName() {
            return mName;
        }

    }

    class ImageItem {

        String path;

        public ImageItem(String path) {
            this.path = path;
        }
    }
}
