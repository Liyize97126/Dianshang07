package com.bawei.dianshang07.util;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    protected static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
