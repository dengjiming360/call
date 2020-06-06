package com.example.twentyfour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.example.ttsreader.TTSTool;

import java.util.ArrayList;
import java.util.Arrays;

public class Main6Activity extends AppCompatActivity {
SharedPreferences sharedPreferences;
boolean IsTTSOpen=true;
boolean bgm=true;
TTSTool ttsTool;
boolean flag=true;
boolean shift=false;
LinearLayout linear;
JianbianTv jianbianTv;
boolean end=false;
int so=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        root();
        setContentView(R.layout.activity_main6);
         ttsTool=Total.getTtsTool();
        linear=findViewById(R.id.lin3);
        jianbianTv=findViewById(R.id.jb);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "STHUPO.TTF");
        jianbianTv.setTypeface(typeface);
        MusicUtil2.testLoopPlayer(this,R.raw.highscore);
        sharedPreferences=getSharedPreferences("status",MODE_PRIVATE);
        IsTTSOpen=sharedPreferences.getBoolean("tts",true);
        bgm=sharedPreferences.getBoolean("bgm",true);
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shift==true){

                    Intent intent=new Intent(Main6Activity.this,Main2Activity.class);
                    ttsTool.stop();
                    try {
                        MediaPlayer mp = Total.getMediaPlayer2();
                        mp.stop();
                        mp.release();
                        mp = null;
                        Total.setMediaPlayer2(null);
                    }
                    catch(Exception e){

                    }
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus==true){
            so++;
            flag=true;
            ttsTool=Total.getTtsTool();
            sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);
            IsTTSOpen = sharedPreferences.getBoolean("tts", true);
            if(IsTTSOpen==true&&shift==false&&so==1) {
                ttsTool.speak("恭喜爱动脑筋的你，你在本次游戏获得了最高分，打破了之前的记录。加油哦，开动脑筋，再接再厉，争取更上一层楼，得到更高的分数。", Main6Activity.this);
            }
            sharedPreferences=getSharedPreferences("status",MODE_PRIVATE);
            IsTTSOpen=sharedPreferences.getBoolean("tts",true);
            bgm=sharedPreferences.getBoolean("bgm",true);
            if(bgm==true){
                if(Total.getStatus()!=0){
                    try {
                        if(Total.getStatus()!=0){
                            Total.setStatus(0);
                        }
                    }
                    catch(Exception e){

                    }
                }
            }
            if(bgm==false){
                if(Total.getStatus()!=1){
                    try {
                        if(Total.getStatus()!=1){
                            Total.setStatus(1);
                        }
                    }
                    catch(Exception e){

                    }
                }
            }
            if(shift==false&&so==1) {
                final AlertDialog.Builder alertbBuilder = new AlertDialog.Builder(this);
                alertbBuilder.setCancelable(false);
                alertbBuilder.setTitle("恭喜获得最高分").setMessage("恭喜爱动脑筋的你，你在本次游戏获得了最高分，打破了之前的记录。加油哦，开动脑筋，再接再厉，争取更上一层楼，得到更高的分数。").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                            Intent intent = new Intent(Main6Activity.this, Main2Activity.class);
                            ttsTool.stop();
                        try {
                            MediaPlayer mp = Total.getMediaPlayer2();
                            mp.stop();
                            mp.release();
                            mp = null;
                            Total.setMediaPlayer2(null);
                        }
                        catch(Exception e){

                        }
                        startActivity(intent);


                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shift = true;
                        dialog.dismiss();
                    }
                }).create();
                if (flag == true) {
                    alertbBuilder.show();
                }
            }
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        AudioManager audioManager  = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if(keyCode==KeyEvent.KEYCODE_BACK){
           Intent intent=new Intent(Main6Activity.this,Main2Activity.class);
           ttsTool.stop();
           try {
               MediaPlayer mp = Total.getMediaPlayer2();
               mp.stop();
               mp.release();
               mp = null;
               Total.setMediaPlayer2(null);
           }
           catch(Exception e){

           }
           startActivity(intent);
        }
        if(keyCode==KeyEvent.KEYCODE_VOLUME_UP) {
            audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FX_FOCUS_NAVIGATION_UP);
            return true;
        }
        if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN) {
            audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FX_FOCUS_NAVIGATION_UP);
            return true;
        }
        return true;
    }
    @Override
    protected void onPause() {
        super.onPause();
        flag=false;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public void root() {
        if (!isTaskRoot()) {
            final Intent intent = getIntent();
            final String intentAction = intent.getAction();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && intentAction != null && intentAction.equals(Intent.ACTION_MAIN)) {
                finish();
                return;
            }
        }
    }
}
