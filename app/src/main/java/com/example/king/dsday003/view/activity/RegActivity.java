package com.example.king.dsday003.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.king.dsday003.R;
import com.example.king.dsday003.benas.User;
import com.example.king.dsday003.contact.Contact;
import com.example.king.dsday003.presenter.Presenter;
import com.example.king.dsday003.utils.Api;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class RegActivity extends BaseActivity implements Contact.Iview {

    private Presenter presenter;

    private EditText mEdUrl;
    private EditText mEdPwd;
    private Button mBtnLogin;
    private String url;
    private String pwd;


    protected void initView() {
        presenter = new Presenter(this);

        mEdUrl = (EditText) findViewById(R.id.ed_url);
        mEdPwd = (EditText) findViewById(R.id.ed_pwd);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Res();
            }
        });
    }

    private void Res() {
        //获取数据
        url = mEdUrl.getText().toString();
        pwd = mEdPwd.getText().toString();
        Map<String,String> params = new HashMap<>();
        params.put("mobile", url);
        params.put("password", pwd);

        presenter.login(params,Api.API);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getResLayId() {
        return R.layout.activity_reg;
    }

    @Override
    public void mobileError(String msg) {

    }

    @Override
    public void pwdError(String msg) {

    }

    @Override
    public void failure(String msg) {

    }

    @Override
    public void success(Object o) {
        String s = (String) o;
        User user = new Gson().fromJson(s, User.class);
        String msg = user.getMsg();
        Toast.makeText(RegActivity.this,msg,Toast.LENGTH_SHORT).show();
        if ("注册成功".equals(msg)){
            Intent intent = new Intent();
            intent.putExtra("mobile", url);
            intent.putExtra("password", pwd);
            setResult(2000,intent);
            finish();
        }

    }

    @Override
    public void successMsg(String msg) {

    }
}
