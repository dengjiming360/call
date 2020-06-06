package com.example.wubi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
ImageView v1,v2;
int[]  lo1=new int[2];
int []lo2=new int[2];
LinearLayout lin1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lin1=findViewById(R.id.lin1);
      //   v1=findViewById(R.id.d1);
         v2=findViewById(R.id.d2);


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Rect rect1=new Rect();
        Rect rect2=new Rect();
    }
}
