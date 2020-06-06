package com.example.twentyfour;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;

public class MusicUtil2 {
    private static MediaPlayer mPlayer,mNextPlayer;
    private  static int mPlayResId= R.raw.highscore;
    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;

    public static void testLoopPlayer(final Context context, final int mPlayResId) {
        if(Total.getStatus()==0) {
            mPlayer = MediaPlayer.create(context, mPlayResId);
            Total.setMediaPlayer2(mPlayer);
        }
        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mPlayer.start();
                if(Total.getStatus()==1){
                    mPlayer.setVolume(0,0);
                }
                else{
                    mPlayer.setVolume(1,1);
                }
            }
        });
        createNextMediaPlayer(context, mPlayResId);
    }

    private static  void createNextMediaPlayer(final Context context, final int mPlayResId){
        mNextPlayer=MediaPlayer.create(context,mPlayResId);
        mPlayer.setNextMediaPlayer(mNextPlayer);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                mp.release();
                mPlayer = mNextPlayer;
                if(Total.getStatus()==1){
                    mPlayer.setVolume(0,0);
                }
                else{
                    mPlayer.setVolume(1,1);
                }
                Total.setMediaPlayer2(mNextPlayer);
                createNextMediaPlayer(context, mPlayResId);
            }
        });
    }
    public static void Voice() {
        if (Total.getStatus() == 1) {
            try {
                if (Total.getMediaPlayer() != null) {
                    Total.getMediaPlayer2().setVolume(0, 0);
                }

            } catch (Exception e) {

            }
        } else {
            try {
                if (Total.getMediaPlayer() != null) {
                    Total.getMediaPlayer2().setVolume(1, 1);
                }

            } catch (Exception e) {
            }
        }
    }
}
