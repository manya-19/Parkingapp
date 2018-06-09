package com.example.manya.locationapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.manya.locationapp.service.Main2Activity;

public class Main3Activity extends AppCompatActivity {
private static int SPLASH_TIME_OUT=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

     new Handler().postDelayed(new Runnable(){
        @Override
         public void run(){
            Intent homeinent=new Intent(Main3Activity.this, Main2Activity.class);
            startActivity(homeinent);
            finish();
        }

     },SPLASH_TIME_OUT);
    }

}
