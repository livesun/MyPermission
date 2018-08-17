package com.livesun.permission;

import android.app.Activity;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;

/**
 * 类描述：6.0权限申请类
 * 创建人：livesun
 * 创建时间：2017/8/28
 * 修改人：
 * 修改时间：
 * github：https://github.com/livesun
 * 用法:
 * PermissionHelper.with(Activity/Fragment)
 *.requestCode(CALL_PHONE_CODE)
 *.requestPermission(new String[]{Manifest.permission.CALL_PHONE})
 *.request();
 */

public class PermissionHelper {

    private  int mRequestCode;
    private  static Object mObject;
    private String[] mPermissions;

    private PermissionHelper(Object object){
        this.mObject = object;
    }

    public void clear(){
        if(mObject!=null){
            mObject=null;
        }
    }


    /**
     * 初始化方法--传入activity
     * @param activity
     * @return
     */
    public static PermissionHelper with(Activity activity){

        return new PermissionHelper(activity);
    }

    public static PermissionHelper with(Fragment fragment){

        return new PermissionHelper(fragment);
    }

    /**
     * 传入请求码
     * @param requestCode
     * @return
     */
    public PermissionHelper requestCode(int requestCode){
        mRequestCode = requestCode;
        return this;
    }

    /**
     * 传入请求数组
     * @param permissions
     * @return
     */
    public PermissionHelper requestPermission(String[] permissions){
        mPermissions = permissions;
        return this;
    }

    /**
     * 请求
     */
    public void request(){
        //判断是否大于6.0
        if(!PermissionUtils.isApi23()){
            //如果小于6.0直接执行成功的方法
            PermissionUtils.executeSucessMethod(mObject,mRequestCode);
            mObject=null;
            return;
        }
        //如果大于6.0,获取当前权限数组是否注册
        ArrayList<String> unregisteredPermissions = PermissionUtils.getUnregisteredPermissions(mObject, mPermissions);

        //判断是否已经注册过
        if(unregisteredPermissions.size()==0){
        //执行成功的方法
            PermissionUtils.executeSucessMethod(mObject,mRequestCode);
            mObject=null;
        }else {
            Intent intent = new Intent(PermissionUtils.getActivity(mObject), PermissionActivity.class);
            intent.putExtra("requestCode",mRequestCode);
            intent.putStringArrayListExtra("permissions",unregisteredPermissions);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PermissionUtils.getActivity(mObject).startActivity(intent);
        }
    }

    /**
     * 申请权限的回调方法
     * @param permissions
     * @param requestCode
     */
     static void requsetResult(Object object,int requestCode,String[] permissions){

        //检查是否注册
        List<String> unregisteredPermissions=PermissionUtils.getUnregisteredPermissions(mObject,permissions);

        if(unregisteredPermissions.size()==0){
            PermissionUtils.executeSucessMethod(mObject,requestCode);
        }else{
            PermissionUtils.executeFiledMethod(mObject,requestCode);
        }
        mObject=null;
    }

}
