package com.example.nenneadora.quizgame;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class GameActivity extends Activity {

    List<Question> questionList;
    TextView result;
    int score = 0;
    int questionID = 0;
    private String tableName;//return value used in switch-case
    private boolean isPaused = false; //timer paused status
    private boolean gameIsRunning;
    private long levelTotalTime = 0;
    private long timeRemaining = 0;
    private long countDownInterval = 1000; //1 second
    private BroadcastReceiver mReceiver;

    Level level;
    Category category;
    String TableName; //table name for db query

    Question currentQuestion;
    TextView txtQuestion, txtTimeLeft, txtScore, txtGamePaused;
    Button optionA, optionB, optionC, optionD;

    View pauseButton;
    View pauseMenu;
    View gameOver;
    ImageView gameOverImage;
    RelativeLayout Rel_main_game;
    LinearLayout inner_layout;


    View.OnClickListener toReplay = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            GameActivity.this.finish();
        }
    };

    View.OnClickListener Continue_list = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pauseMenu.setVisibility(View.GONE);
            gameOver.setVisibility(View.GONE);
            inner_layout.setVisibility(View.VISIBLE);
            pauseButton.setVisibility(View.VISIBLE);
            setTimer(timeRemaining);
        }
    };
    View.OnClickListener toMainMenu = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            GameActivity.this.finish();
        }
    };

    View.OnClickListener Pause_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pauseGame();
        }
    };
    View.OnClickListener toEnd = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //handle what happens when the keypad is off
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (Intent.ACTION_SCREEN_OFF.equals(action) && gameIsRunning) {
                    pauseGame();
                }else{}
            }
        };
        registerReceiver(mReceiver, intentFilter); //register reciever

        Rel_main_game = (RelativeLayout) findViewById(R.id.main_game);
        inner_layout = (LinearLayout) findViewById(R.id.inner_layout);

        txtQuestion = (TextView) findViewById(R.id.tvQuestion);     //question
       // txtQuestionNo = (TextView) findViewById(R.id.tvQuestionNo); //e.g 1/10, 2/10...
        txtScore = (TextView) findViewById(R.id.tvScore);           //users score
        txtTimeLeft = (TextView) findViewById(R.id.tvTimeLeft);     //the timer


        //The four buttons.
        //The logic is to set the text of the buttons with the options from question bank
        optionA = (Button) findViewById(R.id.button1);
        optionB = (Button) findViewById(R.id.button2);
        optionC = (Button) findViewById(R.id.button3);
        optionD = (Button) findViewById(R.id.button4);
        gameIsRunning = true;

        QuizHelper db = new QuizHelper(this); //database - question bank

        //fetch data from previous page using intent
        Bundle extras = getIntent().getExtras();
        //level = extras.getString("Level");
        level = (Level) extras.get("Level");
        category = (Category) extras.get("Category");

        //determine table name and timer (txtTimeLeft.setText("xx:xx:xx");
        switch(level){
            case BEGINNER:
                txtTimeLeft.setText("00:02:00");
                levelTotalTime = 120000;
                TableName = getTableFromCategory();
                break;
            case INTERMEDIATE:
                txtTimeLeft.setText("00:01:00");
                levelTotalTime = 60000;
                TableName = getTableFromCategory();
                break;
            case EXPERT:
                txtTimeLeft.setText("00:01:00");
                levelTotalTime = 60000;
                TableName = getTableFromCategory();
                break;
        }

        questionList = db.getAllQuestions(TableName);
        currentQuestion = questionList.get(questionID);
        setQuestionView();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        final int heightS = displayMetrics.heightPixels;
        final int widthS = displayMetrics.widthPixels;

        LayoutInflater myInflater = (LayoutInflater) getApplicationContext().getSystemService
                (getApplicationContext().LAYOUT_INFLATER_SERVICE);

        pauseButton = myInflater.inflate(R.layout.pause, null,false);

        pauseButton.setX(widthS - 350);
        pauseButton.setY(30);
        Rel_main_game.addView(pauseButton);

        pauseButton.setOnClickListener(Pause_click);
        pauseButton.getLayoutParams().height = 150;
        pauseButton.getLayoutParams().width = 150;

        pauseMenu = myInflater.inflate(R.layout.pause_menu, null, false);
        gameOver = myInflater.inflate(R.layout.gameover, null, false);

        Rel_main_game.addView(pauseMenu);
        Rel_main_game.addView(gameOver);
        pauseMenu.setVisibility(View.GONE);
        gameOver.setVisibility(View.GONE);

        ImageView Cont = (ImageView)pauseMenu.findViewById(R.id.Cont);
        ImageView toMain = (ImageView)pauseMenu.findViewById(R.id.toMain);
        ImageView exit = (ImageView)pauseMenu.findViewById(R.id.End);
        txtGamePaused = (TextView) pauseMenu.findViewById(R.id.textViewPaused);
        gameOverImage = (ImageView) gameOver.findViewById(R.id.gameOverImg);
        Button btnExit = (Button) gameOver.findViewById(R.id.exitgame);
        Button btnReplay = (Button) gameOver.findViewById(R.id.replay);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Bold.ttf");
        txtGamePaused.setTypeface(custom_font);

        Cont.setOnClickListener(Continue_list);
        toMain.setOnClickListener(toMainMenu);
        exit.setOnClickListener(toEnd);
        btnExit.setOnClickListener(toEnd);
        btnReplay.setOnClickListener(toReplay);

        setTimer(levelTotalTime); //START TIMER

        //onclicklistener to call helper method for determining if the answer is correct or not
        optionA.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                answerEvaluator(optionA);
            }
        });
        optionB.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                answerEvaluator(optionB);
            }
        });
        optionC.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                answerEvaluator(optionC);
            }
        });
        optionD.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                answerEvaluator(optionD);
            }
        });


        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        PhoneStateListener phoneStateListener = new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                //super.onCallStateChanged(state, incomingNumber);
                //String number = incomingNumber;
                if(state == TelephonyManager.CALL_STATE_RINGING || state == TelephonyManager.CALL_STATE_OFFHOOK ){
                    pauseGame();
                }
            }
        };
        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);

    }

    private void toResultPage(){
        gameIsRunning = false;
        pauseButton.setVisibility(View.GONE);
        inner_layout.setVisibility(View.GONE);
        gameOver.setVisibility(View.VISIBLE);
        pauseMenu.setVisibility(View.GONE);

        gameOverImage.setImageResource(R.drawable.cute);
        gameOverImage.setMaxHeight(300);
        gameOverImage.setMaxWidth(300);

        result = (TextView) gameOver.findViewById(R.id.result);
        result.setText("Score: "+score);
    }

    @Override
    protected void onUserLeaveHint() {
        if(gameIsRunning){
            pauseGame();
        }
        super.onUserLeaveHint();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_HOME && gameIsRunning){
            pauseGame();
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_HOME && gameIsRunning){
            pauseGame();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        GameActivity.this.finish();
    }

    protected void pauseGame(){
        pauseButton.setVisibility(View.GONE);
        inner_layout.setVisibility(View.GONE);
        gameOver.setVisibility(View.GONE);
        pauseMenu.setVisibility(View.VISIBLE);
        pauseTimer();
    }

    public boolean checkAnswer(String chosenAnswer){
        if(currentQuestion.getANSWER().equals(chosenAnswer)){
            //increase score by one if user chooses the correct answer
            score++;
            txtScore.setText("Score: "+score);
            return true;
        }
        else{
            return false;
            //do nothing if user chooses the wrong answer
        }
    }

    private void toNext(){

        if(questionID < questionList.size()){
            //if the questions aren't over, get the next question
            currentQuestion = questionList.get(questionID);
            setQuestionView();
        }
        else{
            //if user has answered all the questions during the time frame
            toResultPage();
        }
    }

    private void answerEvaluator(final Button btn){
        final boolean answer = checkAnswer(btn.getText().toString());
        if(answer){
            btn.setBackgroundResource(R.drawable.correctbutton);
        }
        else btn.setBackgroundResource(R.drawable.wrongbutton);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                btn.setBackgroundResource(R.drawable.roundbutton);
                toNext();
                }
        }, 300);

    }
    private String getTableFromCategory(){

        switch(category){
            case GENERAL:
                tableName = QuizHelper.TABLE_GENERAL;
                break;
            case ZOBIAWA:
                tableName = QuizHelper.TABLE_ZOBIAWA;
                break;
            }
        return tableName;
    }

    private void setQuestionView(){
        //replacing the placeholders with actual text and setting the quiz
        //txtQuestionNo.setText(questionID+"/10");
        txtQuestion.setText(currentQuestion.getQUESTION());
        optionA.setText(currentQuestion.getOPTA());
        optionB.setText(currentQuestion.getOPTB());
        optionC.setText(currentQuestion.getOPTC());
        optionD.setText(currentQuestion.getOPTD());

        questionID++;
    }

    private void setTimer(long startTime){
        isPaused = false;

        CountDownTimer timer;
        long millisInFuture = startTime;


        timer = new CountDownTimer(millisInFuture, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(isPaused){
                    //if the game timer is paused, cancel the current instance
                    cancel();
                }
                else{
                    //Display the remaining seconds to the app interface
                    //1 second = 1000 milliseconds
                    long millis = millisUntilFinished;
                    String hms = String.format(Locale.ENGLISH,"%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                    if(millis/1000 < 10) {
                        txtTimeLeft.setTextColor(Color.parseColor("#FF0000"));
                    }
                    txtTimeLeft.setText(hms);
                    timeRemaining = millisUntilFinished;
                }
            }

            @Override
            public void onFinish() {
                toResultPage();
            }
        }.start();
    }
    private void pauseTimer(){
        isPaused = true;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }
}
