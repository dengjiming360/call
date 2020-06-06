package com.example.wubi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.alibaba.fastjson.JSONReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;



public class MyUtil {
    ArrayList<String>newallhanzi=new ArrayList<String>();
    ArrayList<String>newallzigen=new ArrayList<String>();
    ArrayList<String>allhanzi=new ArrayList<String>();
    ArrayList<String>allzigen=new ArrayList<String>();
       public void train(ArrayList<String> b, final Context context2) {
           ApiService apiService = RetrofitManager.getInstance(ApiPath.a, context2).setCreate(ApiService.class);
           String[] array = (String[]) b.toArray(new String[b.size()]);
           StringBuffer[] stringBuffers = new StringBuffer[b.size() / 20 + 1];
           String[] str = new String[b.size() / 20 + 1];
           Observable<ResponseBody>[] observables = new Observable[b.size() / 20 + 1];
           for (int i = 0; i < (b.size() / 20 + 1); i++) {
               stringBuffers[i] = new StringBuffer("");
               for (int j = 0; j < 20; j++) {
                   int k = b.size() - i * 20;
                   if(i*20+j<b.size()) {
                       stringBuffers[i] = stringBuffers[i].append(b.get(i * 20 + j));
                       str[i] = stringBuffers[i].toString();
                   }
                   if (k < 0) {
                       break;
                   }

               }
           }
           for(int l=0;l<str.length;l++){
               Log.e("忽必来",str[l]);
           }
           for(int i=0;i<str.length;i++){
               observables[i]=apiService.getHanzi("ab8502ab1b7045d2a939baf8575786dc",str[i]);
           }
            Observable.concatArray(observables).observeOn(Schedulers.newThread()).subscribeOn(Schedulers.newThread()).subscribe(new Observer<ResponseBody>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(ResponseBody responseBody) {
                       shujuku(context2,responseBody.byteStream());
                       newallhanzi=getSingle(allhanzi);
                       newallzigen=allzigen;
                       Log.e("刘禅",newallhanzi.size()+"#"+newallzigen.size());
                }

                @Override
                public void onError(Throwable e) {
                      Log.e("曹丕",e.getMessage());
                }

                @Override
                public void onComplete() {

                }
            });
       }
       public void shujuku(Context context,InputStream inputStream){


           DataBase db=new DataBase(context);
           SQLiteDatabase dba=db.getWritableDatabase();
           try {
               JSONReader jsonReader=new JSONReader(new InputStreamReader(inputStream,"utf-8"));
               jsonReader.startObject();
               while(jsonReader.hasNext()){
                   String key=jsonReader.readString();
                   if(key.equals("error_code")){
                       String a=jsonReader.readObject().toString();
                   }
                   if(key.equals("reason")){
                       String b=jsonReader.readObject().toString();
                   }
                   if(key.equals("result")){
                       jsonReader.startArray();
                       while(jsonReader.hasNext()){
                           jsonReader.startObject();
                           while(jsonReader.hasNext()) {
                               String key3=jsonReader.readString();
                               if (key3.equals("word")) {
                                   String a1 = jsonReader.readObject().toString();
                                   allhanzi.add(a1);
                                   Log.e("汉字1", a1);
                               }
                               if (key3.equals("zigen")) {
                                   String a2 = jsonReader.readObject().toString();
                                   allzigen.add(a2);
                                   Log.e("汉字2", a2);
                               }
                           }
                           jsonReader.endObject();
                       }
                       jsonReader.endArray();
                   }
               }
               jsonReader.endObject();


                  /* for (int i = 0; i < newallhanzi.size(); i++) {

                           DataBaseUtil.AddRecord(db, dba, newallhanzi.get(i).trim(), newallzigen.get(i).trim());


                   }
                   if (DataBaseUtil.query(db, dba, newallhanzi.get(0)).size() == 1) {
                       Log.e("司马光", DataBaseUtil.query(db, dba, newallhanzi.get(0)).get(0));
                   }*/
           } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
           }
       }

    public static ArrayList getSingle(ArrayList list){
        ArrayList newList = new ArrayList();
        Iterator it = list.iterator();
        while(it.hasNext()){
            Object obj = it.next();
            if(!newList.contains(obj)){
                newList.add(obj);
            }
        }
        return newList;
    }



    }

