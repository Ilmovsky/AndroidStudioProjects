package com.lexa.mycamera;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.hardware.Camera.PictureCallback;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Camera camera = null;
    CameraView cameraView = null;
    SurfaceView sv;
    SurfaceHolder holder;
    static int CameraID = 0;
    static boolean fullScreen = true;
    static int cameraNumb;
    static boolean fullScreenPhoto = true;
    float proportion;
    static int orient;
    Display display;
    Button buttonFullScreen;

    PhotoRotate photoRotate = new PhotoRotate();
    PreviewSize previewSize = new PreviewSize();
    GalleryAddPic galleryAddPic1 = new GalleryAddPic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (fullScreen) {
            setContentView(R.layout.activity_main);
        } else {
            setContentView(R.layout.activity_main_2);
        }

        buttonFullScreen = (Button) findViewById(R.id.fullScreenButton);

        orient = getWindowManager().getDefaultDisplay().getRotation();
        display = getWindowManager().getDefaultDisplay();

        initializeCamera();

        cameraNumb = Camera.getNumberOfCameras();

    }



    @Override
    protected void onStart(){
        super.onStart();
        initializeCamera();
    }


    @Override
    protected void onResume() {
        super.onResume();

        int oreint = cameraView.setCameraDisplayOrientation(CameraID);
        initializeCamera1();
        camera.setDisplayOrientation(oreint);

    }


    @Override
    protected void onPause() {
        super.onPause();
        if (camera != null){
           pauseCamera();
        }
    }



    public void  initializeCamera() {
        if(camera == null) {
            try {
                camera = Camera.open(CameraID);
            } catch (Exception e) {
                Log.e("ERROR", "Failed to get camera: " + e.getMessage());
            }

            sv = (SurfaceView) findViewById(R.id.surfaceView);
            cameraView = new CameraView(this, camera);
            holder = sv.getHolder();
            holder.addCallback(cameraView);
        }

    }




    public void  initializeCamera1() {
        if(camera == null) {
            try {
                camera = Camera.open(CameraID);
            } catch (Exception e) {
                Log.e("ERROR", "Failed to get camera: " + e.getMessage());
            }
        }

        proportion = previewSize.setPreviewSize(fullScreen, sv, display, camera, proportion);

        try {
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        camera.startPreview();
    }



    public void pauseCamera(){
        camera.stopPreview();
        camera.release();
        sv.getHolder().removeCallback(cameraView);
        cameraView.getHolder().removeCallback(cameraView);
        camera = null;
    }



    public void onChangeCamera(View view){

        if (cameraNumb >0) {

            pauseCamera();

            if (CameraID == 1) {
                CameraID = 0;
            } else {
                CameraID = 1;
            }
            int oreint = cameraView.setCameraDisplayOrientation(CameraID);
            initializeCamera1();
            camera.setDisplayOrientation(oreint);
        }
    }


    public void onChangeView(View view) {

            pauseCamera();

            if (fullScreen) {
                fullScreen = false;
                setContentView(R.layout.activity_main_2);

            } else {
                fullScreen = true;
                setContentView(R.layout.activity_main);
            }

        initializeCamera();

        int oreint = cameraView.setCameraDisplayOrientation(CameraID);
        initializeCamera1();
       camera.setDisplayOrientation(oreint);

    }



   public void changePictureSize(View view) {
       if(fullScreenPhoto){
           fullScreenPhoto = false;
           buttonFullScreen.setText("3:4");
       } else{
           fullScreenPhoto = true;
           buttonFullScreen.setText("3:5");
       }

   }




    public void onClickPicture(View view) {

        camera.takePicture(myShutterCallback,
                null, myPictureCallback_JPG);

    }


    Camera.ShutterCallback myShutterCallback = new Camera.ShutterCallback(){

        @Override
        public void onShutter() {
            // TODO Auto-generated method stub

        }};

    PictureCallback myPictureCallback_JPG = new PictureCallback(){

        @Override
        public void onPictureTaken(byte[] arg0, Camera arg1) {

            String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(new Date());
            String output_file_name = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+File.separator +  timeStamp+".jpg";

            File pictureFile = new File(output_file_name);
            if (pictureFile.exists()) {
                pictureFile.delete();
            }

            FileOutputStream fos;

            try {
                fos = new FileOutputStream(pictureFile);


                Bitmap realImage = BitmapFactory.decodeByteArray(arg0, 0, arg0.length);

                int rotation = getWindowManager().getDefaultDisplay()
                        .getRotation();


                switch (rotation) {
                    case Surface.ROTATION_0:
                        if (CameraID == 0) {
                            realImage = photoRotate.rotate(realImage, 90, proportion, fullScreen, fullScreenPhoto, CameraID);
                        } else {
                            realImage = photoRotate.rotate(realImage, 270, proportion, fullScreen, fullScreenPhoto, CameraID);
                        }
                        break;
                    case Surface.ROTATION_90:
                            realImage = photoRotate.rotate(realImage, 0, proportion, fullScreen, fullScreenPhoto, CameraID);
                        break;
                    case Surface.ROTATION_180:
                        if (CameraID == 0) {
                            realImage = photoRotate.rotate(realImage, 270, proportion, fullScreen, fullScreenPhoto, CameraID);
                        } else {
                            realImage = photoRotate.rotate(realImage, 90, proportion, fullScreen, fullScreenPhoto, CameraID);
                        }
                        break;
                    case Surface.ROTATION_270:
                            realImage = photoRotate.rotate(realImage, 180, proportion, fullScreen, fullScreenPhoto, CameraID);
                        break;
                }


               realImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);

                fos.close();
                String fpath=pictureFile.toString();
                sendBroadcast(galleryAddPic1.galleryAddPic(fpath));

            } catch (FileNotFoundException e) {
                Log.e("Info", "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.e("TAG", "Error accessing file: " + e.getMessage());
            }

                Toast.makeText(MainActivity.this,
                        "Image saved: " + timeStamp,
                        Toast.LENGTH_LONG).show();

            camera.startPreview();
        }};







}