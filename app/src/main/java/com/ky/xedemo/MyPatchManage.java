package com.ky.xedemo;

import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;

/**
 * Created by kongyi on 16/11/16.
 */

public class MyPatchManage {

    private MyPatchManage(){}

    private PatchManager patchManager = new PatchManager(ContextProvider.getContext());

    public static MyPatchManage myPatchManage;

    public static MyPatchManage getMyPatchManage(){
        if(myPatchManage == null && myPatchManage == null){
            myPatchManage = new MyPatchManage();
        }
        return myPatchManage;
    }

    public void init(String version){
        patchManager.init(version);
        patchManager.loadPatch();
    }

    public void addPatch(String path){
        try {
            patchManager.addPatch(path);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
