package com.android.qz.android_video_player;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.view.View;
import android.media.AudioManager;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    AudioManager audioManager;

    public void playAudio(View view) {
        mediaPlayer.start();
    }

    public void pauseAudio(View view) {
        mediaPlayer.pause();
    }

    public void replayAudio(View view) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this,R.raw.hymn340);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolumn = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVolumn = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);


        final SeekBar timeControl = (SeekBar) findViewById(R.id.timeSeekBar);
        timeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        timeControl.setMax(mediaPlayer.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeControl.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0,10);



        SeekBar volumnControl = (SeekBar) findViewById(R.id.volumnSeekBar);
        volumnControl.setMax(maxVolumn);
        volumnControl.setProgress(curVolumn);
        volumnControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
/*
        VideoView videoView = (VideoView) findViewById(R.id.videoView);

        videoView.setVideoPath("android.resource://" + getPackageName()+ R.raw.hymn340);

        MediaController mediaController = new MediaController(this);

        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);

        videoView.start();
        */

    }
}
