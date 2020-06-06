package com.example.wubi;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main4Activity extends AppCompatActivity {
InputStream inputStream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        inputStream=getResources().openRawResource(R.raw.example);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            DataBase db=new DataBase(this);
            SQLiteDatabase sql=db.getWritableDatabase();
            DataBaseUtil.clear(db,sql);
        }

    }
}
