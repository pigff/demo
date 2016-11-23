package com.example.lin.demo.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lin.demo.R;
import com.example.lin.demo.ui.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by lin on 2016/8/11.
 */
@ContentView(R.layout.activity_modify_pwd_layout)
public class ModifyPwdActivity extends BaseActivity {

    @ViewInject(R.id.input_old_pwd_edit)
    private EditText mOldEdit;

    @ViewInject(R.id.input_new_pwd_edit)
    private EditText mNewEdit;

    @ViewInject(R.id.confirm_new_edit)
    private EditText mConfirmEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }


    @Event(R.id.confirm_modify_pwd)
    private void modifyClick(View view) {
        String oldPwd = mOldEdit.getText().toString();
        String newPwd = mNewEdit.getText().toString();
        String confirmPwd = mConfirmEdit.getText().toString();
        if (TextUtils.equals(oldPwd, "") || TextUtils.equals(newPwd, "") || TextUtils.equals(confirmPwd, "")) {
            Toast.makeText(this, R.string.modify_psw_error, Toast.LENGTH_SHORT).show();
        } else if (TextUtils.equals(newPwd, confirmPwd)) {
           // 修改密码操作
        } else if (!TextUtils.equals(newPwd, confirmPwd)) {
            Toast.makeText(this, R.string.modify_psw_error2, Toast.LENGTH_SHORT).show();
        }
    }

    private void init() {
        setTitle("修改密码");
    }

}
