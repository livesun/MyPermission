# MyPermission
6.0权限框架
使用步骤：
1、
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2
```
dependencies {
	        implementation 'com.github.livesun:MyPermission:v1.1.3'
	}
```

3
```
 private final static int REQUEST_CODE = 10;
 PermissionHelper.with(MainActivity.this)
                        .requestPermission(new String[]{Manifest.permission.CAMERA})
                        .requestCode(REQUEST_CODE)
                        .request();
                       
    @PermissionFailed(REQUEST_CODE)
    public void requestFailed(){
        Toast.makeText(this,"失败",Toast.LENGTH_SHORT).show();
    }

    @PermissionSucess(REQUEST_CODE)
    public void requestSucess(){
        Toast.makeText(this,"成功",Toast.LENGTH_SHORT).show();
    }

```

4在清单文件中
```
        <activity
           android:name="com.livesun.permission.PermissionActivity"
           android:theme="@android:style/Theme.Translucent.NoTitleBar"
           android:launchMode="singleTop"
           />
```
