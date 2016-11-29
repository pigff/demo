
package com.fjrcloud.lin.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fjrcloud.lin.R;
import com.fjrcloud.lin.ui.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_upload_pic)
public class UploadPicActivity extends BaseActivity {

    @ViewInject(R.id.titlebar_right_btn)
    private Button mUploadBtn;
    @ViewInject(R.id.upload_grid)
    private GridView mGridView;
    // 最多选择图片的个数
    private static int MAX_NUM = 3;

    private Context context;
    private PictureAdapter adapter;

    /**
     * 临时的辅助类，用于防止同一个文件夹的多次扫描
     */
    private ArrayList<ImageFloder> mDirPaths = new ArrayList<>();


    private ImageFloder imageAll, currentImageFolder;

    //已选择的图片
    private ArrayList<String> selectedPicture = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        initView();
    }

    @Event(R.id.titlebar_right_btn)
    private void onClick(View view) {
        Toast.makeText(context, R.string.upload_success, Toast.LENGTH_SHORT).show();
        finish();
    }

    /**
     * 视图初始化
     */
    private void initView() {
        setTitle("上传图片");
        mUploadBtn.setVisibility(View.VISIBLE);
        imageAll = new ImageFloder();
        currentImageFolder = imageAll;
        mDirPaths.add(imageAll);

        adapter = new PictureAdapter();
        mGridView.setAdapter(adapter);

        getImagePathFromSD();
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
            for (int i = files.length - 1; i >= 0; i--) {
                File file = files[i];
                if (checkIsImageFile(file.getPath())) {
                    imageAll.images.add(new ImageItem(file.getPath()));
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

    class PictureAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return currentImageFolder.images.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;

            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.grid_upload_pic, parent, false);
                holder = new ViewHolder();
                holder.iv = (ImageView) convertView.findViewById(R.id.iv);
                holder.checkBox = (Button) convertView.findViewById(R.id.check);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.checkBox.setVisibility(View.VISIBLE);

            final ImageItem item = currentImageFolder.images.get(position);
            Glide.with(context).load("file://" + item.path).error(R.mipmap.error_image).into(holder.iv);

            //是否选中
            boolean isSelected = (selectedPicture.contains(item.path));
            holder.checkBox.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    //二次选择，移除
                    if (selectedPicture.contains(item.path)) {
                        selectedPicture.remove(item.path);
                    } else {

                        //加入数组
                        selectedPicture.add(item.path);
                    }
                    mUploadBtn.setEnabled(selectedPicture.size() > 0);

                    v.setSelected(selectedPicture.contains(item.path));
                }
            });
            holder.checkBox.setSelected(isSelected);


            return convertView;
        }
    }

    //自定义的一个类用来缓存convertview
    class ViewHolder {
        ImageView iv;
        Button checkBox;
    }



    class ImageFloder {
        /**
         * 图片的文件夹路径
         */
        private String dir;

        /**
         * 第一张图片的路径
         */
        private String firstImagePath;
        /**
         * 文件夹的名称
         */
        private String name;

        public List<ImageItem> images = new ArrayList<ImageItem>();

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
            int lastIndexOf = this.dir.lastIndexOf("/");
            this.name = this.dir.substring(lastIndexOf);
        }

        public String getFirstImagePath() {
            return firstImagePath;
        }

        public void setFirstImagePath(String firstImagePath) {
            this.firstImagePath = firstImagePath;
        }

        public String getName() {
            return name;
        }

    }

    class ImageItem {
        String path;

        public ImageItem(String p) {
            this.path = p;
        }
    }

}
