package com.example.twentyfour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Service;
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
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ttsreader.TTSTool;

import java.util.ArrayList;
import java.util.Arrays;

public class Main2Activity extends AppCompatActivity {
TextView tv1;
ImageButton mus,vol;

Button begin;
boolean mute=false;
String gameover="";
long click=0;
long click2=0;
boolean IsTTSOpen=true;
TTSTool ttsTool;
    boolean init=false;
boolean isInit=false;
boolean backgroundmusic=true;
SharedPreferences sharedPreferences;
SharedPreferences sharedPreferences2;
SharedPreferences.Editor editor;
SharedPreferences.Editor editor2;
Button exit,highscore;
Intent intent2;
boolean flag=true;
boolean startG=false;
ImageButton choosemusic;
boolean signal=false;
Intent intent4;
int musiccixu=-1;
boolean cope=false;
    int[] Cmusics={com.example.ttsreader.R.raw.music1,com.example.ttsreader.R.raw.music2,com.example.ttsreader.R.raw.music3,com.example.ttsreader.R.raw.music4,
            com.example.ttsreader.R.raw.music5,com.example.ttsreader.R.raw.music6,com.example.ttsreader.R.raw.music7,com.example.ttsreader.R.raw.music8,
            com.example.ttsreader.R.raw.music9,com.example.ttsreader.R.raw.music10,com.example.ttsreader.R.raw.music11,com.example.ttsreader.R.raw.music12,
            com.example.ttsreader.R.raw.music13};
boolean inital2=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        root();
        setContentView(R.layout.activity_main2);
        ttsTool=new TTSTool(Main2Activity.this);
        ttsTool.init(Main2Activity.this);
        Total.setTtsTool(ttsTool);

