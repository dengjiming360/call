package com.example.twentyfour;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cal.Calculator;
import com.deng.result.twentyfour.Tw;
import com.example.ttsreader.TTSTool;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
ImageView img1,img2,img3,img4;
TextView tv1,tv2,tv3,tv4;
TextView remaintime,score;
String str;
String a="";
String b="";
String c="";
ImageView img11,img12,img13;
NumBean numBean1,numBean2,numBean3,numBean4;
int life=3;
long score2=0;
int click9=0;
int click10=0;
int click11=0;
int click12=0;
TextView lifes;
boolean flag=true;
String gameover="";
int dialogshowtime=0;
boolean finish=false;
    boolean pause=false;
    boolean init=false;
    boolean restart=false;
    boolean end=false;
    ArrayList<String>alldatas=new ArrayList<String>();
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==100){
                sharedPreferences=getSharedPreferences("status",MODE_PRIVATE);
                gameover=sharedPreferences.getString("gameover","");
                IsTTSOpen=sharedPreferences.getBoolean("tts",true);
                bgm=sharedPreferences.getBoolean("bgm",true);
                fangfa();
            }
        }
    };
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences2;
    SharedPreferences.Editor editor2;
ArrayList<NumBean>num=new ArrayList<NumBean>();
ArrayList<String>numkey=new ArrayList<String>();
int[] dr={R.drawable.bj,R.drawable.bjc,R.drawable.c10,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,
        R.drawable.c6,R.drawable.c7,R.drawable.c8,R.drawable.c9,R.drawable.ca,R.drawable.cj,R.drawable.ck,
        R.drawable.cq,R.drawable.d10,R.drawable.d2,R.drawable.d3,R.drawable.d4,R.drawable.d5,R.drawable.d6,
        R.drawable.d7,R.drawable.d8,R.drawable.d9,R.drawable.da,R.drawable.dj,R.drawable.dk,R.drawable.dq,
        R.drawable.h10,R.drawable.h2,R.drawable.h3,R.drawable.h4,R.drawable.h5,R.drawable.h6,R.drawable.h7,
        R.drawable.h8,R.drawable.h9,R.drawable.ha,R.drawable.hj,R.drawable.hk,R.drawable.hq,R.drawable.lj,
        R.drawable.ljc,R.drawable.s10,R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5,R.drawable.s6,
        R.drawable.s7,R.drawable.s8,R.drawable.s9,R.drawable.sa,R.drawable.sj,R.drawable.sk,R.drawable.sq};
        int[] numlk=new int[4];
        Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,btn13;
