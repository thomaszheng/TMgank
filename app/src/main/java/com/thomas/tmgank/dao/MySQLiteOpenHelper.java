package com.thomas.tmgank.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by thomas on 2015/10/13.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "tmgank.db";
    public MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table collection (_id integer primary key autoincrement,url text not null,title text not null,time long not null);";
       db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
