package com.example.BJmonitoring;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Sub4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub4);
    }

    public void em_btn(View v) {
        new Thread() {
            @Override
            public void run() {
                try {
                    long nstart = 0, nend = 0;
                    nstart = System.currentTimeMillis();
                    Socket socket = new Socket("203.244.145.81",3000);
                    OutputStream os = socket.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(os);
                    nend = System.currentTimeMillis();
                    dos.writeUTF("received time at server PC - socket open time :" + (nend-nstart) + "ms");
                } catch (IOException e) {
                    System.out.println(toString());
                }
            }
        }.start();
    }
}
