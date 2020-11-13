package com.example.BJmonitoring;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoPlayer extends AppCompatActivity {
    VideoView videoView;
    Button btnStart, btnStop;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videopage);

        //레이아웃 위젯 findViewById
        videoView = (VideoView) findViewById(R.id.view);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);

        //미디어컨트롤러 추가하는 부분
        MediaController controller = new MediaController(VideoPlayer.this);
        videoView.setMediaController(controller);

        //비디오뷰 포커스를 요청함
        videoView.requestFocus();


        Intent intent = getIntent();
        String VideoListName =  intent.getStringExtra("VideoListName2");



        System.out.println("전달받을 비디오 이름 : "+ VideoListName);
        String path = "/data/data/com.example.BJmonitoring/TestFolder/"+VideoListName;
        videoView.setVideoPath(String.valueOf(path));


        //동영상이 재생준비가 완료되었을 때를 알 수 있는 리스너 (실제 웹에서 영상을 다운받아 출력할 때 많이 사용됨)
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Toast.makeText(VideoPlayer.this,"동영상이 준비되었습니다. \n'시작' 버튼을 누르세요", Toast.LENGTH_SHORT).show();
            }
        });

        //동영상 재생이 완료된 걸 알 수 있는 리스너
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //동영상 재생이 완료된 후 호출되는 메소드
                Toast.makeText(VideoPlayer.this,"동영상 재생이 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

    }
    //시작 버튼 onClick Method
    public void StartButton(View v) {
        playVideo();
    }

    //정지 버튼 onClick Method
    public void StopButton(View v) {
        stopVideo();
    }

    //동영상 재생 Method
    private void playVideo() {
        //비디오를 처음부터 재생할 때 0으로 시작(파라메터 sec)
        videoView.seekTo(0);
        videoView.start();
    }

    //동영상 정지 Method
    private void stopVideo() {
        //비디오 재생 잠시 멈춤
        videoView.pause();
        //비디오 재생 완전 멈춤
//        videoView.stopPlayback();
        //videoView를 null로 반환 시 동영상의 반복 재생이 불가능
//        videoView = null;
    }

    //※※※※※※※※※※※※※※※※※※※※※※※※
    public void PreButton(View v) {
        finish();
    }

}
