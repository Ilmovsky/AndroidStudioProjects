package com.lexa.mycamera;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Size;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by Lexa on 12.04.2016.
 */
public class CameraView extends SurfaceView implements SurfaceHolder.Callback {

    private Camera mCamera;


    public CameraView(Context context, Camera camera) {
        super(context);

        mCamera = camera;

    }




    @Override
    public void surfaceCreated(SurfaceHolder holder) {
      try{

        mCamera.setPreviewDisplay(holder);
        mCamera.startPreview();
    } catch (IOException e) {
        Log.e("ERROR", "Camera error on surfaceCreated " + e.getMessage());
    }
    }



    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        try{
            mCamera.stopPreview();
             int result1 = setCameraDisplayOrientation(MainActivity.CameraID);
            mCamera.setDisplayOrientation(result1);

        } catch (Exception e){
            Log.e("ERROR", "Camera error on stopPreview " + e.getMessage());
        }

        try {

            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.e("ERROR", "Camera error on surfaceChanged " + e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }


    public int setCameraDisplayOrientation(int cameraId) {


        int rotation = MainActivity.orient;
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int result = 0;

        // получаем инфо по камере cameraId
        CameraInfo info = new CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        // задняя камера
        if (info.facing == CameraInfo.CAMERA_FACING_BACK) {
            result = ((360 - degrees) + info.orientation);
        } else
            // передняя камера
            if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
                result = ((360 - degrees) - info.orientation);
                result += 360;
            }
       return result = result % 360;
    }
}
