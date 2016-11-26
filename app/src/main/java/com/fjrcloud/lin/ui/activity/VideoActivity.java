package com.fjrcloud.lin.ui.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fjrcloud.lin.App;
import com.fjrcloud.lin.R;
import com.fjrcloud.lin.model.bean.CameraBean;
import com.fjrcloud.lin.model.bean.Video;
import com.fjrcloud.lin.ui.base.BaseActivity;
import com.fjrcloud.lin.util.Constant;
import com.fjrcloud.lin.util.DataManager;
import com.fjrcloud.lin.util.EZUtils;
import com.fjrcloud.lin.util.IntentUtil;
import com.fjrcloud.lin.util.custom_view.DialDialog;
import com.videogo.exception.InnerException;
import com.videogo.openapi.EZPlayer;
import com.videogo.util.MediaScanner;
import com.videogo.util.SDCardUtil;
import com.videogo.util.Utils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

@ContentView(R.layout.activity_video)
public class VideoActivity extends BaseActivity implements SurfaceHolder.Callback, Handler.Callback {

    //    @ViewInject(R.id.video_img)
//    private ImageView mImageView;
    @ViewInject(R.id.video_tip_tv)
    private TextView mTextView;
    @ViewInject(R.id.video_load)
    private ImageView mImageView;
    private Video mVideo;
//    private EZDeviceInfo mDeviceInfo;
//    private EZCameraInfo mCameraInfo;
    private EZPlayer mEzPlayer;
    private SurfaceHolder mRealPlaySh = null;
    private Handler mHandler = null;
    private SurfaceView mRealPlaySv = null;
    private boolean mIsOnline; //设备是否在线

    private boolean mCapBtnClick;
    private boolean mUploadBtnClick;
    private boolean mCallClick;


    private static final String TAG = "VideoActivity";
    private CameraBean.Camera mCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

//    @Event(value = {R.id.capture, R.id.upload, R.id.policy})
//    private void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.capture:
//                PermissionGen.with(VideoActivity.this)
//                        .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                        .addRequestCode(Constant.PERMISSION_CODE)
//                        .request();
//                break;
//            case R.id.upload:
//                PermissionGen.with(VideoActivity.this)
//                        .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                        .addRequestCode(Constant.PERMISSION_CODE2)
//                        .request();
//                break;
//            case R.id.policy:
//                PermissionGen.with(VideoActivity.this)
//                        .permissions(Manifest.permission.CALL_PHONE)
//                        .addRequestCode(Constant.PERMISSION_CODE3)
//                        .request();
//                break;
//            default:
//                break;
//        }
//    }

    @Event(value = {R.id.capture, R.id.upload, R.id.call_policy, R.id.album
                ,R.id.capture_back, R.id.album_back, R.id.upload_back})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.capture:
                Toast.makeText(this, "抓拍", Toast.LENGTH_SHORT).show();
                break;
            case R.id.capture_back:
                Toast.makeText(this, "抓拍背景", Toast.LENGTH_SHORT).show();
                break;
            case R.id.album:
                Toast.makeText(this, "相册", Toast.LENGTH_SHORT).show();
                break;
            case R.id.album_back:
                Toast.makeText(this, "相册背景", Toast.LENGTH_SHORT).show();
                break;
            case R.id.upload:
                Toast.makeText(this, "上传", Toast.LENGTH_SHORT).show();
                break;
            case R.id.upload_back:
                Toast.makeText(this, "上传背景", Toast.LENGTH_SHORT).show();
                break;
            case R.id.call_policy:
                PermissionGen.with(VideoActivity.this)
                        .permissions(Manifest.permission.CALL_PHONE)
                        .addRequestCode(Constant.PERMISSION_CODE3)
                        .request();
                break;
            default:
                break;
        }
    }

    private void screenShots() {
        if (!SDCardUtil.isSDCardUseable()) {
            // 提示SD卡不可用
            Utils.showToast(VideoActivity.this, getString(R.string.sd_error));
            return;
        }

        if (SDCardUtil.getSDCardRemainSize() < SDCardUtil.PIC_MIN_MEM_SPACE) {
            // 提示内存不足
            Utils.showToast(VideoActivity.this, getString(R.string.sd_error2));
            return;
        }
        Thread thr = new Thread() {
            @Override
            public void run() {
                Bitmap bmp = mEzPlayer.capturePicture();
                if (bmp != null) {
                    try {

                        // 可以采用deviceSerial+时间作为文件命名，demo中简化，只用时间命名
                        java.util.Date date = new java.util.Date();
                        final String path = Environment.getExternalStorageDirectory().getPath() + "/Town/CapturePicture/" + String.format("%tY", date)
                                + String.format("%tH", date) + String.format("%tM", date) + String.format("%tS", date) + String.format("%tL", date) + ".jpg";

                        if (TextUtils.isEmpty(path)) {
                            bmp.recycle();
                            bmp = null;
                            return;
                        }
                        EZUtils.saveCapturePictrue(path, bmp);
                        MediaScanner mMediaScanner = new MediaScanner(VideoActivity.this);
                        mMediaScanner.scanFile(path, "jpg");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(VideoActivity.this, getResources().getString(R.string.already_saved_to_volume) + path, Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (InnerException e) {
                        e.printStackTrace();
                    } finally {
                        if (bmp != null) {
                            bmp.recycle();
                            bmp = null;
                            return;
                        }
                    }
                }
                super.run();
            }
        };
        thr.start();
    }

    private void initView() {
//        setTitle(mVideo.getName());
        setTitle(mCamera.getChannelName());
        mRealPlaySv = (SurfaceView) findViewById(R.id.realplay_sv);
        mRealPlaySh = mRealPlaySv.getHolder();
        mRealPlaySh.addCallback(this);
        startPlay();
        mImageView.setVisibility(View.GONE);
//        mImageView.setImageResource(mVideo.getImg());
    }

    private void initData() {
        AnimationDrawable animationDrawable = (AnimationDrawable) mImageView.getDrawable();
        animationDrawable.start();
        mEzPlayer = null;
        mHandler = new Handler(this);
        mCamera = (CameraBean.Camera) getIntent().getSerializableExtra(Constant.BEAN);

//        mVideo = (Video) getIntent().getSerializableExtra(Constant.BEAN);
//        new GetCamersTask().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mEzPlayer != null) {
            mEzPlayer.startRealPlay();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mEzPlayer != null) {
            mEzPlayer.stopRealPlay();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mEzPlayer != null) {
            mEzPlayer.release();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mEzPlayer != null) {
            mEzPlayer.setSurfaceHold(holder);
        }
        mRealPlaySh = holder;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mEzPlayer != null) {
            mEzPlayer.setSurfaceHold(null);
        }
        mRealPlaySh = null;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 207:
//                startRealPlay();
                break;
            default:
                break;
        }
        return false;
    }


