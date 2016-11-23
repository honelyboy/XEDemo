package com.ky.xedemo;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by kongyi on 16/11/22.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ContextProvider.init(this);
        String version = "";
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(),0);
            version = packageInfo.versionName;
        }catch (Exception e){
            e.printStackTrace();
        }

        Log.e("application","init");
        Log.e("ContextProvider",ContextProvider.getContext()+"");

        //热更新初始化
        MyPatchManage.getMyPatchManage().init(version);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

        SDKInitializer.initialize(getApplicationContext());
    }
}
