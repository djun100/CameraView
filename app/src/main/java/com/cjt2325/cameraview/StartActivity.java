package com.cjt2325.cameraview;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hei.permission.PermissionActivity;

public class StartActivity extends PermissionActivity {
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission(new CheckPermListener() {
                    @Override
                    public void superPermission() {
                        startActivity(new Intent(StartActivity.this,MainActivity.class));
                    }
                },android.R.string.cancel, PERMISSIONS);
            }
        });
    }

}