        mus=findViewById(R.id.mus);
        vol=findViewById(R.id.vol);
        choosemusic=findViewById(R.id.choosemusic);
        sharedPreferences2=getSharedPreferences("songs",MODE_PRIVATE);
        musiccixu=sharedPreferences2.getInt("cixu",0);
        cope=sharedPreferences2.getBoolean("cope",false);
        Log.e("桓温",String.valueOf(signal));
        sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);
        signal=sharedPreferences.getBoolean("task",false);
        if(signal==false) {
            MusicUtil.testLoopPlayer(getApplicationContext(), Cmusics[musiccixu]);
        }
        if(signal==true&&cope==true) {
            MusicUtil.testLoopPlayer(getApplicationContext(), Cmusics[musiccixu]);
        }
        if(startG==false) {
            gameover="";
            backgroundmusic=true;
            IsTTSOpen=true;
            mute=false;
        }
        if(startG==true){
            sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);
            gameover = sharedPreferences.getString("gameover", "");
            backgroundmusic = sharedPreferences.getBoolean("bgm", true);
            IsTTSOpen = sharedPreferences.getBoolean("tts", true);
        }
        if(gameover.equals("gameover")){
            sharedPreferences=getSharedPreferences("status",MODE_PRIVATE);
            IsTTSOpen=sharedPreferences.getBoolean("tts",true);
            backgroundmusic=sharedPreferences.getBoolean("bgm",true);
            if(IsTTSOpen==false){
                click2=1;
                vol.setBackgroundResource(R.drawable.novolume);
            }
            if(IsTTSOpen==true){
                click2=0;
                vol.setBackgroundResource(R.drawable.volume);
            }
            if(backgroundmusic==true) {
                mute = false;
                click = 1;
                try {
                    if (Total.getStatus() != 0) {
                        Total.setStatus(0);
                        Total.setMute(false);
                    }
                }
                catch(Exception e){

                }
            }
            if(backgroundmusic==false){
                mute=true;
                click=0;
                try {
                    if (Total.getStatus() != 1) {
                        Total.setStatus(1);
                        Total.setMute(true);
                    }
                }
                catch(Exception e){

                }
            }
            if(mute==false){
                mus.setBackgroundResource(R.drawable.music);
            }
            if(mute==true){
                mus.setBackgroundResource(R.drawable.nomusic);
            }
        }
        tv1 = (TextView) findViewById(R.id.tv1);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "STHUPO.TTF");
        tv1.setTypeface(typeface);
        begin=findViewById(R.id.begin);
        exit=findViewById(R.id.exit);
        highscore=findViewById(R.id.highscore);
        sharedPreferences=getSharedPreferences("status",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putString("gameover","");
        editor.commit();
        sharedPreferences2=getSharedPreferences("songs",MODE_PRIVATE);
       editor=sharedPreferences2.edit();
       editor.putBoolean("cope",false);
       editor.commit();
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences=getSharedPreferences("status",MODE_PRIVATE);
                IsTTSOpen=sharedPreferences.getBoolean("tts",true);
                sharedPreferences=getSharedPreferences("status",MODE_PRIVATE);
                editor=sharedPreferences.edit();
                editor.putString("gameover","");
                editor.commit();
                Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
       highscore.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(Main2Activity.this,Main3Activity.class);

               startActivity(intent);
           }
       });
       choosemusic.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(Main2Activity.this,Main5Activity.class);

               startActivity(intent);
           }
       });
       exit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                   AlertDialog.Builder alertbBuilder=new AlertDialog.Builder(Main2Activity.this);
                    alertbBuilder.setCancelable(false);
                   alertbBuilder.setTitle("真的要离开？").setMessage("你确定要离开24点游戏？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           ttsTool=Total.getTtsTool();
                           ttsTool.release();
                           try {
                               MediaPlayer mp=Total.getMediaPlayer();
                               mp.stop();
                               mp.release();
                               mp=null;
                               Total.setMediaPlayer(null);
                           }
                           catch(Exception e){

                           }
                           Intent startMain = new Intent(Intent.ACTION_MAIN);
                           startMain.addCategory(Intent.CATEGORY_HOME);
                           startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                           startActivity(startMain);
                           finish();
                           System.exit(0);
                       }
                   }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           dialog.cancel();
                       }
                   }).create();
               if(flag==true) {
                   alertbBuilder.show();
               }
               }
       });
       vol.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               click2++;
               if (init == false) {
                   sharedPreferences=getSharedPreferences("status",MODE_PRIVATE);
                   IsTTSOpen=sharedPreferences.getBoolean("tts",true);
                   init=true;
               }


               if(IsTTSOpen==true&&click2%2==1) {
                   sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);
                   editor=sharedPreferences.edit();
                   editor.putBoolean("tts",false);
                   editor.commit();
               }
               if(IsTTSOpen==false&&click2%2==0){
                   sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);
                   editor=sharedPreferences.edit();
                   editor.putBoolean("tts",true);
                   editor.commit();
                   click2=0;
               }
               sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);
               IsTTSOpen=sharedPreferences.getBoolean("tts",true);

               if(IsTTSOpen==true){
                   vol.setBackgroundResource(R.drawable.volume);
               }
               if(IsTTSOpen==false){
                   vol.setBackgroundResource(R.drawable.novolume);
               }
           }
       });
        mus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click++;
                if(mute==false&&click%2==1){
                    sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);
                    editor=sharedPreferences.edit();
                    editor.putBoolean("bgm",false);
                    editor.commit();
                        try {
                            if (Total.getStatus() != 1) {
                                Total.setStatus(1);
                                Total.setMute(true);
                            }
                        }
                        catch(Exception e){

                        }
                    sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);
                    backgroundmusic=sharedPreferences.getBoolean("bgm",true);
                    if(backgroundmusic==false){
                        mute=true;
                        MusicUtil.Voice();
                    }
                }
                if(mute==true&&click%2==0){
                    sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);
                    editor=sharedPreferences.edit();
                    editor.putBoolean("bgm",true);
                    editor.commit();
                    try {
                        if (Total.getStatus() != 0) {
                            Total.setStatus(0);
                            Total.setMute(false);
                        }
                    }
                    catch(Exception e){

                    }
                    sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);
                    backgroundmusic=sharedPreferences.getBoolean("bgm",true);
                    if(backgroundmusic==true){
                        mute=false;
                        MusicUtil.Voice();
                    }
                    click=0;
                }
                if(mute==false){
                    mus.setBackgroundResource(R.drawable.music);
                }
                if(mute==true){
                    mus.setBackgroundResource(R.drawable.nomusic);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            MediaPlayer mp = Total.getMediaPlayer();
            mp.stop();
            mp.release();
            mp = null;
            Total.setMediaPlayer(null);
        }
        catch(Exception e){

        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        AudioManager audioManager  = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if(keyCode==KeyEvent.KEYCODE_BACK){

           AlertDialog.Builder alertbBuilder=new AlertDialog.Builder(Main2Activity.this);
           alertbBuilder.setCancelable(false);
            alertbBuilder.setTitle("真的要离开？").setMessage("你确定要离开24点游戏？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ttsTool=Total.getTtsTool();
                    ttsTool.release();
                    try {
                        MediaPlayer mp=Total.getMediaPlayer();
                        mp.stop();
                        mp.release();
                        mp=null;
                        Total.setMediaPlayer(null);
                    }
                    catch(Exception e){

                    }
                    Intent startMain = new Intent(Intent.ACTION_MAIN);
                    startMain.addCategory(Intent.CATEGORY_HOME);
                    startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(startMain);
                    finish();
                    System.exit(0);
                }
            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            }).create();
            if(flag==true) {
                alertbBuilder.show();
            }
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
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus==true){
           flag=true;
            ttsTool=Total.getTtsTool();
            if(inital2==false){
                sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);
                gameover = sharedPreferences.getString("gameover", "");
                backgroundmusic = sharedPreferences.getBoolean("bgm", true);
                IsTTSOpen = sharedPreferences.getBoolean("tts", true);
                if(IsTTSOpen==false){
                    click2=1;
                    vol.setBackgroundResource(R.drawable.novolume);
                }
                if(IsTTSOpen==true){
                    click2=0;
                    vol.setBackgroundResource(R.drawable.volume);
                }
                if(backgroundmusic==true){
                    mute=false;
                    click=0;
                    try {
                        if(Total.getStatus()!=0){
                            Total.setStatus(0);
                            Total.setMute(true);
                        }
                    }
                    catch(Exception e){

                    }
                }
                if(backgroundmusic==false){
                    mute=true;
                    click=1;
                    try {
                        if(Total.getStatus()!=1){
                            Total.setStatus(1);
                            Total.setMute(false);
                        }
                    }
                    catch(Exception e){

                    }
                }
                if(mute==false){
                    mus.setBackgroundResource(R.drawable.music);
                }
                if(mute==true){
                    mus.setBackgroundResource(R.drawable.nomusic);
                }
            }
            intent4=getIntent();
            inital2=intent4.getBooleanExtra("init",false);
            signal=intent4.getBooleanExtra("signal",false);
            sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);
            editor=sharedPreferences.edit();
            editor.putBoolean("task",true);
            editor.commit();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        flag=false;
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

