package com.example.BJmonitoring;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button GoToPage1;
    Button GoToPage2;
    Button GoToPage3;
    Button GoToWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoToPage1 = (Button) findViewById(R.id.page1);
        GoToPage2 = (Button) findViewById(R.id.page2);
        GoToPage3 = (Button) findViewById(R.id.page3);
        GoToWeb = (Button) findViewById(R.id.web_page);


        // ※※※※※※※※※※※※
        // Text Page.
        GoToPage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread TextPageThread = new Thread() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), Sub2Activity.class);
                        startActivity(intent);
                    }
                };
                TextPageThread.start();
            }
        });

        // ※※※※※※※※※※※※
        // Picture Page.
        GoToPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread PicturePageThread = new Thread() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), Sub1Activity.class);
                        startActivity(intent);
                    }
                };
                PicturePageThread.start();
            }
        });

        // ※※※※※※※※※※※※
        // Video Page.

        GoToPage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread VideoPageThread = new Thread() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), Sub4Activity.class);
                        startActivity(intent);
                    }
                };
                VideoPageThread.start();
            }
        });

        GoToWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("")); // Uri.parse 내부에 CCTV 링크 입력
                startActivity(intent);
            }
        });
    }



    // 비밀번호 변경
    public void changebtn(View v) {
        Intent intent = new Intent(getApplicationContext(), CreatePasswordActivity.class);
        startActivity(intent);
    }


}

