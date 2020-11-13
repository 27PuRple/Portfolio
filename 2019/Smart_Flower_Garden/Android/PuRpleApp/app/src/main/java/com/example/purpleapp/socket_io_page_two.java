package com.example.purpleapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class socket_io_page_two extends AppCompatActivity {
    private String TAG = "MainActivity";
    private Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_io_page_two);

        try {
            socket = IO.socket("http://192.168.0.12:8080");
            socket.connect();
        } catch(URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void goclick(View v) {
        Toast.makeText(getApplicationContext(), "GO", Toast.LENGTH_LONG).show();
        socket.emit("PotGoClick");
    }

    public void backclick(View v) {
        Toast.makeText(getApplicationContext(), "BACK", Toast.LENGTH_LONG).show();
        socket.emit("PotBackClick");
    }
}
