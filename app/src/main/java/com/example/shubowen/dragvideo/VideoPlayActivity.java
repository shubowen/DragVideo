package com.example.shubowen.dragvideo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * 疏博文 新建于 2017/7/27.
 * 邮箱： shubw@icloud.com
 * 描述：请添加此文件的描述
 */

public class VideoPlayActivity extends AppCompatActivity {

    private SurfaceView mSurfaceView;
    private String mUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video_play);

        mSurfaceView = (SurfaceView) findViewById(R.id.video_view);

        mUrl = getIntent().getStringExtra("url");
        final int position = getIntent().getIntExtra("position", 0);

        try {
            AssetFileDescriptor afd = getAssets().openFd("test.mp4");
            VideoPlayer.get().setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }

        mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                VideoPlayer.get().setDisplay(holder);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                VideoPlayer.get().start();
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
        mSurfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public static void go(Activity activity, String url, int position) {
        //先停止小窗口视频
        VideoWidowService.stop(activity);

        Intent intent = new Intent(activity, VideoPlayActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("position", position);
        activity.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (VideoPlayer.get().getMediaPlayer().isPlaying()) {
            int currentPosition = VideoPlayer.get().getMediaPlayer().getCurrentPosition();
            VideoWidowService.go(this, mUrl, currentPosition);
        }
        super.onBackPressed();
    }
}
