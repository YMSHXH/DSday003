package com.example.king.dsday003.view.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends BaseActivity implements Contact.Iview {


    private Presenter presenter;
    private EditText mEdUrl;
    private EditText mEdPwd;
    private Button mBtnLogin;
    private Button mBtnReg;


    protected void initView() {
        //把Contact.Iview接口设置到Presenter
        presenter = new Presenter(this);


        mEdUrl = (EditText) findViewById(R.id.ed_url);
        mEdPwd = (EditText) findViewById(R.id.ed_pwd);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        mBtnReg = (Button) findViewById(R.id.btn_zh);
        mBtnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegActivity.class);
                startActivityForResult(intent,1000);
            }
        });
    }

    private void login() {

        //获取数据
        String url = mEdUrl.getText().toString();
        String pwd = mEdPwd.getText().toString();
        Map<String,String> params = new HashMap<>();
        params.put("mobile",url);
        params.put("password",pwd);

        presenter.login(params,Api.LOGIN);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getResLayId() {
        return R.layout.activity_main;
    }

    @Override
    public void mobileError(String msg) {

    }

    @Override
    public void pwdError(String msg) {
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failure(String msg) {

    }

    @Override
    public void success(Object o) {
        String s = (String) o;
        User user = new Gson().fromJson(s, User.class);
        String msg = user.getMsg();
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
        if ("登录成功".equals(msg)){
            Intent intent = new Intent(MainActivity.this,MessageActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void successMsg(String msg) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == 2000){
            String mobile = data.getStringExtra("mobile");
            String password = data.getStringExtra("password");

            mEdUrl.setText(mobile);
            mEdPwd.setText(password);
        }
    }
}
