package com.example.nenneadora.quizgame;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

public class MainActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener{
    AHBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bottomNavigation= (AHBottomNavigation) findViewById(R.id.myBottomNav);
        bottomNavigation.setOnTabSelectedListener(this);
        this.createNavItems();
    }
    private void createNavItems()
    {
        //CREATE ITEMS
        AHBottomNavigationItem crimeItem=new AHBottomNavigationItem("Beginner",R.drawable.play);
        AHBottomNavigationItem dramaItem=new AHBottomNavigationItem("Intermediate",R.drawable.play);
        AHBottomNavigationItem docstem=new AHBottomNavigationItem("Expert",R.drawable.play);
        //ADD THEM to bar
        bottomNavigation.addItem(crimeItem);
        bottomNavigation.addItem(dramaItem);
        bottomNavigation.addItem(docstem);
        //set properties
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#F4F0F0"));
        bottomNavigation.setAccentColor(Color.parseColor("#FF4081"));
        //bottomNavigation.setInactiveColor(Color.);

        //set current item
        bottomNavigation.setCurrentItem(0);
    }
    @Override
    public void onTabSelected(int position, boolean wasSelected) {
        //show fragment
        if (position==0)
        {
            EasyFragment beg=new EasyFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,beg).commit();
        }else  if (position==1)
        {
            MedFragment med=new MedFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,med).commit();
        }else  if (position==2)
        {
            HardFragment exp=new HardFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,exp).commit();
        }
    }
}