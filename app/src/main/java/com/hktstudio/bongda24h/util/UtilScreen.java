package com.hktstudio.bongda24h.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;

/**
 * Created by Hai on 07/03/2018.
 */

public class UtilScreen {
    public static Point getSizeScreen(Activity context){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        if(height<width){
            int temp = height;
            height = width;
            width = temp;
        }
        return new Point(width,height);
    }
}
