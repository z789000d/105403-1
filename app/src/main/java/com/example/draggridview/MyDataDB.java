package com.example.draggridview;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by wei on 2016/9/19.
 */
public class MyDataDB extends SQLiteOpenHelper {

    private final static String DATABASE_NAME="sec_db";
    private final static int DATABASE_VERSION=1;
    private final static String TABLE_NAME="sec_pwd";
    public final static String FIELD_ID="_id";
    public final static String FIELD_DATA="sec_Data";
    ContentValues cv=new ContentValues();
    String[] webxml;

    public MyDataDB(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    // 需要資料庫的元件呼叫這個方法，這個方法在一般的應用都不需要修改

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 建立應用程式需要的表格
        // 待會再回來完成它

        String sql="Create table "+TABLE_NAME+"("+FIELD_ID+" integer primary key autoincrement,"
                +FIELD_DATA+" text );";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        String sql=" DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);

    }

    public long insert(String Title)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        cv.put(FIELD_DATA, Title);
        long row=db.insert(TABLE_NAME, null, cv);
        //Log.d("ADD", row+"");
        return row;

    }

    public Cursor select()
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(TABLE_NAME, null, null, null, null, null,"_id");

        cursor.moveToFirst();
        String str;
        do {
            str = "id" + cursor.getString(0) + "\n";
            str+= "data" + cursor.getString(1) + "\n";
            Log.d("1211",str);

        }while (cursor.moveToNext());

        return cursor;
    }

    public void delete(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String where=FIELD_ID+"=?";
        String[] whereValue={Integer.toString(id)};
        db.delete(TABLE_NAME, where, whereValue);
    }


}
