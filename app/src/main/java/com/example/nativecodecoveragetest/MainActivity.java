package com.example.nativecodecoveragetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.system.ErrnoException;
import android.system.Os;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    static String TAG = "MainActivityxxxx";

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // 最后的请求码是对应回调方法的请求码
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    }, 1001);
        } else {
            Toast.makeText(this, "你已经有权限了，可以直接拨打电话", Toast.LENGTH_LONG).show();
        }

        try {
            String path = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
            File savePath = new File(path, "Music");
            Log.d(TAG, savePath.getAbsolutePath());
            Os.setenv("GCOV_PREFIX", getCacheDir().getAbsolutePath(), true);
            Os.setenv("GCOV_PREFIX_STRIP", "100", true);
        } catch (ErrnoException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream xx = new FileOutputStream("/data/local/tmp/gcov/abc");
            xx.write(10);
            xx.close();
            Log.d(TAG, "write file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d(TAG, "write xxx");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "write yyy");
        }


        int r = callNative();
        Log.d(TAG, "result is " + r);

        nativeFlush();
    }

    @Override
    protected void onDestroy() {
        nativeFlush();
        super.onDestroy();
    }

    private native int callNative();

    private native void nativeFlush();
}