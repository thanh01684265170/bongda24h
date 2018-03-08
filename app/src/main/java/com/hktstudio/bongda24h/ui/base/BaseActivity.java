package com.hktstudio.bongda24h.ui.base;


import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import com.r0adkll.slidr.Slidr;


/**
 * Created by Hai on 19/02/2018.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Slidr.attach(this);
    }

}
