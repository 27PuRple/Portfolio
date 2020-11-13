package com.example.purpleapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void movepage1(View v) {
        Intent intent = new Intent(getApplicationContext(), socket_io_page_one.class);
        startActivity(intent);
    }

    public void movepage2(View v) {
        Intent intent = new Intent(getApplicationContext(), socket_io_page_two.class);
        startActivity(intent);
    }

    public void movepage3(View v) {
        Intent intent = new Intent(getApplicationContext(), socket_io_page_three.class);
        startActivity(intent);
    }
}
