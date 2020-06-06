package com.example.wubi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {
    FrameLayout ship1,ship2,ship3,ship4;
    ImageView cannon1,cannon2,cannon3,cannon4;
    Rect rectS1,rectS2,rectS3,rectS4;
    Rect rectC1,rectC2,rectC3,rectC4;
    Rect rectP1,rectP2,rectP3,rectP4;
    int R1,T1,R2,T2,R3,T3,R4,T4;
    Thread A;
    Handler handler;
    double add=0;
    RelativeLayout rel1,rel2,rel3,rel4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);
        cannon1=findViewById(R.id.cannon1);
        cannon2=findViewById(R.id.cannon2);
        cannon3=findViewById(R.id.cannon3);
        cannon4=findViewById(R.id.cannon4);
        rel1=findViewById(R.id.rel1);
        rel2=findViewById(R.id.rel2);
        rel3=findViewById(R.id.rel3);
        rel4=findViewById(R.id.rel4);
        ship1=addView(rel1);
        ship2=addView(rel2);
        ship3=addView(rel3);
        ship4=addView(rel4);
       /* handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==2){
                    add=add+0.001;
                    Random random=new Random();
                    Random random2=new Random();
                    Random random3=new Random();
                    Random random4=new Random();

                }
            }
        };*/
    }
    public FrameLayout addView(RelativeLayout rel) {
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        RelativeLayout.LayoutParams lp2=(RelativeLayout.LayoutParams)frameLayout.getLayoutParams();
        lp2.setMargins(0,0,0,0);
        lp2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rel.addView(frameLayout);
        ImageView img=new ImageView(this);
        img.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        img.setBackgroundResource(R.drawable.ship);
        frameLayout.addView(img);
        TextView textView=new TextView(this);
        textView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        FrameLayout.LayoutParams lp=(FrameLayout.LayoutParams)textView.getLayoutParams();
        lp.setMargins(DensityUtil.dp2px(this,24),DensityUtil.dp2px(this,3),0,0);
        textView.setText("曹");
        textView.setTextColor(Color.rgb(0,0,0));
        textView.setTextSize(17);
        textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        frameLayout.addView(textView);
        return frameLayout;
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus==true){
            rectC1=new Rect();
            cannon1.getGlobalVisibleRect(rectC1);
            R1=rectC1.right;
            T1=rectC1.top;
            rectC2=new Rect();
            cannon2.getGlobalVisibleRect(rectC2);
            R2=rectC2.right;
            T2=rectC2.top;
            rectC3=new Rect();
            cannon3.getGlobalVisibleRect(rectC3);
            R3=rectC3.right;
            T3=rectC3.top;
            rectC4=new Rect();
            cannon4.getGlobalVisibleRect(rectC4);
            R4=rectC4.right;
            T4=rectC4.top;
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ship);
            int bitmapHeight = bitmap.getHeight();
            int bitmapWidth = bitmap.getWidth();
            LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) rel1.getLayoutParams();
            linearParams.width= ViewGroup.LayoutParams.MATCH_PARENT;
            linearParams.height=bitmapHeight;
            rel1.setLayoutParams(linearParams);
            LinearLayout.LayoutParams linearParams2 =(LinearLayout.LayoutParams) rel2.getLayoutParams();
            linearParams2.width= ViewGroup.LayoutParams.MATCH_PARENT;
            linearParams2.height=bitmapHeight;
            rel2.setLayoutParams(linearParams2);
            LinearLayout.LayoutParams linearParams3 =(LinearLayout.LayoutParams) rel3.getLayoutParams();
            linearParams3.width= ViewGroup.LayoutParams.MATCH_PARENT;
            linearParams3.height=bitmapHeight;
            rel3.setLayoutParams(linearParams3);
            LinearLayout.LayoutParams linearParams4 =(LinearLayout.LayoutParams) rel4.getLayoutParams();
            linearParams4.width= ViewGroup.LayoutParams.MATCH_PARENT;
            linearParams4.height=bitmapHeight;
            rel4.setLayoutParams(linearParams4);

        }

    }
}