//    class GetCamersTask extends AsyncTask<Void, Void, EZDeviceInfo> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            AnimationDrawable animationDrawable = (AnimationDrawable) mImageView.getDrawable();
//            animationDrawable.start();
//        }
//
//        @Override
//        protected EZDeviceInfo doInBackground(Void... params) {
//            try {
//                mDeviceInfo = App.getOpenSDK().getDeviceList(0, 20).get(0);
//                return mDeviceInfo;
//            } catch (BaseException e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
//
//        @Override
//        protected void onPostExecute(EZDeviceInfo ezDeviceInfo) {
//            super.onPostExecute(ezDeviceInfo);
//            mCameraInfo = EZUtils.getCameraInfoFromDevice(ezDeviceInfo, 0);
//            mImageView.setVisibility(View.GONE);
//            if (ezDeviceInfo.getStatus() == 1) {
//                mIsOnline = true;
//                startPlay();
//            } else {
//                mTextView.setVisibility(View.VISIBLE);
//                mRealPlaySv.setVisibility(View.GONE);
//            }
//        }
//    }

    private void startPlay() {
        if (mCamera != null) {

            if (mEzPlayer == null) {
                mEzPlayer = App.getOpenSDK().createPlayer(mCamera.getDeviceSerial(), Integer.parseInt(mCamera.getChannelNo()));
            }

            if (mEzPlayer == null)
                return;

            if (mCamera.getIsEncrypt() == 1) {
                mEzPlayer.setPlayVerifyCode(DataManager.getInstance().getDeviceSerialVerifyCode(mCamera.getDeviceSerial()));
            }

            mEzPlayer.setHandler(mHandler);
            mEzPlayer.setSurfaceHold(mRealPlaySh);
            mEzPlayer.startRealPlay();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = Constant.PERMISSION_CODE)
    private void permissionSuccess() {
        screenShots();
    }

    @PermissionFail(requestCode = Constant.PERMISSION_CODE)
    private void permissionFailure() {
        Toast.makeText(this, R.string.permission_failure, Toast.LENGTH_SHORT).show();
    }

    @PermissionSuccess(requestCode = Constant.PERMISSION_CODE2)
    private void permissionSuccess2() {
        Intent intent = new Intent(VideoActivity.this, CheckPicActivity.class);
        startActivity(intent);
    }

    @PermissionFail(requestCode = Constant.PERMISSION_CODE2)
    private void permissionFailure2() {
        Toast.makeText(this, R.string.permission_failure, Toast.LENGTH_SHORT).show();
    }

    @PermissionSuccess(requestCode = Constant.PERMISSION_CODE3)
    private void permissionSuccess3() {
        DialDialog.Builder builder = new DialDialog.Builder(this);
        builder.setMessage("是否呼叫 " + "110");
        builder.setPositiveButton("呼叫", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent2Dial = IntentUtil.getIntent2Dial("18060715985");
                        startActivity(intent2Dial);
                        dialog.dismiss();
                    }
                }
        );
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );
        builder.create().show();
    }

    @PermissionFail(requestCode = Constant.PERMISSION_CODE3)
    private void permissionFailure3() {
        Toast.makeText(this, R.string.permission_failure, Toast.LENGTH_SHORT).show();
    }
}
