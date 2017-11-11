package com.example.nenneadora.quizgame;

/**
 * Created by NenneAdora on 7/7/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Window;

public class Splashscreen extends Activity {

    private static final int SPLASH_TIME_OUT = 1000;
    //private boolean isMinimized =
    private Handler mHandler = new Handler();
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splashscreen);
        mRunnable = new Runnable() {

            @Override
            public void run() {
                openLandingActivity();
            }
        };
        mHandler.postDelayed(mRunnable, SPLASH_TIME_OUT);//
    }

    protected void openLandingActivity() {
        // TODO Auto-generated method stub
        Intent intent = new Intent(Splashscreen.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            mHandler.removeCallbacks(mRunnable);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}