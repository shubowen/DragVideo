package com.example.shubowen.dragvideo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 疏博文 新建于 2017/7/27.
 * 邮箱： shubw@icloud.com
 * 描述：请添加此文件的描述
 */

public class VideoPlayActivity extends AppCompatActivity {

    private VideoView mVideoView;
    private String mUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video_play);

        mVideoView = (VideoView) findViewById(R.id.video_view);

        mUrl = getIntent().getStringExtra("url");
        final int position = getIntent().getIntExtra("position", 0);

        mVideoView.setVideoPath(mUrl);
        mVideoView.seekTo(position);
        mVideoView.start();
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
        if (mVideoView.isPlaying())
            VideoWidowService.go(this, mUrl, mVideoView.getCurrentPosition());
        super.onBackPressed();
    }
}
