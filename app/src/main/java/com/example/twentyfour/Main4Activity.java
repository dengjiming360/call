package com.example.twentyfour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.MediaController;

import java.util.ArrayList;
import java.util.Arrays;

public class Main4Activity extends AppCompatActivity {
    boolean initalize = false;
    LinearLayout lin2;
    MediaController me;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    boolean check = false;
    boolean signal=false;
    boolean granted = false;
    SharedPreferences sharedPreferences2;
    SharedPreferences.Editor editor2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        root();
        setContentView(R.layout.activity_main4);
        sharedPreferences2 = getSharedPreferences("status", MODE_PRIVATE);
        editor2=sharedPreferences2.edit();
        editor2.putBoolean("task",false);
        editor2.commit();
        sharedPreferences = getSharedPreferences("settingsquanxian", MODE_PRIVATE);
        check = sharedPreferences.getBoolean("isgranted", false);
        checkPermission();
        sharedPreferences = getSharedPreferences("settingsquanxian", MODE_PRIVATE);
        check = sharedPreferences.getBoolean("isgranted", false);
        while (check == false) {
            checkPermission();
            sharedPreferences = getSharedPreferences("settingsquanxian", MODE_PRIVATE);
            check = sharedPreferences.getBoolean("isgranted", false);
            if (check == true) {
                break;
            }
        }
        sharedPreferences = getSharedPreferences("settingsquanxian", MODE_PRIVATE);
        check = sharedPreferences.getBoolean("isgranted", false);
        lin2 = findViewById(R.id.lin2);
        initalize = true;
        if (check == true) {
            FullScreen mWebFlash = (FullScreen) findViewById(R.id.jxt);
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.begin24);
            me = new MediaController(this);
            // mWebFlash.setMediaController(me);
            me.setMediaPlayer(mWebFlash);
            mWebFlash.requestFocus();
            mWebFlash.setOnCompletionListener(new MyPlayerOnCompletionListener());
            mWebFlash.setVideoURI(uri);
            mWebFlash.start();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }


    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            Intent intent = new Intent(Main4Activity.this, Main2Activity.class);
            signal=true;
            initalize = true;
            intent.putExtra("init", initalize);
            intent.putExtra("signal",signal);
            startActivity(intent);
        }
    }

    public boolean checkPermission() {
        boolean isAllGranted = true;
        String[] num;
        String[] req = {Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_NETWORK_STATE};
        ArrayList<Integer> permit = new ArrayList<Integer>();
        ArrayList<String> allpermissions = new ArrayList<String>(Arrays.asList(req));
        ArrayList<String> denied = new ArrayList<String>();
        for (int i = 0; i < allpermissions.size(); i++) {
            permit.add(ActivityCompat.checkSelfPermission(this, allpermissions.get(i)));
        }
        for (int j = 0; j < permit.size(); j++) {
            if (permit.get(j) != PackageManager.PERMISSION_GRANTED) {
                isAllGranted = false;
                break;
            }
        }
        if (isAllGranted == false) {
            sharedPreferences = getSharedPreferences("settingsquanxian", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putBoolean("isgranted", false);
            editor.commit();
            for (int k = 0; k < permit.size(); k++) {
                if (permit.get(k) != PackageManager.PERMISSION_GRANTED) {
                    denied.add(allpermissions.get(k));
                }
            }
            num = new String[denied.size()];
            for (int l = 0; l < denied.size(); l++) {
                num[l] = denied.get(l);
            }
            for (int m = 0; m < num.length; m++) {
                ActivityCompat.requestPermissions(this, num, 1);
            }

        }
        if (isAllGranted == true) {
            sharedPreferences = getSharedPreferences("settingsquanxian", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putBoolean("isgranted", true);
            editor.commit();
        }
        return isAllGranted;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        AudioManager audioManager  = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(startMain);
            finish();
            System.exit(0);
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

    public boolean isGranted() {
        return granted;
    }

    public void setGranted(boolean granted) {
        this.granted = granted;
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

