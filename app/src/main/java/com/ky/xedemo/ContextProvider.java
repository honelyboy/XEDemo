package com.ky.xedemo;

import android.app.Application;
import android.view.LayoutInflater;

/**
 * Created by kongyi on 16/9/30.
 */

public class ContextProvider {

    static Application sInstance;

    public static void init(Application app){
        sInstance = app;
    }

    public static Application getContext(){
        return sInstance;
    }

    public static LayoutInflater getLayoutInflater(){
        return LayoutInflater.from(sInstance);
    }
}
