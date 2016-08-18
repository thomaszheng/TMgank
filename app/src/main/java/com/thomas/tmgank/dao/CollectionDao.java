package com.thomas.tmgank.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thomas.tmgank.model.Collection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by thomas on 2015/10/23.
 */
public class CollectionDao {
    private MySQLiteOpenHelper helper;
    private SQLiteDatabase database;
    public  final String TABLE_NAME = "collection";
    public  final String URL = "url";
    public  final String TITLE = "title";
    public  final String TIME = "time";
    public CollectionDao(Context context) {
        this.helper=new MySQLiteOpenHelper(context);
    }


    public void insert(Collection collection){
        database=helper.getWritableDatabase();
        ContentValues cv = new ContentValues(3);
        cv.put(URL, collection.url);
        cv.put(TITLE, collection.title);
        cv.put(TIME, collection.time.getTime());
        database.insert(TABLE_NAME, null, cv);
        database.close();
    }

    public void delete(String url) {
        database=helper.getWritableDatabase();
        database.delete(TABLE_NAME, "url=?", new String[]{url});
        database.close();
    }

    public List<Collection> select() {
        database=helper.getWritableDatabase();

            String sql = "select * from collection order by time desc;";
            Cursor cursor = database.rawQuery(sql,null);
            int count = cursor.getCount();
            List<Collection> collections = new ArrayList<Collection>(count);
            cursor.moveToFirst();
            while (cursor.moveToNext()){
                String url = cursor.getString(cursor.getColumnIndex(URL));
                String title = cursor.getString(cursor.getColumnIndex(TITLE));
                long time = cursor.getLong(cursor.getColumnIndex(TIME));
                collections.add(new Collection(url, title, new Date(time)));
            }
        database.close();
        return collections;
    }

    public boolean isExist(String url) {
        database=helper.getWritableDatabase();
        String sql = "select * from collection where url=?";
        Cursor cursor = database.rawQuery(sql, new String[]{url});
        boolean flag = false;
        if (cursor!=null && cursor.getCount() > 0) {
            flag = true;
        }
        database.close();
        return flag;
    }

}
