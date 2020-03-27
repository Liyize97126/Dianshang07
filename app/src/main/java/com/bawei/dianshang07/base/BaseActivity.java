package com.bawei.dianshang07.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    //定义
    private ActionBar actionBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView(savedInstanceState);
        actionBar = getSupportActionBar();
    }
    //方法封装
    protected abstract int getLayoutId();
    protected abstract void initView(Bundle savedInstanceState);
    protected void getAppTitle(String appTitle){
        actionBar.setTitle(appTitle);
    }
}
