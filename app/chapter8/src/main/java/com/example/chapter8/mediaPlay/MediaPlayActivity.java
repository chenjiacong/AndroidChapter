package com.example.chapter8.mediaPlay;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.chapter8.R;

import java.io.File;
import java.io.IOException;

public class MediaPlayActivity extends AppCompatActivity implements View.OnClickListener {

    private Button play;
    private Button stop;
    private Button pause;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private Button playVideo;
    private Button pauseVideo;
    private Button replay;
    private VideoView video_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_play);
        initView();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        } else {
            initMediaPlay();
            initVideoPath();
        }
    }

    private void initVideoPath() {
        File file=new File(Environment.getExternalStorageDirectory(),"movie.mp4");
        video_view.setVideoPath(file.getPath());
    }

    private void initMediaPlay() {
        File file = new File(Environment.getExternalStorageDirectory(), "music.mp3");
        try {
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResult) {
        switch (requestCode) {
            case 1:
                if (grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                    initMediaPlay();
                    initVideoPath();
                } else {
                    Toast.makeText(this, "请授权，否则无法使用", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

    }

    private void initView() {
        play = (Button) findViewById(R.id.play);
        stop = (Button) findViewById(R.id.stop);
        pause = (Button) findViewById(R.id.pause);

        play.setOnClickListener(this);
        stop.setOnClickListener(this);
        pause.setOnClickListener(this);
        playVideo = (Button) findViewById(R.id.playVideo);
        playVideo.setOnClickListener(this);
        pauseVideo = (Button) findViewById(R.id.pauseVideo);
        pauseVideo.setOnClickListener(this);
        replay = (Button) findViewById(R.id.replay);
        replay.setOnClickListener(this);
        video_view = (VideoView) findViewById(R.id.video_view);
        video_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.play) {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        } else if (id == R.id.stop) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.reset();
                initMediaPlay();
            }
        } else if (id == R.id.pause) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        }else if (id==R.id.playVideo){
            if (!video_view.isPlaying()){
                video_view.start();
            }
        }else if (id==R.id.pauseVideo){
            if (video_view.isPlaying()){
                video_view.pause();
            }
        }else  if (id==R.id.replay){
            if (video_view.isPlaying()){
                video_view.resume();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        if (video_view!=null){
            video_view.suspend();
        }
    }
}