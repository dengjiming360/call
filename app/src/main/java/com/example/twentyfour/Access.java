package com.example.twentyfour;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;





    public class Access extends SQLiteOpenHelper {
        private static final String DBNAME = "score3.db";
        private static final int VERSION = 1;

        public Access(Context context) {
            super(context, DBNAME, null, 1);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table score3 ( _id  integer PRIMARY KEY autoincrement,shuju bigint);");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }

}
