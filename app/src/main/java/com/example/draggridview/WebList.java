package com.example.draggridview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;

public class WebList extends Activity {

    ListView listview;
    ArrayAdapter<String> MyArrayAdapter;
    private SQLiteDatabase db;
    MyDataDB dbHelper;
    public Activity activity;
    String strGroup[] = new String[50];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_list);
        activity = this;
        dbHelper = new MyDataDB(this);
        db = dbHelper.getWritableDatabase();



        listview = (ListView)findViewById(R.id.list);

        MyArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listview.setAdapter(MyArrayAdapter);


        Cursor cursor = dbHelper.select();	//取得SQLite類別的回傳值:Cursor物件
        cursor.moveToFirst();
        int x = 0 ;
        if(MyArrayAdapter.isEmpty()) // 如果適配器是空的直接新增進去

            {

                MyArrayAdapter.add(cursor.getString(2));
                MyArrayAdapter.notifyDataSetChanged();
                strGroup[x]= cursor.getString(2);
                x = x +1;


            }
        String test = cursor.getString(2);
        cursor.moveToNext();


        do {
//            if (cursor.getString(2).equals(null) || cursor.getString(2).equals("") ){
//
//                cursor.moveToFirst();
//                MyArrayAdapter.add(cursor.getString(2));
//                MyArrayAdapter.notifyDataSetChanged();
//                strGroup[x]= cursor.getString(2);
//                x= x +1;
//
//            }
//            else
//            {
                if (test.equals(cursor.getString(2))) { //做盼對在新增進去適配器 去除重複


                } else {
                    test = cursor.getString(2);
                    MyArrayAdapter.add(cursor.getString(2));
                    MyArrayAdapter.notifyDataSetChanged();
                    strGroup[x]= cursor.getString(2);
                    x = x +1;
//                }

            }

        }while (cursor.moveToNext());



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = strGroup[i];
                // 整理要顯示的文字。
                String result = "內容: " + text;
                // 顯示。
                Toast.makeText(activity, result, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.putExtra("edit",text);
                intent.setClass(WebList.this,ShowInWebViewNext.class);
                startActivity(intent);
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                String text = strGroup[i];
                Log.d("123",text);
                dbHelper.del(text);
                MyArrayAdapter.remove(text);
                MyArrayAdapter.notifyDataSetChanged();



                return false;
            }
        });


    }
}
