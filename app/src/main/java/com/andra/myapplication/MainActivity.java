package com.andra.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void firstImageButton(View view) {
        Intent intent = new Intent(MainActivity.this, motor.class);
        startActivity(intent);
    }
    public void secondImageButton(View view) {
        Intent intent = new Intent(MainActivity.this, mobil.class);
        startActivity(intent);
    }
    public void about(View view) {
        Intent intent = new Intent(MainActivity.this, about.class);
        startActivity(intent);
    }
}
