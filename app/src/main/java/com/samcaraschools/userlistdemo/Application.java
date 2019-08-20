package com.samcaraschools.userlistdemo;

import android.content.Context;

import com.samcaraschools.userlistdemo.local.DatabaseClient;

public class Application extends android.app.Application {
    private static Application instance;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = getApplicationContext();
    }

    public static DatabaseClient getDatabase(){
        return DatabaseClient.getInstance(mContext);
    }
}
