package com.greedygame.sample.sdk.java.showcase.nongames.utils.notimportant;

import android.animation.TimeInterpolator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Build;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import com.takusemba.spotlight.shape.Shape;

import org.jetbrains.annotations.NotNull;

public class Rectangle implements Shape {
    long duration = 1000;
    TimeInterpolator interpolator = new AccelerateInterpolator(10f);
    private int[] rectCordinates  = new int[2];
    float width,height;

    public Rectangle(View targetView, int adjustDistance) {
        width = targetView.getWidth();
        height = targetView.getHeight();
        targetView.getLocationOnScreen(rectCordinates);
    }

    @Override
    public long getDuration() {
        return duration;
    }

    @NotNull
    @Override
    public TimeInterpolator getInterpolator() {
        return interpolator;
    }

    @Override
    public void draw(@NotNull Canvas canvas, @NotNull PointF pointF, float v, @NotNull Paint paint) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
         canvas.drawRoundRect(rectCordinates[0],rectCordinates[1],rectCordinates[0]+width,rectCordinates[1]+height,
         20f,20f,paint);
         }else{
         canvas.drawRect(rectCordinates[0],rectCordinates[1],rectCordinates[0]+width,rectCordinates[1]+height,
         paint);
         }
    }
}

// if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//         canvas.drawRoundRect(rectCordinates[0].(),rectCordinates[1].(),rectCordinates[0]+width,rectCordinates[1]+height,
//         20f,20f,paint
//         )
//         }else{
//         canvasac.drawRect(rectCordinates[0].(),rectCordinates[1].(),rectCordinates[0]+width,rectCordinates[1]+height,
//         paint
//         )
//         }