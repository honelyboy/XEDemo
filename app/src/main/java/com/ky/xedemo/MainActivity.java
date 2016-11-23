package com.ky.xedemo;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ky.xedemo.location.IndoorLocationActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.fix_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "";//获取补丁的url.
                fixBug(url);
            }
        });

        findViewById(R.id.location_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IndoorLocationActivity.class));
            }
        });


    }

    /**
     * 通过网络获取补丁并加载。
     * @param url
     */
    private void fixBug(String url){
        String []ss = url.split("/");
        String patchName = ss[ss.length-1];
        Log.e("patchUrl===",patchName);
        OkHttpUtils.get().url(url).build().execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(),patchName) {
            @Override
            public void onError(Call call, Exception e, int id) {
            }
            @Override
            public void onResponse(File response, int id) {
                //加载补丁的方法
                MyPatchManage.getMyPatchManage().addPatch(response.getPath());
            }
        });
    }
}
