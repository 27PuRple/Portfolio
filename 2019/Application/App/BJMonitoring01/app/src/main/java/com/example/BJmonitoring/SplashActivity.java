package com.example.BJmonitoring;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.io.DataOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.Socket;

public class SplashActivity extends AppCompatActivity {

    String password;
    PCFileClear FC = new PCFileClear();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // create the folder
        String path = "/data/data/com.example.BJmonitoring/TestFolder"; //폴더 경로
        File Folder = new File(path);

        if (!Folder.exists()) {
            try {
                Folder.mkdir(); //폴더 생성합니다.
                System.out.println("폴더가 생성되었습니다.");
            } catch (Exception e) {
                e.getStackTrace();
            }
        } else{
            System.out.println("폴더가 존재 합니다 ~");
        }



        FC.start();



        //load the password
        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        password = settings.getString("password", "");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(password.equals("")) {
                    //if there is no password
                    Intent intent = new Intent(getApplicationContext(), CreatePasswordActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    //if there is a password
                    Intent intent = new Intent(getApplicationContext(), EnterPasswordActivity.class); // EnterPasswordActivity.class
                    startActivity(intent);
                    finish();
                }
            }
        }, 2000);
    }

    class PCFileClear extends Thread{
        public void run() {
            try {
                // 서버 소켓 접속.
                Socket clientSocket = new Socket("203.244.145.81", 3000);
                System.out.println("서버접속 완료");

                String str = "Splash";

                OutputStream os = clientSocket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeUTF(str);

                System.out.println("※ 서버에 전송한 메세지 : " + str);

                dos.close();
                clientSocket.close();


            } catch (Exception e) {
                System.out.println("※ 어디선가 오류 발생.");
                //e.printStackTrace();
            }
        }
    }
}