EditText suanshi;
boolean isEnabled1=false;
boolean isEnabled2=false;
boolean isEnabled3=false;
boolean isEnabled4=false;
boolean isEnabled9=true;
    boolean isEnabled10=true;
    boolean isEnabled11=true;
    boolean isEnabled12=true;
    TTSTool ttsTool;
    int count=120;
    boolean IsTTSOpen=true;
    MediaPlayer mediaPlayer2;
    boolean bgm=true;
    int once=0;
    double datacount=0;
    boolean isHigh=false;
    boolean chu=false;
    MediaPlayer mp;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        root();
        setContentView(R.layout.activity_main);
        sharedPreferences=getSharedPreferences("status",MODE_PRIVATE);
        gameover=sharedPreferences.getString("gameover","");
        IsTTSOpen=sharedPreferences.getBoolean("tts",true);
        bgm=sharedPreferences.getBoolean("bgm",true);
        datacount=StroreDoubleUtil.getDouble(sharedPreferences,"datanum",0);
        ttsTool=Total.getTtsTool();
        mp=Total.getMediaPlayer();
        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        img3=findViewById(R.id.img3);
        img4=findViewById(R.id.img4);
        lifes=findViewById(R.id.lifes);
        numlk[0]=-1;
        numlk[1]=-1;
        numlk[2]=-1;
        numlk[3]=-1;
        numlk=NumUtil.Randoms(56);
        img1.setImageResource(dr[numlk[0]]);
        img2.setImageResource(dr[numlk[1]]);
        img3.setImageResource(dr[numlk[2]]);
        img4.setImageResource(dr[numlk[3]]);
        tv1=findViewById(R.id.textview1);
        tv2=findViewById(R.id.textview2);
        tv3=findViewById(R.id.textview3);
        tv4=findViewById(R.id.textview4);
        suanshi=findViewById(R.id.suanshi);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        btn7=findViewById(R.id.btn7);
        btn8=findViewById(R.id.btn8);
        btn9=findViewById(R.id.btn9);
        btn10=findViewById(R.id.btn10);
        btn11=findViewById(R.id.btn11);
        btn12=findViewById(R.id.btn12);
        btn13=findViewById(R.id.btn13);
        img11=findViewById(R.id.img11);
        img12=findViewById(R.id.img12);
        img13=findViewById(R.id.img13);
        remaintime=findViewById(R.id.remaintime);
        score=findViewById(R.id.score);
        tv1.setText(NumUtil.str(numlk[0]));
        tv2.setText(NumUtil.str(numlk[1]));
        tv3.setText(NumUtil.str(numlk[2]));
        tv4.setText(NumUtil.str(numlk[3]));
        btn9.setText(NumUtil.str(numlk[0]));
        btn10.setText(NumUtil.str(numlk[1]));
        btn11.setText(NumUtil.str(numlk[2]));
        btn12.setText(NumUtil.str(numlk[3]));
        btn1.setBackgroundResource(R.drawable.disabled);
        btn2.setBackgroundResource(R.drawable.disabled);
        btn3.setBackgroundResource(R.drawable.disabled);
        btn4.setBackgroundResource(R.drawable.disabled);
        btn9.setBackgroundResource(R.drawable.myshape);
        btn10.setBackgroundResource(R.drawable.myshape);
        btn11.setBackgroundResource(R.drawable.myshape);
        btn12.setBackgroundResource(R.drawable.myshape);
        initView();
        score.setText("得分:0");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEnabled1==true) {
                    btn1.setBackgroundResource(R.drawable.myshape);
                    suanshi.append("+");

                    isEnabled1=false;
                    isEnabled2=false;
                    isEnabled3=false;
                    isEnabled4=false;
                    isEnabled9=true;
                    isEnabled10=true;
                    isEnabled11=true;
                    isEnabled12=true;
                    btn1.setBackgroundResource(R.drawable.disabled);
                    btn2.setBackgroundResource(R.drawable.disabled);
                    btn3.setBackgroundResource(R.drawable.disabled);
                    btn4.setBackgroundResource(R.drawable.disabled);
                    if(click9==0) {
                        btn9.setBackgroundResource(R.drawable.myshape);
                    }
                    if(click10==0) {
                        btn10.setBackgroundResource(R.drawable.myshape);
                    }
                    if(click11==0) {
                        btn11.setBackgroundResource(R.drawable.myshape);
                    }
                    if(click12==0) {
                        btn12.setBackgroundResource(R.drawable.myshape);
                    }
                }
                else{
                    btn1.setBackgroundResource(R.drawable.disabled);
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEnabled1==true) {
                    btn2.setBackgroundResource(R.drawable.myshape);
                    suanshi.append("-");
                    isEnabled1=false;
                    isEnabled2=false;
                    isEnabled3=false;
                    isEnabled4=false;
                    isEnabled9=true;
                    isEnabled10=true;
                    isEnabled11=true;
                    isEnabled12=true;
                    btn1.setBackgroundResource(R.drawable.disabled);
                    btn2.setBackgroundResource(R.drawable.disabled);
                    btn3.setBackgroundResource(R.drawable.disabled);
                    btn4.setBackgroundResource(R.drawable.disabled);
                    if(click9==0) {
                        btn9.setBackgroundResource(R.drawable.myshape);
                    }
                    if(click10==0) {
                        btn10.setBackgroundResource(R.drawable.myshape);
                    }
                    if(click11==0) {
                        btn11.setBackgroundResource(R.drawable.myshape);
                    }
                    if(click12==0) {
                        btn12.setBackgroundResource(R.drawable.myshape);
                    }
                }
                else{
                    btn2.setBackgroundResource(R.drawable.disabled);
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEnabled1==true) {
                    btn3.setBackgroundResource(R.drawable.myshape);
                    suanshi.append("×");
                    isEnabled1=false;
                    isEnabled2=false;
                    isEnabled3=false;
                    isEnabled4=false;
                    isEnabled9=true;
                    isEnabled10=true;
                    isEnabled11=true;
                    isEnabled12=true;
                    btn1.setBackgroundResource(R.drawable.disabled);
                    btn2.setBackgroundResource(R.drawable.disabled);
                    btn3.setBackgroundResource(R.drawable.disabled);
                    btn4.setBackgroundResource(R.drawable.disabled);
                    if(click9==0) {
                        btn9.setBackgroundResource(R.drawable.myshape);
                    }
                    if(click10==0) {
                        btn10.setBackgroundResource(R.drawable.myshape);
                    }
                    if(click11==0) {
                        btn11.setBackgroundResource(R.drawable.myshape);
                    }
                    if(click12==0) {
                        btn12.setBackgroundResource(R.drawable.myshape);
                    }
                }
                else{
                    btn3.setBackgroundResource(R.drawable.disabled);
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEnabled1==true) {
                    btn4.setBackgroundResource(R.drawable.myshape);
                    suanshi.append("÷");
                    isEnabled1=false;
                    isEnabled2=false;
                    isEnabled3=false;
                    isEnabled4=false;
                    isEnabled9=true;
                    isEnabled10=true;
                    isEnabled11=true;
                    isEnabled12=true;
                    btn1.setBackgroundResource(R.drawable.disabled);
                    btn2.setBackgroundResource(R.drawable.disabled);
                    btn3.setBackgroundResource(R.drawable.disabled);
                    btn4.setBackgroundResource(R.drawable.disabled);
                    if(click9==0) {
                        btn9.setBackgroundResource(R.drawable.myshape);
                    }
                    if(click10==0) {
                        btn10.setBackgroundResource(R.drawable.myshape);
                    }
                    if(click11==0) {
                        btn11.setBackgroundResource(R.drawable.myshape);
                    }
                    if(click12==0) {
                        btn12.setBackgroundResource(R.drawable.myshape);
                    }
                }
                else{
                    btn4.setBackgroundResource(R.drawable.disabled);
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suanshi.append("(");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               suanshi.append(")");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = suanshi.getText().toString().trim();


                if (a.length() >= 2) {
                    b = a.substring(a.length() - 2, a.length());
                }
                if (a.length() < 2) {
                    b = "";
                }
                if (a.length() >= 1) {
                    c = a.substring(a.length() - 1, a.length());
                }
                if (a.length() < 1) {
                    c = "";
                }

                String ta1 = btn9.getText().toString().trim();
                String ta2 = btn10.getText().toString().trim();
                String ta3 = btn11.getText().toString().trim();
                String ta4 = btn12.getText().toString().trim();

                if (a.length() >= 2) {
                if (b.equals("10") || b.equals("11") || b.equals("12") || b.equals("13") || b.equals("14")) {

                        if (num.size() > 0) {
                            if (num.get(num.size() - 1).getK().equals("A")) {
                                isEnabled9 = true;
                                btn9.setBackgroundResource(R.drawable.myshape);
                                click9 = 0;
                                num.remove(num.size() - 1);
                                numkey.remove(numkey.size() - 1);
                            }
                        }
                        if(num.size()>0) {
                            if (num.get(num.size() - 1).getK().equals("B")) {
                                isEnabled10 = true;
                                btn10.setBackgroundResource(R.drawable.myshape);
                                click10 = 0;
                                num.remove(num.size() - 1);
                                numkey.remove(numkey.size() - 1);
                            }
                        }
                        if(num.size()>0) {
                            if (num.get(num.size() - 1).getK().equals("C")) {
                                isEnabled11 = true;
                                btn11.setBackgroundResource(R.drawable.myshape);
                                click11 = 0;
                                num.remove(num.size() - 1);
                                numkey.remove(numkey.size() - 1);
                            }
                        }
                        if(num.size()>0) {
                            if (num.get(num.size() - 1).getK().equals("D")) {
                                isEnabled12 = true;
                                btn12.setBackgroundResource(R.drawable.myshape);
                                click12 = 0;
                                num.remove(num.size() - 1);
                                numkey.remove(numkey.size() - 1);
                            }
                        }
                        if (!numkey.contains("A")) {
                            isEnabled9 = true;
                            click9=0;
                            btn9.setBackgroundResource(R.drawable.myshape);
                        }
                        if (!numkey.contains("B")) {
                            isEnabled10 = true;
                            click10=0;
                            btn10.setBackgroundResource(R.drawable.myshape);
                        }
                        if (!numkey.contains("C")) {
                            isEnabled11 = true;
                            click11=0;
                            btn11.setBackgroundResource(R.drawable.myshape);
                        }
                        if (!numkey.contains("D")) {
                            isEnabled12 = true;
                            click12=0;
                            btn12.setBackgroundResource(R.drawable.myshape);
                        }
                        if (numkey.contains("A")) {
                            isEnabled9 = false;
                            btn9.setBackgroundResource(R.drawable.disabled);
                        }
                        if (numkey.contains("B")) {
                            isEnabled10 = false;
                            btn10.setBackgroundResource(R.drawable.disabled);
                        }
                        if (numkey.contains("C")) {
                            isEnabled11 = false;
                            btn11.setBackgroundResource(R.drawable.disabled);
                        }
                        if (numkey.contains("D")) {
                            isEnabled12 = false;
                            btn12.setBackgroundResource(R.drawable.disabled);
                        }
                        btn1.setBackgroundResource(R.drawable.disabled);
                        btn2.setBackgroundResource(R.drawable.disabled);
                        btn3.setBackgroundResource(R.drawable.disabled);
                        btn4.setBackgroundResource(R.drawable.disabled);
                        isEnabled1 = false;
                        isEnabled2 = false;
                        isEnabled3 = false;
                        isEnabled4 = false;
                        suanshi.setText(a.substring(0, a.length() - 2));
                    }
                    if (suanshi.getText().toString().trim().length() == 0) {
                        suanshi.setText("");
                    }
                }
                if (a.length() >= 2) {
                    if (!b.equals("10") && !b.equals("11") && !b.equals("12") && !b.equals("13") && !b.equals("14")) {
                        if (c.equals("1") || c.equals("2") || c.equals("3") || c.equals("4") || c.equals("5") ||
                                c.equals("6") || c.equals("7") || c.equals("8") || c.equals("9")) {

                            if (num.size() > 0) {
                                if (num.get(num.size() - 1).getK().equals("A")) {
                                    isEnabled9 = true;
                                    btn9.setBackgroundResource(R.drawable.myshape);
                                    click9 = 0;
                                    num.remove(num.size() - 1);
                                    numkey.remove(numkey.size() - 1);
                                }
                            }
                                if (num.size() > 0) {
                                    if (num.get(num.size() - 1).getK().equals("B")) {
                                        isEnabled10 = true;
                                        btn10.setBackgroundResource(R.drawable.myshape);
                                        click10 = 0;
                                        num.remove(num.size() - 1);
                                        numkey.remove(numkey.size() - 1);
                                    }
                                }
                                if (num.size() > 0) {
                                    if (num.get(num.size() - 1).getK().equals("C")) {
                                        isEnabled11 = true;
                                        btn11.setBackgroundResource(R.drawable.myshape);
                                        click11 = 0;
                                        num.remove(num.size() - 1);
                                        numkey.remove(numkey.size() - 1);
                                    }
                                }
                                if (num.size() > 0) {
                                    if (num.get(num.size() - 1).getK().equals("D")) {
                                        isEnabled12 = true;
                                        btn12.setBackgroundResource(R.drawable.myshape);
                                        click12 = 0;
                                        num.remove(num.size() - 1);
                                        numkey.remove(numkey.size() - 1);
                                    }
                                }

                            if (!numkey.contains("A")) {
                                isEnabled9 = true;
                                click9=0;
                                btn9.setBackgroundResource(R.drawable.myshape);
                            }
                            if (!numkey.contains("B")) {
                                isEnabled10 = true;
                                click10=0;
                                btn10.setBackgroundResource(R.drawable.myshape);
                            }
                            if (!numkey.contains("C")) {
                                isEnabled11 = true;
                                click11=0;
                                btn11.setBackgroundResource(R.drawable.myshape);
                            }
                            if (!numkey.contains("D")) {
                                isEnabled12 = true;
                                click12=0;
                                btn12.setBackgroundResource(R.drawable.myshape);
                            }
                            if (numkey.contains("A")) {
                                isEnabled9 = false;
                                btn9.setBackgroundResource(R.drawable.disabled);
                            }
                            if (numkey.contains("B")) {
                                isEnabled10 = false;
                                btn10.setBackgroundResource(R.drawable.disabled);
                            }
                            if (numkey.contains("C")) {
                                isEnabled11 = false;
                                btn11.setBackgroundResource(R.drawable.disabled);
                            }
                            if (numkey.contains("D")) {
                                isEnabled12 = false;
                                btn12.setBackgroundResource(R.drawable.disabled);
                            }
                            btn1.setBackgroundResource(R.drawable.disabled);
                            btn2.setBackgroundResource(R.drawable.disabled);
                            btn3.setBackgroundResource(R.drawable.disabled);
                            btn4.setBackgroundResource(R.drawable.disabled);
                            isEnabled1 = false;
                            isEnabled2 = false;
                            isEnabled3 = false;
                            isEnabled4 = false;

                            suanshi.setText(a.substring(0, a.length() - 1));
                        }
                        if (a.length() == 0) {
                            suanshi.setText("");
                        }
                    }
                    if (c.equals("+") || c.equals("-") || c.equals("×") || c.equals("÷")) {
                        if (a.length() >= 1) {

                            btn1.setBackgroundResource(R.drawable.myshape);
                            btn2.setBackgroundResource(R.drawable.myshape);
                            btn3.setBackgroundResource(R.drawable.myshape);
                            btn4.setBackgroundResource(R.drawable.myshape);
                            isEnabled1 = true;
                            isEnabled2 = true;
                            isEnabled3 = true;
                            isEnabled4 = true;
                            btn9.setBackgroundResource(R.drawable.disabled);
                            btn10.setBackgroundResource(R.drawable.disabled);
                            btn11.setBackgroundResource(R.drawable.disabled);
                            btn12.setBackgroundResource(R.drawable.disabled);
                            isEnabled9 = false;
                            isEnabled10 = false;
                            isEnabled11 = false;
                            isEnabled12 = false;
                            suanshi.setText(a.substring(0, a.length() - 1));
                        }
                        if (a.length() == 0) {
                            suanshi.setText("");
                        }
                    }
                    if (c.equals(")") || c.equals("(")) {
                        if (a.length() >= 1) {
                            suanshi.setText(a.substring(0, a.length() - 1));
                        }
                        if (a.length() == 0) {
                            suanshi.setText("");
                        }
                    }
                    if (a.length() == 0) {
                        suanshi.setText("");
                    }
                }
                if (a.length() == 1) {
                    if (c.equals("1") || c.equals("2") || c.equals("3") || c.equals("4") || c.equals("5") ||
                            c.equals("6") || c.equals("7") || c.equals("8") || c.equals("9")) {


                        if (num.size() > 0) {
                            if (num.get(num.size() - 1).getK().equals("A")) {
                                isEnabled9 = true;
                                btn9.setBackgroundResource(R.drawable.myshape);
                                num.remove(num.size() - 1);
                                numkey.remove(numkey.size() - 1);
                                click9 = 0;
                            }
                        }
                        if (num.size() > 0) {
                            if (num.get(num.size() - 1).getK().equals("B")) {
                                isEnabled10 = true;
                                btn10.setBackgroundResource(R.drawable.myshape);
                                num.remove(num.size() - 1);
                                numkey.remove(numkey.size() - 1);
                                click10 = 0;
                            }
                        }
                        if (num.size() > 0) {
                            if (num.get(num.size() - 1).getK().equals("C")) {
                                isEnabled11 = true;
                                btn11.setBackgroundResource(R.drawable.myshape);
                                num.remove(num.size() - 1);
                                numkey.remove(numkey.size() - 1);
                                click11 = 0;
                            }
                        }
                        if (num.size() > 0) {
                            if (num.get(num.size() - 1).getK().equals("D")) {
                                isEnabled12 = true;
                                btn12.setBackgroundResource(R.drawable.myshape);
                                num.remove(num.size() - 1);
                                numkey.remove(numkey.size() - 1);
                                click12 = 0;
                            }
                        }

                        if (!numkey.contains("A")) {
                            isEnabled9 = true;
                            btn9.setBackgroundResource(R.drawable.myshape);
                            click9=0;
                        }
                        if (!numkey.contains("B")) {
                            isEnabled10 = true;
                            btn10.setBackgroundResource(R.drawable.myshape);
                            click10=0;
                        }
                        if (!numkey.contains("C")) {
                            isEnabled11 = true;
                            btn11.setBackgroundResource(R.drawable.myshape);
                            click11=0;
                        }
                        if (!numkey.contains("D")) {
                            isEnabled12 = true;
                            btn12.setBackgroundResource(R.drawable.myshape);
                            click12=0;
                        }
                        if (numkey.contains("A")) {
                            isEnabled9 = false;
                            btn9.setBackgroundResource(R.drawable.disabled);
                        }
                        if (numkey.contains("B")) {
                            isEnabled10 = false;
                            btn10.setBackgroundResource(R.drawable.disabled);
                        }
                        if (numkey.contains("C")) {
                            isEnabled11 = false;
                            btn11.setBackgroundResource(R.drawable.disabled);
                        }
                        if (numkey.contains("D")) {
                            isEnabled12 = false;
                            btn12.setBackgroundResource(R.drawable.disabled);
                        }
                        btn1.setBackgroundResource(R.drawable.disabled);
                        btn2.setBackgroundResource(R.drawable.disabled);
                        btn3.setBackgroundResource(R.drawable.disabled);
                        btn4.setBackgroundResource(R.drawable.disabled);
                        isEnabled1 = false;
                        isEnabled2 = false;
                        isEnabled3 = false;
                        isEnabled4 = false;
                        suanshi.setText(a.substring(0, a.length() - 1));
                    }

                    if (a.length() == 0) {
                        suanshi.setText("");
                    }
                }
                if (c.equals("+") || c.equals("-") || c.equals("×") || c.equals("÷")) {
                    if (a.length() >= 1) {

                        btn1.setBackgroundResource(R.drawable.myshape);
                        btn2.setBackgroundResource(R.drawable.myshape);
                        btn3.setBackgroundResource(R.drawable.myshape);
                        btn4.setBackgroundResource(R.drawable.myshape);
                        isEnabled1 = true;
                        isEnabled2 = true;
                        isEnabled3 = true;
                        isEnabled4 = true;
                        btn9.setBackgroundResource(R.drawable.disabled);
                        btn10.setBackgroundResource(R.drawable.disabled);
                        btn11.setBackgroundResource(R.drawable.disabled);
                        btn12.setBackgroundResource(R.drawable.disabled);
                        isEnabled9 = false;
                        isEnabled10 = false;
                        isEnabled11 = false;
                        isEnabled12 = false;
                        suanshi.setText(a.substring(0, a.length() - 1));
                    }
                    if (a.length() == 0) {
                        suanshi.setText("");
                    }
                }
                if (c.equals(")") || c.equals("(")) {
                    if (a.length() >= 1) {

                        suanshi.setText(a.substring(0, a.length() - 1));
                    }
                    if (a.length() == 0) {
                        suanshi.setText("");
                    }
                }
                if (a.length() == 0) {
                    suanshi.setText("");
                }
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean match=Kuohao.whetherStringClose(suanshi.getText().toString().trim());
                try {
                    if (match == true) {
                        String finalresult = "";
                        String b = suanshi.getText().toString().trim();
                        String c = b.replaceAll("×", "*");
                        String d = c.replaceAll("÷", "/");
                        Calculator ca = new Calculator();
                        String result = ca.handleStr(d);
                        if (result.endsWith(".0")) {
                            finalresult = result.substring(0, result.length() - 2);
                        }
                        if (finalresult.equals("24")) {
                            score2 = score2 + 1;
                            score.setText("得分:" + score2);
                            pause = true;
                            if (IsTTSOpen == true) {
                                ttsTool.speak("恭喜你答对了，真棒。再接再厉哦。", MainActivity.this);
                            }
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("提示：");
                            builder.setMessage("恭喜你，答对了。你的最终结果等于24，符合要求，真棒。加一分，再接再厉，继续加油哦！");

                            builder.setCancelable(false);
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    numlk = NumUtil.Randoms(56);
                                    img1.setImageResource(dr[numlk[0]]);
                                    img2.setImageResource(dr[numlk[1]]);
                                    img3.setImageResource(dr[numlk[2]]);
                                    img4.setImageResource(dr[numlk[3]]);
                                    btn9.setText(NumUtil.str(numlk[0]));
                                    btn10.setText(NumUtil.str(numlk[1]));
                                    btn11.setText(NumUtil.str(numlk[2]));
                                    btn12.setText(NumUtil.str(numlk[3]));
                                    tv1.setText(NumUtil.str(numlk[0]));
                                    tv2.setText(NumUtil.str(numlk[1]));
                                    tv3.setText(NumUtil.str(numlk[2]));
                                    tv4.setText(NumUtil.str(numlk[3]));
                                    btn1.setBackgroundResource(R.drawable.disabled);
                                    btn2.setBackgroundResource(R.drawable.disabled);
                                    btn3.setBackgroundResource(R.drawable.disabled);
                                    btn4.setBackgroundResource(R.drawable.disabled);
                                    btn9.setBackgroundResource(R.drawable.myshape);
                                    btn10.setBackgroundResource(R.drawable.myshape);
                                    btn11.setBackgroundResource(R.drawable.myshape);
                                    btn12.setBackgroundResource(R.drawable.myshape);
                                    isEnabled1 = false;
                                    isEnabled2 = false;
                                    isEnabled3 = false;
                                    isEnabled4 = false;
                                    isEnabled9 = true;
                                    isEnabled10 = true;
                                    isEnabled11 = true;
                                    isEnabled12 = true;
                                    click9 = 0;
                                    click10 = 0;
                                    click11 = 0;
                                    click12 = 0;
                                    count = 120;
                                    suanshi.setText("");
                                    if (life != 0) {
                                        if (finish == true) {
                                            finish = false;
                                        }
                                        if (pause == true) {
                                            restart = true;
                                            pause = false;
                                            initView();
                                        }
                                    }
                                    if (IsTTSOpen == true && life != 0) {
                                        ttsTool.speak("请看下一题。", MainActivity.this);
                                    }
                                }
                            });
                            AlertDialog dialog = builder.create();
                            if (flag == true) {
                                dialog.show();
                            }
                        }
                        if (!finalresult.equals("24")) {
                            if (life > 0) {
                                life = life - 1;
                            }
                            if (life == 3) {
                                img13.setBackgroundResource(R.drawable.pinkheart);
                                img12.setBackgroundResource(R.drawable.pinkheart);
                                img11.setBackgroundResource(R.drawable.pinkheart);
                            }
                            if (life == 2) {
                                img13.setBackgroundResource(R.drawable.grayheart);
                                img12.setBackgroundResource(R.drawable.pinkheart);
                                img11.setBackgroundResource(R.drawable.pinkheart);
                            }
                            if (life == 1) {
                                img13.setBackgroundResource(R.drawable.grayheart);
                                img12.setBackgroundResource(R.drawable.grayheart);
                                img11.setBackgroundResource(R.drawable.pinkheart);
                            }
                            if (life == 0) {
                                img13.setBackgroundResource(R.drawable.grayheart);
                                img12.setBackgroundResource(R.drawable.grayheart);
                                img11.setBackgroundResource(R.drawable.grayheart);
                            }

                            Tw tw = new Tw();
                            boolean iswujie = tw.hasSolution(Integer.valueOf(NumUtil.str(numlk[0])), Integer.valueOf(NumUtil.str(numlk[1])), Integer.valueOf(NumUtil.str(numlk[2])), Integer.valueOf(NumUtil.str(numlk[3])));
                            if (iswujie == true) {
                                String res = tw.getSolution(Integer.valueOf(NumUtil.str(numlk[0])), Integer.valueOf(NumUtil.str(numlk[1])), Integer.valueOf(NumUtil.str(numlk[2])), Integer.valueOf(NumUtil.str(numlk[3])));
                                String res2 = res.replaceAll("\\*", "×");
                                String res3 = res2.replaceAll("/", "÷");
                                pause = true;
                                if (IsTTSOpen == true) {
                                    ttsTool.speak("不好意思，您答错了。继续加油哦。", MainActivity.this);
                                }
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setTitle("提示：");
                                builder.setMessage("错误，您的答案不等于24，正确结果为" + res3);
                                builder.setCancelable(false);
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        if (life == 0) {
                                            gameOver();
                                        }
                                        if (life > 0) {
                                            numlk = NumUtil.Randoms(56);
                                            img1.setImageResource(dr[numlk[0]]);
                                            img2.setImageResource(dr[numlk[1]]);
                                            img3.setImageResource(dr[numlk[2]]);
                                            img4.setImageResource(dr[numlk[3]]);
                                            btn9.setText(NumUtil.str(numlk[0]));
                                            btn10.setText(NumUtil.str(numlk[1]));
                                            btn11.setText(NumUtil.str(numlk[2]));
                                            btn12.setText(NumUtil.str(numlk[3]));
                                            tv1.setText(NumUtil.str(numlk[0]));
                                            tv2.setText(NumUtil.str(numlk[1]));
                                            tv3.setText(NumUtil.str(numlk[2]));
                                            tv4.setText(NumUtil.str(numlk[3]));
                                            btn1.setBackgroundResource(R.drawable.disabled);
                                            btn2.setBackgroundResource(R.drawable.disabled);
                                            btn3.setBackgroundResource(R.drawable.disabled);
                                            btn4.setBackgroundResource(R.drawable.disabled);
                                            btn9.setBackgroundResource(R.drawable.myshape);
                                            btn10.setBackgroundResource(R.drawable.myshape);
                                            btn11.setBackgroundResource(R.drawable.myshape);
                                            btn12.setBackgroundResource(R.drawable.myshape);
                                            isEnabled1 = false;
                                            isEnabled2 = false;
                                            isEnabled3 = false;
                                            isEnabled4 = false;
                                            isEnabled9 = true;
                                            isEnabled10 = true;
                                            isEnabled11 = true;
                                            isEnabled12 = true;
                                            click9 = 0;
                                            click10 = 0;
                                            click11 = 0;
                                            click12 = 0;
                                            count = 120;
                                            suanshi.setText("");
                                        }
                                        if (life != 0) {
                                            if (finish == true) {
                                                finish = false;
                                            }
                                            if (pause == true) {
                                                restart = true;
                                                pause = false;
                                                initView();
                                            }
                                        }
                                        if (IsTTSOpen == true && life != 0) {
                                            ttsTool.speak("请看下一题。", MainActivity.this);
                                        }
                                    }
                                });
                                AlertDialog dialog = builder.create();
                                if (flag == true) {
                                    dialog.show();
                                }
                            }
                            if (iswujie == false) {
                                pause = true;
                                if (IsTTSOpen == true) {
                                    ttsTool.speak("不好意思，您答错了。本题正确答案为无解。继续加油哦。", MainActivity.this);
                                }
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setTitle("提示：");
                                builder.setMessage("错误，您的答案不等于24，本题答案为无解。");
                                builder.setCancelable(false);
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        if (life == 0) {
                                            gameOver();
                                        }
                                        if (life > 0) {
                                            numlk = NumUtil.Randoms(56);
                                            img1.setImageResource(dr[numlk[0]]);
                                            img2.setImageResource(dr[numlk[1]]);
                                            img3.setImageResource(dr[numlk[2]]);
                                            img4.setImageResource(dr[numlk[3]]);
                                            btn9.setText(NumUtil.str(numlk[0]));
                                            btn10.setText(NumUtil.str(numlk[1]));
                                            btn11.setText(NumUtil.str(numlk[2]));
                                            btn12.setText(NumUtil.str(numlk[3]));
                                            tv1.setText(NumUtil.str(numlk[0]));
                                            tv2.setText(NumUtil.str(numlk[1]));
                                            tv3.setText(NumUtil.str(numlk[2]));
                                            tv4.setText(NumUtil.str(numlk[3]));
                                            btn1.setBackgroundResource(R.drawable.disabled);
                                            btn2.setBackgroundResource(R.drawable.disabled);
                                            btn3.setBackgroundResource(R.drawable.disabled);
                                            btn4.setBackgroundResource(R.drawable.disabled);
                                            btn9.setBackgroundResource(R.drawable.myshape);
                                            btn10.setBackgroundResource(R.drawable.myshape);
                                            btn11.setBackgroundResource(R.drawable.myshape);
                                            btn12.setBackgroundResource(R.drawable.myshape);
                                            isEnabled1 = false;
                                            isEnabled2 = false;
                                            isEnabled3 = false;
                                            isEnabled4 = false;
                                            isEnabled9 = true;
                                            isEnabled10 = true;
                                            isEnabled11 = true;
                                            isEnabled12 = true;
                                            click9 = 0;
                                            click10 = 0;
                                            click11 = 0;
                                            click12 = 0;
                                            count = 120;
                                            suanshi.setText("");
                                        }
                                        if (life != 0) {
                                            if (finish == true) {
                                                finish = false;
                                            }
                                            if (pause == true) {
                                                restart = true;
                                                pause = false;
                                                initView();
                                            }
                                        }
                                        if (IsTTSOpen == true && life != 0) {
                                            ttsTool.speak("请看下一题。", MainActivity.this);
                                        }
                                    }
                                });
                                AlertDialog dialog = builder.create();
                                if (flag == true) {
                                    dialog.show();
                                }
                            }
                        }
                    }
                    if(match==false){
                        pause=true;
                        if(IsTTSOpen==true){
                            ttsTool.speak("您输入的算式中的括号没有成对，没有匹配，请检查并修改。",MainActivity.this);
                        }
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("提示：");
                        builder.setMessage("您的算式中的括号没有成对，没有匹配，请检查并修改。");
                        builder.setCancelable(false);
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                if(pause==true&&finish==false) {
                                    pause = false;
                                    initView();
                                }
                            }
                        });
                        AlertDialog dialog = builder.create();
                        if(flag==true) {
                            dialog.show();
                        }
                    }
                }
                catch(Exception e){
                    pause=true;
                    if(IsTTSOpen==true){
                        ttsTool.speak("您输入的算式格式错误，请检查并修改。",MainActivity.this);
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("提示：");
                    builder.setMessage("您的算式输入的格式不正确，请检查并修改。");
                    builder.setCancelable(false);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            if(pause==true&&finish==false) {
                                pause = false;
                                initView();
                            }
                        }
                    });
                    AlertDialog dialog = builder.create();
                    if(flag==true) {
                        dialog.show();
                    }
                }
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEnabled9==true&&click9==0) {
                    btn9.setBackgroundResource(R.drawable.myshape);
                    suanshi.append(btn9.getText().toString().trim());
                    isEnabled1=true;
                    isEnabled2=true;
                    isEnabled3=true;
                    isEnabled4=true;
                    btn1.setBackgroundResource(R.drawable.myshape);
                    btn2.setBackgroundResource(R.drawable.myshape);
                    btn3.setBackgroundResource(R.drawable.myshape);
                    btn4.setBackgroundResource(R.drawable.myshape);
                    btn9.setBackgroundResource(R.drawable.disabled);
                    btn10.setBackgroundResource(R.drawable.disabled);
                    btn11.setBackgroundResource(R.drawable.disabled);
                    btn12.setBackgroundResource(R.drawable.disabled);
                    isEnabled9=false;
                    isEnabled10=false;
                    isEnabled11=false;
                    isEnabled12=false;
                    numBean1=new NumBean("A",btn9.getText().toString().trim());
                    numBean1.setK("A");
                    numBean1.setN(btn9.getText().toString().trim());
                    num.add(numBean1);
                    numkey.add("A");

                    click9=1;
                }
                else{
                    btn9.setBackgroundResource(R.drawable.disabled);
                }
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEnabled10==true&&click10==0) {
                    btn10.setBackgroundResource(R.drawable.myshape);
                    suanshi.append(btn10.getText().toString().trim());
                    isEnabled1=true;
                    isEnabled2=true;
                    isEnabled3=true;
                    isEnabled4=true;
                    isEnabled9=false;
                    isEnabled10=false;
                    isEnabled11=false;
                    isEnabled12=false;
                    btn1.setBackgroundResource(R.drawable.myshape);
                    btn2.setBackgroundResource(R.drawable.myshape);
                    btn3.setBackgroundResource(R.drawable.myshape);
                    btn4.setBackgroundResource(R.drawable.myshape);
                    btn9.setBackgroundResource(R.drawable.disabled);
                    btn10.setBackgroundResource(R.drawable.disabled);
                    btn11.setBackgroundResource(R.drawable.disabled);
                    btn12.setBackgroundResource(R.drawable.disabled);
                    numBean2=new NumBean("B",btn10.getText().toString().trim());
                    numBean2.setK("B");
                    numBean2.setN(btn10.getText().toString().trim());
                    num.add(numBean2);
                    numkey.add("B");

                    click10=1;
                }
                else{
                    btn10.setBackgroundResource(R.drawable.disabled);
                }
            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEnabled11==true&&click11==0) {
                    btn11.setBackgroundResource(R.drawable.myshape);
                    suanshi.append(btn11.getText().toString().trim());
                    isEnabled1=true;
                    isEnabled2=true;
                    isEnabled3=true;
                    isEnabled4=true;
                    isEnabled9=false;
                    isEnabled10=false;
                    isEnabled11=false;
                    isEnabled12=false;
                    btn1.setBackgroundResource(R.drawable.myshape);
                    btn2.setBackgroundResource(R.drawable.myshape);
                    btn3.setBackgroundResource(R.drawable.myshape);
                    btn4.setBackgroundResource(R.drawable.myshape);
                    btn9.setBackgroundResource(R.drawable.disabled);
                    btn10.setBackgroundResource(R.drawable.disabled);
                    btn11.setBackgroundResource(R.drawable.disabled);
                    btn12.setBackgroundResource(R.drawable.disabled);
                    numBean3=new NumBean("C",btn11.getText().toString().trim());
                    numBean3.setK("C");
                    numBean3.setN(btn11.getText().toString().trim());
                    num.add(numBean3);
                    numkey.add("C");

                    click11=1;
                }
                else{
                    btn11.setBackgroundResource(R.drawable.disabled);
                }
            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(click12==1){
                    btn12.setBackgroundResource(R.drawable.disabled);
                }
                if(isEnabled12==true&&click12==0) {
                    btn12.setBackgroundResource(R.drawable.myshape);
                    suanshi.append(btn12.getText().toString().trim());
                    isEnabled1=true;
                    isEnabled2=true;
                    isEnabled3=true;
                    isEnabled4=true;
                    isEnabled9=false;
                    isEnabled10=false;
                    isEnabled11=false;
                    isEnabled12=false;
                    btn1.setBackgroundResource(R.drawable.myshape);
                    btn2.setBackgroundResource(R.drawable.myshape);
                    btn3.setBackgroundResource(R.drawable.myshape);
                    btn4.setBackgroundResource(R.drawable.myshape);
                    btn9.setBackgroundResource(R.drawable.disabled);
                    btn10.setBackgroundResource(R.drawable.disabled);
                    btn11.setBackgroundResource(R.drawable.disabled);
                    btn12.setBackgroundResource(R.drawable.disabled);
                    numBean4=new NumBean("D",btn12.getText().toString().trim());
                    numBean4.setK("D");
                    numBean4.setN(btn12.getText().toString().trim());
                    num.add(numBean4);
                    numkey.add("D");

                    click12=1;
                }
                else{
                    btn12.setBackgroundResource(R.drawable.disabled);
                }
            }
        });
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tw tw = new Tw();
                boolean iswujie = tw.hasSolution(Integer.valueOf(NumUtil.str(numlk[0])), Integer.valueOf(NumUtil.str(numlk[1])), Integer.valueOf(NumUtil.str(numlk[2])), Integer.valueOf(NumUtil.str(numlk[3])));
                if(iswujie==false){
                    score2=score2+1;
                    score.setText("得分:"+score2);
                    pause=true;
                    if(IsTTSOpen==true){
                        ttsTool.speak("恭喜你答对了，本题正确答案为无解。真棒。再接再厉哦。",MainActivity.this);
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("提示：");
                    builder.setMessage("恭喜你，答对了。本题正确答案为无解，真棒。加一分，再接再厉，继续加油哦！。");
                    builder.setCancelable(false);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                            numlk=NumUtil.Randoms(56);
                            img1.setImageResource(dr[numlk[0]]);
                            img2.setImageResource(dr[numlk[1]]);
                            img3.setImageResource(dr[numlk[2]]);
                            img4.setImageResource(dr[numlk[3]]);
                            btn9.setText(NumUtil.str(numlk[0]));
                            btn10.setText(NumUtil.str(numlk[1]));
                            btn11.setText(NumUtil.str(numlk[2]));
                            btn12.setText(NumUtil.str(numlk[3]));
                            tv1.setText(NumUtil.str(numlk[0]));
                            tv2.setText(NumUtil.str(numlk[1]));
                            tv3.setText(NumUtil.str(numlk[2]));
                            tv4.setText(NumUtil.str(numlk[3]));
                            btn1.setBackgroundResource(R.drawable.disabled);
                            btn2.setBackgroundResource(R.drawable.disabled);
                            btn3.setBackgroundResource(R.drawable.disabled);
                            btn4.setBackgroundResource(R.drawable.disabled);
                            btn9.setBackgroundResource(R.drawable.myshape);
                            btn10.setBackgroundResource(R.drawable.myshape);
                            btn11.setBackgroundResource(R.drawable.myshape);
                            btn12.setBackgroundResource(R.drawable.myshape);
                            isEnabled1=false;
                            isEnabled2=false;
                            isEnabled3=false;
                            isEnabled4=false;
                            isEnabled9=true;
                            isEnabled10=true;
                            isEnabled11=true;
                            isEnabled12=true;
                            click9=0;
                            click10=0;
                            click11=0;
                            click12=0;
                            count=120;
                            suanshi.setText("");
                            if(life!=0) {
                                if (finish == true) {
                                    finish = false;
                                }
                                if (pause == true) {
                                    restart = true;
                                    pause = false;
                                    initView();
                                }
                            }
                            if(IsTTSOpen==true&&life!=0){
                                ttsTool.speak("请看下一题。",MainActivity.this);
                            }
                        }
                    });
                    AlertDialog dialog = builder.create();
                    if(flag==true) {
                        dialog.show();
                    }
                }
                if(iswujie==true){
                    if(life>0) {
                        life = life - 1;
                    }
                    if(life==3){
                        img13.setBackgroundResource(R.drawable.pinkheart);
                        img12.setBackgroundResource(R.drawable.pinkheart);
                        img11.setBackgroundResource(R.drawable.pinkheart);
                    }
                    if(life==2){
                        img13.setBackgroundResource(R.drawable.grayheart);
                        img12.setBackgroundResource(R.drawable.pinkheart);
                        img11.setBackgroundResource(R.drawable.pinkheart);
                    }
                    if(life==1){
                        img13.setBackgroundResource(R.drawable.grayheart);
                        img12.setBackgroundResource(R.drawable.grayheart);
                        img11.setBackgroundResource(R.drawable.pinkheart);
                    }
                    if(life==0){
                        img13.setBackgroundResource(R.drawable.grayheart);
                        img12.setBackgroundResource(R.drawable.grayheart);
                        img11.setBackgroundResource(R.drawable.grayheart);
                    }

                    String res = tw.getSolution(Integer.valueOf(NumUtil.str(numlk[0])), Integer.valueOf(NumUtil.str(numlk[1])), Integer.valueOf(NumUtil.str(numlk[2])), Integer.valueOf(NumUtil.str(numlk[3])));
                    String res2=res.replaceAll("\\*","×");
                    String res3=res2.replaceAll("/","÷");
                    pause=true;
                    if(IsTTSOpen==true){
                        ttsTool.speak("不好意思，您答错了。继续加油哦。",MainActivity.this);
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("提示：");
                    builder.setMessage("错误，本题有解，而不是无解，其中一个正确结果为" + res3);
                    builder.setCancelable(false);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            if(life==0){
                                gameOver();
                            }
                            if(life>0) {
                                numlk = NumUtil.Randoms(56);
                                img1.setImageResource(dr[numlk[0]]);
                                img2.setImageResource(dr[numlk[1]]);
                                img3.setImageResource(dr[numlk[2]]);
                                img4.setImageResource(dr[numlk[3]]);
                                btn9.setText(NumUtil.str(numlk[0]));
                                btn10.setText(NumUtil.str(numlk[1]));
                                btn11.setText(NumUtil.str(numlk[2]));
                                btn12.setText(NumUtil.str(numlk[3]));
                                tv1.setText(NumUtil.str(numlk[0]));
                                tv2.setText(NumUtil.str(numlk[1]));
                                tv3.setText(NumUtil.str(numlk[2]));
                                tv4.setText(NumUtil.str(numlk[3]));
                                btn1.setBackgroundResource(R.drawable.disabled);
                                btn2.setBackgroundResource(R.drawable.disabled);
                                btn3.setBackgroundResource(R.drawable.disabled);
                                btn4.setBackgroundResource(R.drawable.disabled);
                                btn9.setBackgroundResource(R.drawable.myshape);
                                btn10.setBackgroundResource(R.drawable.myshape);
                                btn11.setBackgroundResource(R.drawable.myshape);
                                btn12.setBackgroundResource(R.drawable.myshape);
                                isEnabled1 = false;
                                isEnabled2 = false;
                                isEnabled3 = false;
                                isEnabled4 = false;
                                isEnabled9 = true;
                                isEnabled10 = true;
                                isEnabled11 = true;
                                isEnabled12 = true;
                                click9 = 0;
                                click10 = 0;
                                click11 = 0;
                                click12 = 0;
                                count = 120;
                                suanshi.setText("");
                            }
                            if(life!=0) {
                                if (finish == true) {
                                    finish = false;
                                }
                                if (pause == true) {
                                    restart = true;
                                    pause = false;
                                    initView();
                                }
                            }
                            if(IsTTSOpen==true&&life!=0){
                                ttsTool.speak("请看下一题。",MainActivity.this);
                            }
                        }
                    });
                    AlertDialog dialog = builder.create();
                    if(flag==true) {
                        dialog.show();
                    }
                }
            }
        });

    }

            public void gameOver(){
                end=true;
                sharedPreferences=getSharedPreferences("status",MODE_PRIVATE);
                IsTTSOpen=sharedPreferences.getBoolean("tts",true);
                bgm=sharedPreferences.getBoolean("bgm",true);
            if (num.size() > 0) {
                num.clear();
            }
            if(numkey.size()>0){
                numkey.clear();
            }
            pause=true;
                if(IsTTSOpen==true){
                    ttsTool.speak("对不起，您的生命值为0。游戏结束。分数为"+String.valueOf(score2)+"分，继续努力哦，争取得到更高的分数。",MainActivity.this);
                }
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("提示：");
            builder.setMessage("您在游戏中的3次机会已经用尽，游戏结束。加油哦，再接再厉，开动您的脑筋，下次争取拿到更高的分数哦。");
            builder.setCancelable(false);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    sharedPreferences=getSharedPreferences("status",MODE_PRIVATE);
                    datacount=StroreDoubleUtil.getDouble(sharedPreferences,"datanum",0);
                    sharedPreferences=getSharedPreferences("status",MODE_PRIVATE);
                    editor=sharedPreferences.edit();
                    editor.putString("gameover","gameover");
                    StroreDoubleUtil.putDouble(editor,"datanum",datacount+1);
                    editor.commit();
                    Access dbHelper = new Access(MainActivity.this);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    alldatas = DataBaseUtil.query(dbHelper, db);
                    if(alldatas.size()>0) {
                        if (score2 > Long.valueOf(alldatas.get(0))&&score2>0) {
                            sharedPreferences2 = getSharedPreferences("hi", MODE_PRIVATE);
                            editor2 = sharedPreferences2.edit();
                            editor2.putLong("hiscore", score2);
                            editor2.putBoolean("above", true);
                            editor2.commit();
                        }
                        if (score2 <= Long.valueOf(alldatas.get(0))) {
                            sharedPreferences2 = getSharedPreferences("hi", MODE_PRIVATE);
                            editor2 = sharedPreferences2.edit();
                            editor2.putBoolean("above", false);
                            editor2.commit();
                        }
                    }
                    if(alldatas.size()==0&&score2>0){
                        sharedPreferences2 = getSharedPreferences("hi", MODE_PRIVATE);
                        editor2 = sharedPreferences2.edit();
                        editor2.putLong("hiscore", score2);
                        editor2.putBoolean("above", true);
                        editor2.commit();
                    }
                    DataBaseUtil.AddRecord(dbHelper,db,String.valueOf(score2));
                    sharedPreferences2=getSharedPreferences("hi",MODE_PRIVATE);
                    isHigh=sharedPreferences2.getBoolean("above",false);
                    if(isHigh==true){
                        Intent intent=new Intent(MainActivity.this,Main6Activity.class);
                        ttsTool.stop();
                        startActivity(intent);
                    }
                    if(isHigh==false) {
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        ttsTool.stop();
                        startActivity(intent);
                    }
                }
            });
            AlertDialog dialog = builder.create();
                if(flag==true) {
                    dialog.show();
                }
        }
    public void overtime(){
        if(life>0) {
            life = life - 1;
        }
        if(life==3){
            img13.setBackgroundResource(R.drawable.pinkheart);
            img12.setBackgroundResource(R.drawable.pinkheart);
            img11.setBackgroundResource(R.drawable.pinkheart);
        }
        if(life==2){
            img13.setBackgroundResource(R.drawable.grayheart);
            img12.setBackgroundResource(R.drawable.pinkheart);
            img11.setBackgroundResource(R.drawable.pinkheart);
        }
        if(life==1){
            img13.setBackgroundResource(R.drawable.grayheart);
            img12.setBackgroundResource(R.drawable.grayheart);
            img11.setBackgroundResource(R.drawable.pinkheart);
        }
        if(life==0){
            img13.setBackgroundResource(R.drawable.grayheart);
            img12.setBackgroundResource(R.drawable.grayheart);
            img11.setBackgroundResource(R.drawable.grayheart);
        }
        once++;
        Tw tw = new Tw();
        boolean iswujie = tw.hasSolution(Integer.valueOf(NumUtil.str(numlk[0])), Integer.valueOf(NumUtil.str(numlk[1])), Integer.valueOf(NumUtil.str(numlk[2])), Integer.valueOf(NumUtil.str(numlk[3])));
        if(iswujie==true) {
            String res = tw.getSolution(Integer.valueOf(NumUtil.str(numlk[0])), Integer.valueOf(NumUtil.str(numlk[1])), Integer.valueOf(NumUtil.str(numlk[2])), Integer.valueOf(NumUtil.str(numlk[3])));
            String res2=res.replaceAll("\\*","×");
            String res3=res2.replaceAll("/","÷");
            if(IsTTSOpen==true&&once==1){
                ttsTool.speak("不好意思，您的反应太慢了。答题时间消耗殆尽。加油哦，下一题争取快点想出答案哦",MainActivity.this);
            }
            if(once==1) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("提示：");
                builder.setMessage("不好意思，你的反应太慢了，超时了，正确答案是" + res3);
                builder.setCancelable(false);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (life == 0) {
                            gameOver();
                        }
                        if (life > 0) {
                            numlk = NumUtil.Randoms(56);
                            img1.setImageResource(dr[numlk[0]]);
                            img2.setImageResource(dr[numlk[1]]);
                            img3.setImageResource(dr[numlk[2]]);
                            img4.setImageResource(dr[numlk[3]]);
                            btn9.setText(NumUtil.str(numlk[0]));
                            btn10.setText(NumUtil.str(numlk[1]));
                            btn11.setText(NumUtil.str(numlk[2]));
                            btn12.setText(NumUtil.str(numlk[3]));
                            tv1.setText(NumUtil.str(numlk[0]));
                            tv2.setText(NumUtil.str(numlk[1]));
                            tv3.setText(NumUtil.str(numlk[2]));
                            tv4.setText(NumUtil.str(numlk[3]));
                            btn1.setBackgroundResource(R.drawable.disabled);
                            btn2.setBackgroundResource(R.drawable.disabled);
                            btn3.setBackgroundResource(R.drawable.disabled);
                            btn4.setBackgroundResource(R.drawable.disabled);
                            btn9.setBackgroundResource(R.drawable.myshape);
                            btn10.setBackgroundResource(R.drawable.myshape);
                            btn11.setBackgroundResource(R.drawable.myshape);
                            btn12.setBackgroundResource(R.drawable.myshape);
                            isEnabled1 = false;
                            isEnabled2 = false;
                            isEnabled3 = false;
                            isEnabled4 = false;
                            isEnabled9 = true;
                            isEnabled10 = true;
                            isEnabled11 = true;
                            isEnabled12 = true;
                            click9 = 0;
                            click10 = 0;
                            click11 = 0;
                            click12 = 0;
                            count = 120;
                            once = 0;
                            dialogshowtime = 0;
                            suanshi.setText("");
                        }
                        if (life != 0) {
                            if (finish == true) {
                                finish = false;
                            }
                            if (pause == true) {
                                restart = true;
                                pause = false;
                                initView();
                            }
                        }
                        if (IsTTSOpen == true && life != 0) {
                            ttsTool.speak("请看下一题。", MainActivity.this);
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                if (flag == true) {
                    dialog.show();
                }
            }
        }
        if(iswujie==false) {
            if (IsTTSOpen == true && once == 1) {
                ttsTool.speak("不好意思，您的反应太慢了。答题时间消耗殆尽。本题正确答案为无解。加油哦，下一题争取快点想出答案哦", MainActivity.this);
            }
            if (once == 1) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("提示：");
                builder.setMessage("不好意思，你的反应太慢了，超时了，正确答案是无解。");
                builder.setCancelable(false);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (life == 0) {
                            gameOver();
                        }
                        if(life>0) {
                            numlk = NumUtil.Randoms(56);
                            img1.setImageResource(dr[numlk[0]]);
                            img2.setImageResource(dr[numlk[1]]);
                            img3.setImageResource(dr[numlk[2]]);
                            img4.setImageResource(dr[numlk[3]]);
                            btn9.setText(NumUtil.str(numlk[0]));
                            btn10.setText(NumUtil.str(numlk[1]));
                            btn11.setText(NumUtil.str(numlk[2]));
                            btn12.setText(NumUtil.str(numlk[3]));
                            tv1.setText(NumUtil.str(numlk[0]));
                            tv2.setText(NumUtil.str(numlk[1]));
                            tv3.setText(NumUtil.str(numlk[2]));
                            tv4.setText(NumUtil.str(numlk[3]));
                            btn1.setBackgroundResource(R.drawable.disabled);
                            btn2.setBackgroundResource(R.drawable.disabled);
                            btn3.setBackgroundResource(R.drawable.disabled);
                            btn4.setBackgroundResource(R.drawable.disabled);
                            btn9.setBackgroundResource(R.drawable.myshape);
                            btn10.setBackgroundResource(R.drawable.myshape);
                            btn11.setBackgroundResource(R.drawable.myshape);
                            btn12.setBackgroundResource(R.drawable.myshape);
                            isEnabled1 = false;
                            isEnabled2 = false;
                            isEnabled3 = false;
                            isEnabled4 = false;
                            isEnabled9 = true;
                            isEnabled10 = true;
                            isEnabled11 = true;
                            isEnabled12 = true;
                            click9 = 0;
                            click10 = 0;
                            click11 = 0;
                            click12 = 0;
                            count = 120;
                            once = 0;
                            dialogshowtime = 0;
                            suanshi.setText("");
                        }
                        if (life != 0) {
                            if (finish == true) {
                                finish = false;
                            }
                            if (pause == true) {
                                restart = true;
                                pause = false;
                                initView();
                            }
                        }
                        if (IsTTSOpen == true && life != 0) {
                            ttsTool.speak("请看下一题。", MainActivity.this);
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                if (flag == true) {
                    dialog.show();
                }
            }
        }
    }
    public void initView() {

        handler.sendEmptyMessageDelayed(100, 1000);
    }
    public void fangfa(){
        if(count>=0){
            if(count==24){
                if(IsTTSOpen==true&&pause==false){
                    ttsTool.speak("注意哦，倒计时还剩下24秒哦。",MainActivity.this);
                }
            }
            if(count==10){
                if(IsTTSOpen==true&&pause==false){
                    ttsTool.speak("抓紧时间哦，倒计时只有10秒了。",MainActivity.this);
                }
            }
            int min=count/60;
            int sec=count%60;
            if(sec>=0&&sec<10) {
                remaintime.setText(String.valueOf(min)+":"+"0"+String.valueOf(sec));
            }
            if(sec>=10&&sec<60){
                remaintime.setText(String.valueOf(min)+":"+String.valueOf(sec));
            }

            if (pause == false) {
                count--;
                handler.sendEmptyMessageDelayed(100, 1000);
            } else {
                if(restart==true){
                    count = 120;
                    handler.sendEmptyMessageDelayed(100, 1000);
                    restart = false;
                }
            }
            if(restart==true){
                count = 120;
                restart = false;
            }
            if (count==0){
                dialogshowtime++;
                if(dialogshowtime==1) {
                    overtime();
                    pause = true;
                    finish = true;
                }
            }
            if(finish==true&&restart==true){
                count = 120;
                handler.sendEmptyMessageDelayed(100, 1000);
                restart = false;
                finish=false;
            }

        }else {

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus==true){
            sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);
            IsTTSOpen = sharedPreferences.getBoolean("tts", true);
            ttsTool=Total.getTtsTool();
            flag=true;
            if(IsTTSOpen==true&&end==false&&chu==false){
                 chu=true;
                ttsTool.speak("请看本题",MainActivity.this);
            }
        }
        if(hasFocus==false){
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        AudioManager audioManager  = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(IsTTSOpen==true){
                ttsTool.speak("你真的要退出本局游戏吗？如果退出，很遗憾，本局游戏分数清零，你将得不到如何分数。",MainActivity.this);
            }
            AlertDialog.Builder alertbBuilder=new AlertDialog.Builder(this);
            alertbBuilder.setCancelable(false);
            alertbBuilder.setTitle("确定中途退出游戏？").setMessage("你真的要退出本局游戏吗？如果退出，很遗憾，本局游戏分数清零，你将得不到如何分数。").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                    ttsTool.stop();
                    startActivity(intent);
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
