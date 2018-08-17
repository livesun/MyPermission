package com.livesun.mypermission;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;

import com.livesun.permission.PermissionHelper;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionHelper.with(this)
                .requestCode(20)
                .requestPermission(new String[]{Manifest.permission.CAMERA})
                .request();
    }
}
