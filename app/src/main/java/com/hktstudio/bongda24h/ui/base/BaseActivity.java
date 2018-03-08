package com.hktstudio.bongda24h.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hktstudio.bongda24h.util.UtilScreen;
import com.liuguangqiang.swipeback.SwipeBackLayout;




/**
 * Created by Hai on 19/02/2018.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    protected void setLayoutView(int idLayout){
        View v =getLayoutInflater().inflate(idLayout,null);
        SwipeBackLayout cs = new SwipeBackLayout(this);
        cs.addView(v);
        cs.setDragEdge(SwipeBackLayout.DragEdge.LEFT);
        setContentView(cs);
        cs.setFinishAnchor(UtilScreen.getSizeScreen(this).x/5);
    }
}
