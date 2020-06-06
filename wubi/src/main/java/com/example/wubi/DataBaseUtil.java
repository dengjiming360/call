package com.example.wubi;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DataBaseUtil {
    public static void AddRecord(DataBase acc, SQLiteDatabase db, String hanzi,String zigen){
        db=acc.getWritableDatabase();
        db.execSQL(  "insert into wubi (hanzi, zigen) values(?, ?)", new String[] {hanzi, zigen});
        db.close();
    }
    public static ArrayList<String> query(DataBase acc, SQLiteDatabase db,String hanzi){
        String result="";
        ArrayList<String>zigens=new ArrayList<String>();
        db = acc.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from wubi where hanzi = ?", new String[] {hanzi});
         while(cursor.moveToNext()){
             result=cursor.getString(cursor.getColumnIndex("zigen"));
             zigens.add(result);
         }
        cursor.close();
        db.close();
        return zigens;
    }
   /* public static int getCount(DataBase acc,SQLiteDatabase db){
        int counta=-1;
        db=acc.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from score3",null);
        if(cursor!=null){
            counta=cursor.getCount();
        }
        return counta;
    }*/
    public static void clear(DataBase acc,SQLiteDatabase db){
        db=acc.getWritableDatabase();
        db.execSQL("delete from wubi");
        db.delete("wubi","",new String[]{});
        db.close();
    }
}
