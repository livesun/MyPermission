package com.livesun.permission;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;

import androidx.core.app.ActivityCompat;


/**
 * 类描述：
 * 创建人：livesun
 * 创建时间：2017/8/28
 * 修改人：
 * 修改时间：
 * github：https://github.com/livesun
 */

 class PermissionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            handleIntent(getIntent());
        }
    }

    private void handleIntent(Intent intent) {

        int mRequestCode= intent.getIntExtra("requestCode",-1);
        ArrayList<String> permissions = intent.getStringArrayListExtra("permissions");
        String[] strings =  permissions.toArray(new String[permissions.size()]);
        ActivityCompat.requestPermissions(this,strings ,mRequestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        PermissionHelper.requsetResult(this,requestCode,permissions);

        finish();

    }
}
