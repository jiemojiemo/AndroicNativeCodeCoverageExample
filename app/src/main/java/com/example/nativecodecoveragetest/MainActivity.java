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
    static String TAG = "MainActivity";

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Os.setenv("GCOV_PREFIX", getCacheDir().getAbsolutePath(), true);
            Os.setenv("GCOV_PREFIX_STRIP", "100", true);
        } catch (ErrnoException e) {
            e.printStackTrace();
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