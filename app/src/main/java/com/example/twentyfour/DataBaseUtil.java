package com.example.twentyfour;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DataBaseUtil {
    public static void AddRecord(Access acc,SQLiteDatabase db,String num){
       db=acc.getWritableDatabase();
       db.execSQL("insert into score3(shuju) values ("+Long.valueOf(num)+")",new Object[]{});
       db.close();
    }
    public static ArrayList<String> query(Access acc,SQLiteDatabase db){
        ArrayList<String> datas=new ArrayList<String>();
        db=acc.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from score3 order by shuju desc",null);
        if(cursor!=null&&cursor.getCount()>0){
           while(cursor.moveToNext()){
               String data=cursor.getString(1);
               datas.add(data);
           }
        }
        return datas;
    }
    public static int getCount(Access acc,SQLiteDatabase db){
        int counta=-1;
        db=acc.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from score3",null);
        if(cursor!=null){
            counta=cursor.getCount();
        }
        return counta;
    }
    public static void clear(Access acc,SQLiteDatabase db){
        db=acc.getWritableDatabase();
        db.execSQL("delete from score3");
        db.delete("score3","shuju > -1",new String[]{});
        db.close();
    }
}
