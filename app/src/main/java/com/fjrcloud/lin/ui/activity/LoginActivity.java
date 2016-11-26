package com.fjrcloud.lin.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fjrcloud.lin.R;
import com.fjrcloud.lin.ui.base.BaseActivity;

import org.xutils.common.util.MD5;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by lin on 2016/8/15.
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @ViewInject(R.id.input_account_edit)
    private EditText mAccountEdit;

    @ViewInject(R.id.input_pwd_edit)
    private EditText mPwdEdit;

    @ViewInject(R.id.login_btn)
    private Button mLogin;
    private String mUserName;
    private String mPassword;

//    private ResponseHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * -------------------------------------------------------------------------------------------------------------------------------------------------
     */


    /**
     * 将用户名和密码记录进sharedpreferences中
     */
    private void initSp() {
        SharedPreferences sp = getSharedPreferences("user_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("account", mUserName);
        editor.putString("password", mPassword);
        editor.apply();
    }


    /**
     * -------------------------------------------------------------------------------------------------------------------------------------------------
     */

    @Event(R.id.login_btn)
    private void onLoginClick(View view) {
        mUserName = mAccountEdit.getText().toString();
        mPassword = MD5.md5(mPwdEdit.getText().toString());
        if (TextUtils.equals(mUserName, "") || TextUtils.equals(mPassword, "")) {
            Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
        }
    }

}
