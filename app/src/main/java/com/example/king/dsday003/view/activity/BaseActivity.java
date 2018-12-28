package com.example.king.dsday003.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.king.dsday003.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResLayId());

        initView();
        initData();
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract int getResLayId();
}
