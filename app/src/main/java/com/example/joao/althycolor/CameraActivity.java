package com.example.joao.althycolor;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.camera2.*;
import android.util.Log;
import android.content.Context;
import android.util.Size;

public class CameraActivity extends AppCompatActivity {
    private static final String Tag = "CameraActivity";
    private String cameraId;
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private Size imageDimension;
    protected CameraDevice cameraDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
    }


    private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(CameraDevice camera) {
            //Isto é chamado quando a camara é aberta
            cameraDevice = camera;
        }
        @Override
        public void onDisconnected(CameraDevice camera) {
            return;
        }
        @Override
        public void onError(CameraDevice camera, int error) {
            return;
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void openCamera(int id) {

        try {
            CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            String firstCamera = manager.getCameraIdList()[0];
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(CameraActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CAMERA_PERMISSION);
                return;
            }
            manager.openCamera(firstCamera,stateCallback,null);
        } catch (CameraAccessException e1) {
            e1.printStackTrace();
        }

    }



}
