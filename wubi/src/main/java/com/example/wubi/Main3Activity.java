package com.example.wubi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main3Activity extends AppCompatActivity {
ArrayList<String>hos=new ArrayList<String>();
DataBase db;
SQLiteDatabase sql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        checkPermission();
        db=new DataBase(this);
        sql=db.getWritableDatabase();
        DataBaseUtil.clear(db,sql);
        initRaw();
        GetDataBasePath(this);
    }
    public void initRaw() {
        InputStream inputStream = getResources().openRawResource(R.raw.write);
        Scanner sc=new Scanner(inputStream,"gbk");
        while(sc.hasNext()){
            String a=sc.next();
            Log.e("忽必烈",a);
            hos.add(a);
        }
        MyUtil myUtil=new MyUtil();
        myUtil.train(hos,this);
    }
   private void GetDataBasePath(Context context) {

       String path = "/data/data/com.example.wubi/databases/check.db";
       Log.e("司马丕", String.valueOf(new File(path).exists()));
       if (new File(path).exists()) {
//           Log.e("司马懿",DataBaseUtil.query(db,sql,"王").get(0));
       }
       else{

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
        /*if (isAllGranted == false) {
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
        }*/
        return isAllGranted;
    }
}
