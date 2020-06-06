package com.example.twentyfour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.ttsreader.TTSTool;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    TextView one,two,three,four,five,six,seven,eight,nine,ten,eleven,twelve;
    Button sure,reset;
    int cou=-1;
    int ind=-1;
    String ao2,ao;
    int cursion2=-1;
    long lo;
    String inf;
    public static final String RETURN_INFO = "info";
    public final String TABLENAME = "score3";
    String[] a;
    boolean IsTTSOpen=true;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    double datacount;
    Access dbHelper;
    SQLiteDatabase db;
    ArrayList<String>alldatas=new ArrayList<String>();
   TTSTool ttsTool;

    @Override
        protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        root();
        setContentView(R.layout.activity_main3);
        sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);
        datacount = StroreDoubleUtil.getDouble(sharedPreferences, "datanum", 0);
        ttsTool=Total.getTtsTool();
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        ten = findViewById(R.id.ten);
        eleven = findViewById(R.id.eleven);
        twelve = findViewById(R.id.twelve);
        sure = findViewById(R.id.sure);
        reset = findViewById(R.id.reset);
        dbHelper = new Access(Main3Activity.this);
        db = dbHelper.getWritableDatabase();
        alldatas = DataBaseUtil.query(dbHelper, db);
        if (datacount <= 12) {
            for (int i = 0; i < alldatas.size(); i++) {
                if (i == alldatas.size()) {
                    break;
                }
                if (i == 0) {
                    one.setText(alldatas.get(0));
                }
                if (i == 1) {
                    two.setText(alldatas.get(1));
                }
                if (i == 2) {
                    three.setText(alldatas.get(2));
                }
                if (i == 3) {
                    four.setText(alldatas.get(3));
                }
                if (i == 4) {
                    five.setText(alldatas.get(4));
                }
                if (i == 5) {
                    six.setText(alldatas.get(5));
                }
                if (i == 6) {
                    seven.setText(alldatas.get(6));
                }
                if (i == 7) {
                    eight.setText(alldatas.get(7));
                }
                if (i == 8) {
                    nine.setText(alldatas.get(8));
                }
                if (i == 9) {
                    ten.setText(alldatas.get(9));
                }
                if (i == 10) {
                    eleven.setText(alldatas.get(10));
                }
                if (i == 11) {
                    twelve.setText(alldatas.get(11));
                }
            }
            for (int i = alldatas.size(); i < 12; i++) {
                if (i == 0) {
                    one.setText("0");
                }
                if (i == 1) {
                    two.setText("0");
                }
                if (i == 2) {
                    three.setText("0");
                }
                if (i == 3) {
                    four.setText("0");
                }
                if (i == 4) {
                    five.setText("0");
                }
                if (i == 5) {
                    six.setText("0");
                }
                if (i == 6) {
                    seven.setText("0");
                }
                if (i == 7) {
                    eight.setText("0");
                }
                if (i == 8) {
                    nine.setText("0");
                }
                if (i == 9) {
                    ten.setText("0");
                }
                if (i == 10) {
                    eleven.setText("0");
                }
                if (i == 11) {
                    twelve.setText("0");
                }
            }
        }
        if (datacount > 12) {
            for (int i = 0; i < 12; i++) {
                if (i == alldatas.size()) {
                    break;
                }
                if (i == 0) {
                    one.setText(alldatas.get(0));
                }
                if (i == 1) {
                    two.setText(alldatas.get(1));
                }
                if (i == 2) {
                    three.setText(alldatas.get(2));
                }
                if (i == 3) {
                    four.setText(alldatas.get(3));
                }
                if (i == 4) {
                    five.setText(alldatas.get(4));
                }
                if (i == 5) {
                    six.setText(alldatas.get(5));
                }
                if (i == 6) {
                    seven.setText(alldatas.get(6));
                }
                if (i == 7) {
                    eight.setText(alldatas.get(7));
                }
                if (i == 8) {
                    nine.setText(alldatas.get(8));
                }
                if (i == 9) {
                    ten.setText(alldatas.get(9));
                }
                if (i == 10) {
                    eleven.setText(alldatas.get(10));
                }
                if (i == 11) {
                    twelve.setText(alldatas.get(11));
                }
            }

        }
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main3Activity.this,Main2Activity.class);
                ttsTool.stop();
                startActivity(intent);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new Access(Main3Activity.this);
                db = dbHelper.getWritableDatabase();
                DataBaseUtil.clear(dbHelper,db);
                alldatas.clear();
                sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);
                editor=sharedPreferences.edit();
                StroreDoubleUtil.putDouble(editor,"datanum",0);
                editor.commit();
                one.setText("0");
                two.setText("0");
                three.setText("0");
                four.setText("0");
                five.setText("0");
                six.setText("0");
                seven.setText("0");
                eight.setText("0");
                nine.setText("0");
                ten.setText("0");
                eleven.setText("0");
                twelve.setText("0");
                Intent intent=new Intent(Main3Activity.this,Main2Activity.class);
                ttsTool.stop();
                startActivity(intent);
            }
        });
    }
        @Override
        public void onWindowFocusChanged(boolean hasFocus) {
            super.onWindowFocusChanged(hasFocus);
            if(hasFocus==true){
                ttsTool=Total.getTtsTool();
                sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);

                IsTTSOpen = sharedPreferences.getBoolean("tts", true);
                if(alldatas.size()!=0) {
                    if (IsTTSOpen == true) {
                        ttsTool.speak("您的最高分是" + alldatas.get(0) + "分，加油，开动你的脑筋，继续努力哦，争取打破纪录，超过最高分。加油！", Main3Activity.this);
                    }
                }
                else {
                    if (IsTTSOpen == true) {
                        ttsTool.speak("您的最高分是0分，加油，开动你的脑筋，继续努力哦，争取打破纪录，超过最高分。加油！", Main3Activity.this);
                    }
                }
            }
        }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        AudioManager audioManager  = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if(keyCode==KeyEvent.KEYCODE_BACK){
            Intent intent=new Intent(Main3Activity.this,Main2Activity.class);
            ttsTool.stop();
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
