package com.lexa.mycamera;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;
import android.view.Surface;

/**
 * Created by Lexa on 20.04.2016.
 */
public class PhotoRotate {

    public Bitmap rotate(Bitmap bitmap, int degree,float proportion, boolean fullScreen,
                         boolean fullScreenPhoto,int cameraNumb) {
        int w = 0;
        int h = 0;

        Matrix mtx = new Matrix();
        mtx.setRotate(degree);

        if(fullScreen&&!fullScreenPhoto){
            w = bitmap.getWidth();
            h = bitmap.getHeight();
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
        }
        else{
                    if (fullScreen) {
                        w = bitmap.getWidth();
                        h = (int) (bitmap.getHeight() / proportion);
                        Log.e("dgffd",String.valueOf(proportion));
                        bitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
                    } else {
                        w = (int) (bitmap.getWidth() / proportion);
                        h = bitmap.getHeight();
                        if (cameraNumb == 0) {
                            bitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
                        } else {
                            bitmap = Bitmap.createBitmap(bitmap, bitmap.getWidth() - w, 0, w, h, mtx, true);
                        }
                    }
        }
        return bitmap;
    }

}
