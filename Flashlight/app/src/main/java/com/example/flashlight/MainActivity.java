package com.example.flashlight;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private Switch switchFlashlightButton;
    private CameraManager cameraManager;
    private String getCameraID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchFlashlightButton = findViewById(R.id.flashlightSwitch);
        switchFlashlightButton.setOnCheckedChangeListener(this::ToggleFlashlightSwitch);

        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            getCameraID = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void ToggleFlashlightSwitch(CompoundButton compoundButton, boolean b) {
        try {
            cameraManager.setTorchMode(getCameraID, b);
        }
        catch (CameraAccessException e){
            e.printStackTrace();
        }
    }


}