package com.example.shubowen.dragvideo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.SurfaceHolder;

import java.io.FileDescriptor;
import java.util.Map;

/**
 * 疏博文 新建于 2018/6/11.
 * 邮箱： shubw@icloud.com
 * 描述：请添加此文件的描述
 */
public class VideoPlayer {

    private static VideoPlayer sPlayer;

    private MediaPlayer mMediaPlayer = null;
    private SurfaceHolder mSurfaceHolder = null;

    private VideoPlayer() {
    }

    public static VideoPlayer get() {
        synchronized (VideoPlayer.class) {
            if (null == sPlayer) {
                sPlayer = new VideoPlayer();
            }
        }
        return sPlayer;
    }

    public void setVideoURI(Context ctx, Uri uri) {
        setVideoURI(ctx, uri, null);
    }

    public void setVideoURI(Context ctx, Uri uri, Map<String, String> headers) {
        /*AudioManager am = (AudioManager) ctx.getSystemService(Context.AUDIO_SERVICE);
        am.requestAudioFocus(null, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);*/

        if (null == mMediaPlayer) {
            mMediaPlayer = new MediaPlayer();
        }

        try {
            /*if (mAudioSession != 0) {
                mMediaPlayer.setAudioSessionId(mAudioSession);
            } else {
                mAudioSession = mMediaPlayer.getAudioSessionId();
            }*/
            /*mMediaPlayer.setOnPreparedListener(mPreparedListener);
            mMediaPlayer.setOnVideoSizeChangedListener(mSizeChangedListener);
            mMediaPlayer.setOnCompletionListener(mCompletionListener);
            mMediaPlayer.setOnErrorListener(mErrorListener);
            mMediaPlayer.setOnInfoListener(mInfoListener);
            mMediaPlayer.setOnBufferingUpdateListener(mBufferingUpdateListener);*/
            mMediaPlayer.setDataSource(ctx, uri, headers);
            if (null != mSurfaceHolder) {
                mMediaPlayer.setDisplay(mSurfaceHolder);
            }
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setScreenOnWhilePlaying(true);
            mMediaPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setDataSource(FileDescriptor fd, long offset, long length) {
        if (null == mMediaPlayer) {
            mMediaPlayer = new MediaPlayer();
        }

        try {
            /*if (mAudioSession != 0) {
                mMediaPlayer.setAudioSessionId(mAudioSession);
            } else {
                mAudioSession = mMediaPlayer.getAudioSessionId();
            }*/
            /*mMediaPlayer.setOnPreparedListener(mPreparedListener);
            mMediaPlayer.setOnVideoSizeChangedListener(mSizeChangedListener);
            mMediaPlayer.setOnCompletionListener(mCompletionListener);
            mMediaPlayer.setOnErrorListener(mErrorListener);
            mMediaPlayer.setOnInfoListener(mInfoListener);
            mMediaPlayer.setOnBufferingUpdateListener(mBufferingUpdateListener);*/
            mMediaPlayer.setDataSource(fd, offset, length);
            if (null != mSurfaceHolder) {
                mMediaPlayer.setDisplay(mSurfaceHolder);
                mMediaPlayer.setScreenOnWhilePlaying(true);
            }
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDisplay(SurfaceHolder sh) {
        mSurfaceHolder = sh;
        if (null != mSurfaceHolder && null != mMediaPlayer) {
            mMediaPlayer.setDisplay(mSurfaceHolder);
            mMediaPlayer.setScreenOnWhilePlaying(true);
        }
    }

    public void start() {
        if (null != mMediaPlayer) {
            mMediaPlayer.start();
        }
    }

    public MediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }

}
