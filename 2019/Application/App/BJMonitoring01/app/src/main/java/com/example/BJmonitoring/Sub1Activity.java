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


public class Sub1Activity extends AppCompatActivity {
    TextView Avol, Bvol, Cvol, Acur, Bcur, Ccur;
    LoadDataThread LDT01 = new LoadDataThread();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);
        System.out.println("※ 텍스트 페이지 이동.");
        LDT01 = new LoadDataThread();
        LDT01.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println(">>>>> 스레드 종료 <<<<<");
        LDT01.interrupt();
        finish();
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

    class LoadDataThread extends Thread{
        public void run() {
            while(true) {
                try {
                    long starttime = System.currentTimeMillis();
                    Socket socket = new Socket("203.244.145.81",3000);

                    OutputStream os = socket.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(os);
                    dos.writeUTF("sixdata");

                    InputStream is = socket.getInputStream();
                    DataInputStream dis = new DataInputStream(is);

                    File file = new File("/data/data/com.example.BJmonitoring/TestFolder/Data.txt");
                    FileOutputStream fos = new FileOutputStream(file);

                    byte[] buf = new byte[1024];
                    int readbyte;

                    while((readbyte = socket.getInputStream().read(buf)) != -1){
                        fos.write(buf,0,readbyte);
                    }

                    dos.close();
                    dis.close();
                    socket.close();

                    Avol = (TextView)findViewById(R.id.volA);
                    Bvol = (TextView)findViewById(R.id.volB);
                    Cvol = (TextView)findViewById(R.id.volC);
                    Acur = (TextView)findViewById(R.id.curA);
                    Bcur = (TextView)findViewById(R.id.curB);
                    Ccur = (TextView)findViewById(R.id.curC);

                    Scanner scanner = new Scanner(file);

                    Avol.setText(null);
                    Bvol.setText(null);
                    Cvol.setText(null);
                    Acur.setText(null);
                    Bcur.setText(null);
                    Ccur.setText(null);

                    while (scanner.hasNext()) {
                        Thread.sleep(300);
                        String str = scanner.nextLine();
                        String[] arr = str.split(",");

                        Avol.setText(arr[1]);
                        Bvol.setText(arr[3]);
                        Cvol.setText(arr[5]);
                        Acur.setText(arr[7]);
                        Bcur.setText(arr[9]);
                        Ccur.setText(arr[11]);

                        long endtime = System.currentTimeMillis();
                        System.out.println("received time at client phone - socket open time2 : "+(endtime-starttime) + "ms");
                    }
                    Thread.sleep(1000);
                } catch (IOException e) {
                    break;
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}
