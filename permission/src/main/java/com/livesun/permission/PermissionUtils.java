package com.livesun.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import java.lang.reflect.Method;
import java.util.ArrayList;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

/**
 * 类描述：6.0运行时权限工具类
 * 创建人：livesun
 * 创建时间：2017/8/28
 * 修改人：
 * 修改时间：
 * github：https://github.com/livesun
 */

 class PermissionUtils {
    private PermissionUtils(){
    }

    /**
     * 判断当前版本是否大于6.0
     * @return
     */
    public static boolean isApi23(){
        return Build.VERSION.SDK_INT>=Build.VERSION_CODES.M;
    }

    /**
     * 获取Activity
     * @param object
     * @return
     */
    public static Activity getActivity(Object object) {

        if(object instanceof Activity){
            return (Activity) object;
        }

        if(object instanceof Fragment){

            return ((Fragment) object).getActivity();
        }

        return null;
    }

    /**
     * 获取未注册的权限集合
     * @param object
     * @param requstPermissions
     * @return
     */
    public static ArrayList<String> getUnregisteredPermissions(Object object, String[] requstPermissions){
        ArrayList<String> unregisteredPermissions=new ArrayList<>();
        for (String permission : requstPermissions) {
            if(ContextCompat.checkSelfPermission(getActivity(object),permission)
                    == PackageManager.PERMISSION_DENIED//未注册
                    ){
                unregisteredPermissions.add(permission);
            }
        }
        return unregisteredPermissions;
    }

    /**
     * 申请成功
     * @param object
     * @param requestCode
     */
    public static void executeSucessMethod(Object object, int requestCode) {
        Class<?> aClass = object.getClass();
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            PermissionSucess annotation = method.getAnnotation(PermissionSucess.class);
            if(annotation!=null){
                if(annotation.value()==requestCode){
                    try {
                        method.setAccessible(true);
                        method.invoke(object,new Object[]{});
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        object=null;
                    }
                }
            }
        }
    }

    /**
     * 申请失败
     * @param object
     * @param requestCode
     */
    public static void executeFiledMethod(Object object, int requestCode) {
        Class<?> aClass = object.getClass();
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            PermissionFailed annotation = method.getAnnotation(PermissionFailed.class);
            if(annotation!=null){
                if(annotation.value()==requestCode){
                    method.setAccessible(true);
                    try {
                        method.invoke(object,new Object[]{});
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        object=null;
                    }
                }
            }
        }
    }
}
