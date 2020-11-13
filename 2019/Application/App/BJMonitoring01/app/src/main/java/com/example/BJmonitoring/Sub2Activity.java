package com.example.BJmonitoring;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class Sub2Activity extends AppCompatActivity {
    int DownloadData = 0;
    int VideoListNum = 0;

    String VideoListName;

    TextView ShowText;

    Button VideoButton1;
    Button VideoButton2;
    Button VideoButton3;
    Button VideoButton4;
    Button VideoButton5;
    Button VideoButton6;
    Button VideoButton7;
    Button VideoButton8;

    LoadListThread LDT02 = new LoadListThread();
    DownloadVideo DV = new DownloadVideo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);
        System.out.println("※ 비디오 페이지 이동.");
        LDT02.start();






        VideoButton1 = (Button) findViewById(R.id.button1);
        VideoButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread TextPageThread1 = new Thread() {
                    @Override
                    public void run() {
                        if (VideoListNum >= 1 ){
                            System.out.println("동작 가능");

                            File FileList = new File("/data/data/com.example.BJmonitoring/TestFolder/FileList.txt");


                            try {
                                Scanner scanner = new Scanner(FileList);
                                VideoListName = scanner.nextLine();

                                // System.out.println("VideoListName : " + VideoListName);
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }

                            DV = new DownloadVideo();
                            DV.start();
                        }
                        else{
                            System.out.println("동작 불가능");
                        }
                    }
                };
                TextPageThread1.start();
            }
        });

        VideoButton2 = (Button) findViewById(R.id.button2);
        VideoButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread TextPageThread2 = new Thread() {
                    @Override
                    public void run() {
                        if (VideoListNum >= 2 ){
                            System.out.println("동작 가능");

                            File FileList = new File("/data/data/com.example.BJmonitoring/TestFolder/FileList.txt");

                            try {
                                Scanner scanner = new Scanner(FileList);
                                VideoListName = scanner.nextLine();
                                VideoListName = scanner.nextLine();

                                // System.out.println("VideoListName : " + VideoListName);
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }

                            DV = new DownloadVideo();
                            DV.start();


                        }
                        else{
                            System.out.println("동작 불가능");
                        }
                    }
                };
                TextPageThread2.start();
            }
        });

        VideoButton3 = (Button) findViewById(R.id.button3);
        VideoButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread TextPageThread3 = new Thread() {
                    @Override
                    public void run() {
                        if (VideoListNum >= 3 ){
                            System.out.println("동작 가능");

                            File FileList = new File("/data/data/com.example.BJmonitoring/TestFolder/FileList.txt");

                            try {
                                Scanner scanner = new Scanner(FileList);
                                VideoListName = scanner.nextLine();
                                VideoListName = scanner.nextLine();
                                VideoListName = scanner.nextLine();

                                // System.out.println("VideoListName : " + VideoListName);
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }

                            DV = new DownloadVideo();
                            DV.start();
                        }
                        else{
                            System.out.println("동작 불가능");
                        }
                    }
                };
                TextPageThread3.start();
            }
        });

        VideoButton4 = (Button) findViewById(R.id.button4);
        VideoButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread TextPageThread4 = new Thread() {
                    @Override
                    public void run() {
                        if (VideoListNum >= 4 ){
                            System.out.println("동작 가능");

                            File FileList = new File("/data/data/com.example.BJmonitoring/TestFolder/FileList.txt");

                            try {
                                Scanner scanner = new Scanner(FileList);
                                VideoListName = scanner.nextLine();
                                VideoListName = scanner.nextLine();
                                VideoListName = scanner.nextLine();
                                VideoListName = scanner.nextLine();

                                // System.out.println("VideoListName : " + VideoListName);
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }

                            DV = new DownloadVideo();
                            DV.start();
                        }
                        else{
                            System.out.println("동작 불가능");
                        }
                    }
                };
                TextPageThread4.start();
            }
        });

        VideoButton5 = (Button) findViewById(R.id.button5);
        VideoButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread TextPageThread5 = new Thread() {
                    @Override
                    public void run() {
                        if (VideoListNum >= 5 ){
                            System.out.println("동작 가능");

                            File FileList = new File("/data/data/com.example.BJmonitoring/TestFolder/FileList.txt");

                            try {
                                Scanner scanner = new Scanner(FileList);
                                VideoListName = scanner.nextLine();
                                VideoListName = scanner.nextLine();
                                VideoListName = scanner.nextLine();
                                VideoListName = scanner.nextLine();

                                VideoListName = scanner.nextLine();

                                // System.out.println("VideoListName : " + VideoListName);
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }

                            DV = new DownloadVideo();
                            DV.start();
                        }
                        else{
                            System.out.println("동작 불가능");
                        }
                    }
                };
                TextPageThread5.start();
            }
        });

        VideoButton6 = (Button) findViewById(R.id.button6);
        VideoButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread TextPageThread6 = new Thread() {
                    @Override
                    public void run() {
                        if (VideoListNum >= 6 ){
                            System.out.println("동작 가능");

                            File FileList = new File("/data/data/com.example.BJmonitoring/TestFolder/FileList.txt");

                            try {
                                Scanner scanner = new Scanner(FileList);
                                VideoListName = scanner.nextLine();
                                VideoListName = scanner.nextLine();
                                VideoListName = scanner.nextLine();
                                VideoListName = scanner.nextLine();

                                VideoListName = scanner.nextLine();
                                VideoListName = scanner.nextLine();

                                // System.out.println("VideoListName : " + VideoListName);
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }

                            DV = new DownloadVideo();
                            DV.start();
                        }
                        else{
                            System.out.println("동작 불가능");
                        }
                    }
                };
                TextPageThread6.start();
            }
        });

        VideoButton7 = (Button) findViewById(R.id.button7);
        VideoButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread TextPageThread7 = new Thread() {
                    @Override
                    public void run() {
                        if (VideoListNum >= 7 ){
                            System.out.println("동작 가능");

                            File FileList = new File("/data/data/com.example.BJmonitoring/TestFolder/FileList.txt");

                            try {
                                Scanner scanner = new Scanner(FileList);
                                VideoListName = scanner.nextLine();
                                VideoListName = scanner.nextLine();
                                VideoListName = scanner.nextLine();
                                VideoListName = scanner.nextLine();

                                VideoListName = scanner.nextLine();
                                VideoListName = scanner.nextLine();
                                VideoListName = scanner.nextLine();

                                // System.out.println("VideoListName : " + VideoListName);
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }

                            DV = new DownloadVideo();
                            DV.start();
                        }
                        else{
                            System.out.println("동작 불가능");
                        }
                    }
                };
                TextPageThread7.start();
            }
        });

        VideoButton8 = (Button) findViewById(R.id.button8);
        VideoButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread TextPageThread8 = new Thread() {
                    @Override
                    public void run() {
                        if (VideoListNum >= 8 ){
                            System.out.println("동작 가능");

                            File FileList = new File("/data/data/com.example.BJmonitoring/TestFolder/FileList.txt");
                            try {
                                Scanner scanner = new Scanner(FileList);
                                VideoListName = scanner.nextLine();
                                VideoListName = scanner.nextLine();
                                VideoListName = scanner.nextLine();
                                VideoListName = scanner.nextLine();

                                VideoListName = scanner.nextLine();
                                VideoListName = scanner.nextLine();
                                VideoListName = scanner.nextLine();
                                VideoListName = scanner.nextLine();

                                System.out.println("VideoListName : " + VideoListName);
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }


                            DV.start();
                        }
                        else{
                            System.out.println("동작 불가능");
                        }
                    }
                };
                TextPageThread8.start();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println(">>>>> 스레드 종료 <<<<<");
        LDT02.interrupt();
        finish();
    }

    //※※※※※※※※※※※※※※※※※※※※※※※※
    class LoadListThread extends Thread{
        public void run() {
            try {
                // 서버 소켓 접속.
                Socket clientSocket = new Socket("203.244.145.81", 3000);
                System.out.println("서버접속 완료");

                String StrSwitch = "VideoPage";
                OutputStream os = clientSocket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeUTF(StrSwitch);

                System.out.println("※ 서버에 전송한 메세지 : " + StrSwitch);

                // 파일 수신하기.
                InputStream is = clientSocket.getInputStream(); // java.net.SocketInputStream@fb17af1
                DataInputStream dis = new DataInputStream(is); // java.io.DataInputStream@96cf6d6

                // 파일 저장하기.
                File FileList = new File("/data/data/com.example.BJmonitoring/TestFolder/FileList.txt"); ///storage/1F0E-2C08/Download/DownloadTest/

                //System.out.println("동작확인 file :" + file ); //   /data/data/com.example.Client1205/TestFolder/Data.txt


                FileOutputStream fos = new FileOutputStream(FileList);//     java.io.FileOutputStream@fa016cf
                //System.out.println("동작확인 fos :" + fos);

                //BufferedOutputStream bos = new BufferedOutputStream(fos);
                //System.out.println("동작확인 bos :" + bos);


                // Take byte buffer setting.
                byte[] buf = new byte[1024];
                // System.out.println("동작확인 buf :" + buf);
                int readbyte;

                while((readbyte = clientSocket.getInputStream().read(buf)) != -1){
                    fos.write(buf,0,readbyte);
                    //System.out.println("동작확인 readbyte :" + readbyte);
                }

                //System.out.println("다운로드 완료");


                //초기화
                dos.close();
                dis.close();
                clientSocket.close();
                //System.out.println("초기화 완료");


                ShowText= (TextView) findViewById(R.id.textViewer);

                // Read File
                Scanner scanner = new Scanner(FileList);
                ShowText.setText(null);

                while (scanner.hasNext()) {
                    Thread.sleep(50); //<<<<<<<<<<<<<<<<<<<<<<<<<<
                    VideoListNum++;
                    ShowText.append(scanner.nextLine() + "\n");
                }
                //System.out.println("(출력 완료)");

                System.out.println(">>>>>>>>>>>>>>>> VideoListNum :" + VideoListNum);


            } catch (Exception e) {
                System.out.println("※ 어디선가 오류 발생.");
                //e.printStackTrace();
            }
        }
    }

    //※※※※※※※※※※※※※※※※※※※※※※※※
    class DownloadVideo extends Thread{
        public void run() {
            try {
                // 서버 소켓 접속.
                Socket clientSocket = new Socket("203.244.145.81", 3000);
                System.out.println("서버접속 완료");

                OutputStream os = clientSocket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeUTF(VideoListName);

                System.out.println("※ 서버에 전송한 메세지 : " + VideoListName);


                // 파일 수신하기.
                InputStream is = clientSocket.getInputStream();
                DataInputStream dis = new DataInputStream(is);

                // 파일 저장하기.
                File file = new File("/data/data/com.example.BJmonitoring/TestFolder/"+ VideoListName); ///storage/1F0E-2C08/Download/DownloadTest/


                //System.out.println("동작확인 file :" + file ); //   /data/data/com.example.Client1205/TestFolder/Data.txt


                FileOutputStream fos = new FileOutputStream(file);//     java.io.FileOutputStream@fa016cf
                //System.out.println("동작확인 fos :" + fos);

                //BufferedOutputStream bos = new BufferedOutputStream(fos);
                //System.out.println("동작확인 bos :" + bos);


                // Take byte buffer setting.
                byte[] buf = new byte[1024];
                // System.out.println("동작확인 buf :" + buf);
                int readbyte;

                while((readbyte = clientSocket.getInputStream().read(buf)) != -1){
                    fos.write(buf,0,readbyte);


                    DownloadData = DownloadData + readbyte;
                    System.out.println("다운 로드중 : "+ DownloadData + " [byte]");

                }

                System.out.println("다운로드 완료");


                Intent intent = new Intent(getApplicationContext(), VideoPlayer.class);
                intent.putExtra("VideoListName2",VideoListName);
                startActivity(intent);




                //초기화
                dos.close();
                dis.close();
                clientSocket.close();
                //System.out.println("초기화 완료");

/*
                ShowText= (TextView) findViewById(R.id.textViewer);

                // Read File
                Scanner scanner = new Scanner(file);
                ShowText.setText(null);

                while (scanner.hasNext()) {
                    VideoListNum++;
                    ShowText.append(scanner.nextLine() + "\n");
                }
                //System.out.println("(출력 완료)");

                System.out.println(">>>>>>>>>>>>>>>> VideoListNum :" + VideoListNum);
                */


            } catch (Exception e) {
                System.out.println("※ 어디선가 오류 발생.");
                //e.printStackTrace();
            }
        }
    }



}