package com.example.BJmonitoring;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;


public class Sub3Activity extends AppCompatActivity {
    TextView text;
    LoadDataThread LDT1 = new LoadDataThread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub3);
        LDT1 = new LoadDataThread();
        LDT1.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println(">>>>> 스레드 종료 <<<<<");
        LDT1.interrupt();
        finish();
    }

    public void em_btn(View v) {
        LDT1.interrupt();
    }

    class LoadDataThread extends Thread {
        public void run() {
            while(true) {
                try {
                    long starttime = System.currentTimeMillis();
                    Socket socket = new Socket("203.244.145.81",3000);

                    OutputStream os = socket.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(os);
                    dos.writeUTF("TextData");

                    InputStream is = socket.getInputStream();
                    DataInputStream dis = new DataInputStream(is);

                    File file = new File("/data/data/com.example.BJmonitoring/TestFolder/data_test.txt");
                    FileOutputStream fos = new FileOutputStream(file);

                    byte[] buf = new byte[1024];
                    int readbyte;

                    while((readbyte = socket.getInputStream().read(buf)) != -1) {
                        fos.write(buf,0,readbyte);
                    }

                    dos.close();
                    dis.close();
                    socket.close();

                    text = (TextView)findViewById(R.id.textViewer);

                    Scanner scanner = new Scanner(file);

                    text.setText(null);

                    while(scanner.hasNext()) {
                        Thread.sleep(300);
                        text.append(scanner.nextLine() + "\n");

                        long endtime = System.currentTimeMillis();
                        System.out.println("received time at client phone - socket open time : "+(endtime-starttime) + "ms");
                    }
                    Thread.sleep(0);
                } catch (IOException e) {
                    break;
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}
