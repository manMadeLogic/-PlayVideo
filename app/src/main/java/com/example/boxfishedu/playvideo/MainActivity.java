package com.example.boxfishedu.playvideo;

import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback{
    String path = "/sdcard0/bbb.3gp";
    Button play_Button;
    Button pause_Button;
    boolean isPause = false;
    SurfaceHolder surfaceHolder;
    MediaPlayer mediaPlayer;
    SurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play_Button = (Button) findViewById(R.id.play2_Button);
        play_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPause = false;
                playVideo(path);
            }
        });
        pause_Button = (Button) findViewById(R.id.pause2_Button);
        pause_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPause) {
                    mediaPlayer.start();
                    isPause = false;
                } else {
                    mediaPlayer.pause();
                    isPause = true;
                }
            }
        });
        getWindow().setFormat(PixelFormat.UNKNOWN);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setFixedSize(176,144);
//        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        mediaPlayer = new MediaPlayer();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    private void playVideo(String strPath){//自定义播放影片函数
        if(mediaPlayer.isPlaying()){
            mediaPlayer.reset();
        }
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDisplay(surfaceHolder);//设置Video影片以SurfaceHolder播放
        try{
            mediaPlayer.setDataSource(strPath);
            mediaPlayer.prepare();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        mediaPlayer.start();
    }
}
