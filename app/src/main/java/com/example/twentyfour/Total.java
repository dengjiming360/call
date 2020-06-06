package com.example.twentyfour;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.net.Uri;
import com.example.ttsreader.TTSTool;

public class Total extends Application {
    public static int getStatus() {
        return Status;
    }

    public static void setStatus(int status) {
        Status = status;
    }
    public static int Shunxu=-1;
    public static TTSTool getTtsTool() {
        return ttsTool;
    }
    public static int Status=0;
    public static void setTtsTool(TTSTool ttsTool) {
        Total.ttsTool = ttsTool;
    }
    public static MediaPlayer mediaPlayer2;
    public static TTSTool ttsTool;

    public static boolean isMute() {
        return Mute;
    }

    public static void setMute(boolean mute) {
        Mute = mute;
    }

    public static boolean Mute=false;
    public static MediaPlayer getMediaPlayer2() {
        return mediaPlayer2;
    }

    public static void setMediaPlayer2(MediaPlayer mediaPlayer2) {
        Total.mediaPlayer2 = mediaPlayer2;
    }

    public static int getReset() {
        return Reset;
    }

    public static int getShunxu() {
        return Shunxu;
    }

    public static void setShunxu(int Shunxu) {
        Total.Shunxu = Shunxu;
    }

    public static void setReset(int reset) {
        Reset = reset;
    }

    public static int Reset=0;
    public static MediaPlayer mediaPlayer;

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public static void setMediaPlayer(MediaPlayer mediaPlayer) {
        Total.mediaPlayer = mediaPlayer;
    }

        @Override
        public void onCreate() {
            super.onCreate();
            initActivityLifecycleCallbacks();
        }

        /**
         * 在application里监听所以activity生命周期的回调
         */
        private void initActivityLifecycleCallbacks(){
            registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() { //添加监听
                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    //activity创建生命周期
                    if(activity instanceof MainActivity){ //判断创建的activity对应对象
                         Log.e("司马懿","司马懿");
                    }

                }

                @Override
                public void onActivityStarted(Activity activity) {
                    //activity启动生命周期
                    Log.e("司马师","司马师");
                }

                @Override
                public void onActivityResumed(Activity activity) {
                    //activity恢复生命周期
                    Log.e("司马昭","司马昭");
                }

                @Override
                public void onActivityPaused(Activity activity) {
                    //activity暂停生命周期
                    Log.e("司马炎","司马炎");
                }

                @Override
                public void onActivityStopped(Activity activity) {
                    //activity停止生命周期
                    Log.e("司马睿","司马睿");
                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                    //保存activity实例状态
                    Log.e("司马衷","司马衷");
                }

                @Override
                public void onActivityDestroyed(Activity activity) {
                    //activity销毁生命周期
                    Log.e("司马丕","司马丕");
                }
            });
        }
    }

