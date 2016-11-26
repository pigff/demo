package com.fjrcloud.lin.util;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import org.xutils.DbManager;

import java.io.File;

/**
 * Created by lin on 2016/9/6.
 */
public class IntentUtil {

    public static Intent openFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase();
        if (end.equals("jpg") || end.equals("gif") || end.equals("png") || end.equals("jpeg") || end.equals("bmp")) {
            return getImageFileIntent(filePath);
        } else if (end.equals("ppt")) {
            return getPptFileIntent(filePath);
        } else if (end.equals("xls")) {
            return getExcelFileIntent(filePath);
        } else if (end.equals("doc") || end.equals("docx")) {
            return getWordFileIntent(filePath);
        } else if (end.equals("pdf")) {
            return getPdfFileIntent(filePath);
        } else if (end.equals("txt")) {
            return getTextFileIntent(filePath);
        } else {
            return null;
        }
    }

//    public static Integer getFileImage(String filePath) {
//        String end = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
//        List<Integer> imageList = new ArrayList<>();
//        if (end.equals("jpg") || end.equals("gif") || end.equals("png") || end.equals("jpeg") || end.equals("bmp")) {
//            return R.mipmap.picture;
//        } else if (end.equals("ppt")) {
//            return R.mipmap.ppt;
//        } else if (end.equals("xls")) {
//            return R.mipmap.excel;
//        } else if (end.equals("doc") || end.equals("docx")) {
//            return R.mipmap.word;
//        } else if (end.equals("pdf")) {
//            return R.mipmap.pdf;
//        } else if (end.equals("txt")) {
//            return R.mipmap.txt;
//        } else {
//            return R.mipmap.unknown;
//        }
//    }

    private static Intent getPdfFileIntent(String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setDataAndType(uri, "application/pdf");
        return intent;
    }

    private static Intent getWordFileIntent(String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setDataAndType(uri, "application/msword");
        return intent;
    }

    private static Intent getExcelFileIntent(String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setDataAndType(uri, "application/vnd.ms-excel");
        return intent;
    }

    private static Intent getImageFileIntent(String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setDataAndType(uri, "image/*");
        return intent;
    }

    private static Intent getPptFileIntent(String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        return intent;
    }

    private static Intent getTextFileIntent(String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setDataAndType(uri, "text/plain");
        return intent;
    }

    /**
     * 跳转到手机拨号界面
     */
    public static Intent getIntent2Dial(String number) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("tel://" + number));
        intent.setAction(Intent.ACTION_CALL);
        return intent;
    }

    /**
     * 手机直接拨号
     * @param number 手机号码
     * @return
     */
    public static Intent getIntent2Call(String number) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("tel://" + number));
        intent.setAction(Intent.ACTION_CALL);
        return intent;
    }

    /**
     * 判断链接是否可用
     * @param link
     * @return
     */
    public static boolean judgeLink(String link) {
        int position = link.indexOf(":");
        String head = link.substring(0, position);
        if (TextUtils.equals(head, "https") || TextUtils.equals(head, "http")) {
            return true;
        }
        return false;
    }

    public static DbManager.DaoConfig getDaoConfig() {
        File file = new File(Environment.getExternalStorageDirectory().getPath());
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbDir(file)
                .setDbName("login")
                .setAllowTransaction(true);
        return daoConfig;
    }
}
