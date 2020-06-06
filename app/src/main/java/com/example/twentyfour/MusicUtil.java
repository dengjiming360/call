package com.example.twentyfour;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;


import java.net.URI;


public class MusicUtil{
    private static MediaPlayer mPlayer, mNextPlayer;

    public static MediaPlayer testLoopPlayer(final Context context, final int mPlayResId) {
        if (Total.getStatus() == 0) {
            mPlayer = MediaPlayer.create(context, mPlayResId);
            Log.e("铁穆耳",String.valueOf(mPlayResId)+"#"+Integer.valueOf(R.raw.highscore).equals(mPlayResId)+"#"+Integer.valueOf(mPlayResId).equals(Integer.valueOf(com.example.ttsreader.R.raw.music1)));
            Total.setMediaPlayer(mPlayer);
        }
        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mPlayer.start();
                if (Total.getStatus() == 1) {
                    mPlayer.setVolume(0, 0);
                    Total.setMute(true);
                }
                if(Total.getStatus()==0){
                    mPlayer.setVolume(1, 1);
                    Total.setMute(false);
                }
            }
        });
        createNextMediaPlayer(context, mPlayResId);
        return mPlayer;
    }

    private static void createNextMediaPlayer(final Context context, final int mPlayResId) {
        if(Total.getStatus()!=2&&Total.getStatus()!=3) {
            mNextPlayer = MediaPlayer.create(context, mPlayResId);
            Log.e("忽必烈", String.valueOf(mPlayResId) + "#" + Integer.valueOf(R.raw.highscore).equals(mPlayResId) + "#" + Integer.valueOf(mPlayResId).equals(Integer.valueOf(com.example.ttsreader.R.raw.music1)));

            mPlayer.setNextMediaPlayer(mNextPlayer);
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                    mp.release();
                    mPlayer = mNextPlayer;
                    Total.setMediaPlayer(mNextPlayer);
                    if (Total.getStatus() == 1) {
                        mPlayer.setVolume(0, 0);
                        Total.setMute(true);
                    }
                    if (Total.getStatus() == 0) {
                        mPlayer.setVolume(1, 1);
                        Total.setMute(false);
                    }
                    createNextMediaPlayer(context, mPlayResId);
                }
            });
        }
        }


        public static void Voice() {
            if (Total.getStatus() == 1) {
                try {
                    if (Total.getMediaPlayer() != null) {
                        Total.getMediaPlayer().setVolume(0, 0);
                        Total.setMute(true);
                    }

                } catch (Exception e) {

                }
            } if(Total.getStatus()==0) {
                try {
                    if (Total.getMediaPlayer() != null) {
                        Total.getMediaPlayer().setVolume(1, 1);
                        Total.setMute(false);
                    }

                } catch (Exception e) {
                }
            }
        }
        public static void Stop() {
            if (Total.getStatus() == 2) {
                try {
                    Total.getMediaPlayer().stop();
                    Total.getMediaPlayer().release();
                } catch (Exception e) {
                }
            }
        }
        public static void Restart(Context context, final int mPlayResId){
        if(Total.getStatus()==3) {
            MusicUtil.testLoopPlayer(context,mPlayResId);
        }
        }
}




