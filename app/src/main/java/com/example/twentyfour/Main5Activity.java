package com.example.twentyfour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;

import static com.example.twentyfour.MusicUtil.Stop;

public class Main5Activity extends AppCompatActivity {
RecyclerView rcv1;
ArrayList<String> bgms=new ArrayList<String>();
MyAdapter1 myAdapter1;
LinearLayoutManager lgm;
    SharedPreferences sharedPreferences2;
    SharedPreferences.Editor editor2;
SharedPreferences sharedPreferences;
SharedPreferences.Editor editor;
boolean cope=false;
boolean reset2=false;
int[] mus={com.example.ttsreader.R.raw.music1,com.example.ttsreader.R.raw.music2,com.example.ttsreader.R.raw.music3,com.example.ttsreader.R.raw.music4,
        com.example.ttsreader.R.raw.music5,com.example.ttsreader.R.raw.music6,com.example.ttsreader.R.raw.music7,com.example.ttsreader.R.raw.music8,
        com.example.ttsreader.R.raw.music9,com.example.ttsreader.R.raw.music10,com.example.ttsreader.R.raw.music11,com.example.ttsreader.R.raw.music12,
        com.example.ttsreader.R.raw.music13};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        root();
        setContentView(R.layout.activity_main5);
        rcv1=findViewById(R.id.rcv1);
        bgms.add("背景音乐1");
        bgms.add("背景音乐2");
        bgms.add("背景音乐3");
        bgms.add("背景音乐4");
        bgms.add("背景音乐5");
        bgms.add("背景音乐6");
        bgms.add("背景音乐7");
        bgms.add("背景音乐8");
        bgms.add("背景音乐9");
        bgms.add("背景音乐10");
        bgms.add("背景音乐11");
        bgms.add("背景音乐12");
        bgms.add("背景音乐13");
        lgm=new LinearLayoutManager(this);
        rcv1.setLayoutManager(lgm);
        myAdapter1=new MyAdapter1(bgms);
        rcv1.setAdapter(myAdapter1);
        rcv1.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        myAdapter1.setOnMyClickListener(new MyAdapter1.MyClickListener(){

            @Override
            public void click(View v, int pos) {
               if(pos<mus.length) {
                   sharedPreferences=getSharedPreferences("songs",MODE_PRIVATE);
                   editor=sharedPreferences.edit();
                   editor.putInt("cixu",pos);
                   editor.putBoolean("reset",true);
                   editor.commit();
                   sharedPreferences2=Main5Activity.this.getSharedPreferences("songs", Context.MODE_PRIVATE);
                   reset2= sharedPreferences2.getBoolean("reset", false);
                   cope=sharedPreferences2.getBoolean("cope",false);
                   Log.e("曹仁",mus[pos]+"#"+reset2+"#"+pos+"#"+Integer.valueOf(mus[pos]).equals(Integer.valueOf(R.raw.highscore)));
                   if(reset2==true){
                       try {
                           Total.setStatus(2);
                           MusicUtil.Stop();
                           if(Total.isMute()==true){
                               Total.setStatus(1);
                           }
                           if(Total.isMute()==false){
                               Total.setStatus(0);
                           }
                       }
                       catch (Exception e){

                       }
                   }
                   sharedPreferences2=Main5Activity.this.getSharedPreferences("songs", Context.MODE_PRIVATE);
                   editor2=sharedPreferences2.edit();
                   editor2.putBoolean("reset",false);
                   editor2.putBoolean("cope",true);
                   editor2.commit();
                   Intent intent = new Intent(Main5Activity.this, Main2Activity.class);
                   startActivity(intent);
               }


            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        AudioManager audioManager  = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if(keyCode==KeyEvent.KEYCODE_BACK){
            Intent intent=new Intent(Main5Activity.this,Main2Activity.class);

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
