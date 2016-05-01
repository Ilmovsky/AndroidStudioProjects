package com.lexa.mycamera;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.hardware.Camera;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by Lexa on 21.04.2016.
 */
public class PreviewSize {

    float setPreviewSize(boolean fullScreen, SurfaceView sv, Display display,
                        Camera camera, float proportion) {


        sv.getLayoutParams().height = 0;
        sv.getLayoutParams().width = 0;

        Log.e(String.valueOf(display.getWidth()), String.valueOf(display.getHeight()));

        boolean orient = display.getWidth()>display.getHeight();

        Camera.Parameters parameters = camera.getParameters();
        parameters.setPreviewSize(800,600);
        camera.setParameters(parameters);

        // определяем размеры превью камеры
        Camera.Size size = camera.getParameters().getPreviewSize();


        Log.e(String.valueOf(size.width),String.valueOf(size.height));

        RectF rectDisplay = new RectF();
        RectF rectPreview = new RectF();


        if(!orient){
            rectPreview.set(0, 0, size.height, size.width);
            rectDisplay.set(0, 0, display.getWidth(), display.getHeight() / 2);
        }
        else{
            rectPreview.set(0, 0, size.width, size.height);
            rectDisplay.set(0, 0, display.getWidth() / 2, display.getHeight());
        }

        Matrix matrix = new Matrix();

        // подготовка матрицы преобразования
        if (fullScreen) {
            // если превью будет "втиснут" в экран (второй вариант из урока)
            matrix.setRectToRect(rectPreview, rectDisplay,
                    Matrix.ScaleToFit.START);
            if(!orient) {
                proportion =(display.getHeight()/Float.valueOf(display.getWidth()))/(size.width/Float.valueOf(size.height));
                Log.e("dgffd1",String.valueOf(proportion));}
            else{
                proportion =(display.getWidth()/Float.valueOf(display.getHeight()))/(size.width/Float.valueOf(size.height));
                Log.e("dgffd2",String.valueOf(proportion));
            }
        } else {
            // если экран будет "втиснут" в превью (третий вариант из урока)
            matrix.setRectToRect(rectDisplay, rectPreview,
                    Matrix.ScaleToFit.START);
            matrix.invert(matrix);
            if(!orient) {
                proportion = (display.getWidth() / Float.valueOf(size.height) * size.width / (display.getHeight() / 2.f));
            }else{
                proportion = (display.getHeight() / Float.valueOf(size.height) * size.width / (display.getWidth() / 2.f));
            }
        }
        // преобразование
        matrix.mapRect(rectPreview);

        // установка размеров surface из получившегося преобразования
        sv.getLayoutParams().height = (int) (rectPreview.bottom);
        sv.getLayoutParams().width = (int) (rectPreview.right);

        return proportion;
    }

}
