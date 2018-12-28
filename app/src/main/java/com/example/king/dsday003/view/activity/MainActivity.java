package com.example.king.dsday003.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.king.dsday003.R;
import com.example.king.dsday003.benas.User;
import com.example.king.dsday003.contact.Contact;
import com.example.king.dsday003.presenter.Presenter;
import com.google.gson.Gson;

public class MainActivity extends BaseActivity implements Contact.Iview {

    private Presenter presenter;
    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        //把Contact.Iview接口设置到Presenter
        presenter = new Presenter(this);
        presenter.login();

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
    }

    @Override
    public void successMsg(String msg) {

    }
}
