package com.example.nenneadora.quizgame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeActivity extends Activity {

    RelativeLayout btn;
    Button PlayButton,ImagePlay;
    TextView quizName, quizAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        if (getIntent().getExtras() != null && getIntent().getExtras().getBoolean("EXIT")) {
            finish();
        }

        PlayButton = (Button) findViewById(R.id.img_btn);

        quizName = (TextView) findViewById(R.id.quizname);
        quizAuthor = (TextView) findViewById(R.id.quizauthor);

        Typeface Custom = Typeface.createFromAsset(getAssets(), "fonts/Walkway.ttf");
        quizName.setTypeface(Custom);
        quizAuthor.setTypeface(Custom);

        PlayButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        PlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();

            }
        });
    }

        private void play(){
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
    }


}
