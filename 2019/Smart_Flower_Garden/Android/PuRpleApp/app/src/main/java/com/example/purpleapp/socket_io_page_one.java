package com.example.purpleapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class socket_io_page_one extends AppCompatActivity {
    private String TAG = "MainActivity";
    private Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_io_page_one);

        try {
            socket = IO.socket("http://192.168.0.12:8080");
            socket.connect();
        } catch(URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void clickon(View v) {
        Toast.makeText(getApplicationContext(), "OPEN", Toast.LENGTH_LONG).show();
        socket.emit("ClickOpen");
    }

    public void clickmid(View v) {
        Toast.makeText(getApplicationContext(), "MIDDLE", Toast.LENGTH_LONG).show();
        socket.emit("ClickMid");
    }

    public void clickoff(View v) {
        Toast.makeText(getApplicationContext(), "CLOSE", Toast.LENGTH_LONG).show();
        socket.emit("ClickClose");
    }
}
