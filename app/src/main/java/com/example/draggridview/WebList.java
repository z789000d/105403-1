package com.example.draggridview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class WebList extends Activity {

    ListView listview;
    ArrayAdapter<String> MyArrayAdapter;
    private SQLiteDatabase db;
    MyDataDB dbHelper;
    public Activity activity;
    String strGroup[] = new String[50];
    String remove[] = new String[100];
    Button bt01;
    Cursor cursor;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_list);

        final RelativeLayout background = (RelativeLayout) findViewById(R.id.back);
        background.setBackgroundColor(Color.rgb(188,195,48));

        activity = this;
        dbHelper = new MyDataDB(this);
        db = dbHelper.getWritableDatabase();


        listview = (ListView) findViewById(R.id.list);
        bt01 = (Button) findViewById(R.id.button3);
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/wt040.ttf");
        bt01.setTypeface(face);

        MyArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listview.setAdapter(MyArrayAdapter);

        bt01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(WebList.this,MainView.class);
                startActivity(intent);
            }
        });
        try {
             cursor = dbHelper.select();
            //取得SQLite類別的回傳值:Cursor物件

        cursor.moveToFirst();

        if (MyArrayAdapter.isEmpty()) // 如果適配器是空的直接新增進去

        {

            MyArrayAdapter.add(cursor.getString(2));
            MyArrayAdapter.notifyDataSetChanged();



        }
        String test = cursor.getString(2);
        cursor.moveToNext();

        do {
            if (test.equals(cursor.getString(2))) { //做盼對在新增進去適配器 去除重複


            } else {
                test = cursor.getString(2);
                MyArrayAdapter.add(cursor.getString(2));
                MyArrayAdapter.notifyDataSetChanged();


            }

        } while (cursor.moveToNext());
        }catch (java.lang.RuntimeException e)
        {


//            Toast.makeText(activity, "請先新增版行", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent();
//            intent.setClass(WebList.this, MainView.class);
//            startActivity(intent);

        }

        for (int i = 0; i <= listview.getAdapter().getCount(); i++)  //外循环是循环的次数
        {
            for (int j = listview.getAdapter().getCount() - 1 ; j > i; j--)  //内循环是 外循环一次比较的次数
            {
                if (listview.getAdapter().getItem(i).toString().equals( listview.getAdapter().getItem(j).toString())) {

                    MyArrayAdapter.remove(listview.getAdapter().getItem(j).toString());


                }
                else
                {

                }
            }
        }

        for(int i = 0 ; i<=listview.getAdapter().getCount()-1;i++)
        {
            strGroup[i]=listview.getAdapter().getItem(i).toString();
        }


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = strGroup[i];
                // 整理要顯示的文字。
                String result = "內容: " + text;
                // 顯示。
                Toast.makeText(activity, result, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.putExtra("edit", text);
                intent.setClass(WebList.this, ShowInWebViewNext.class);
                startActivity(intent);
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {


                dialog(i);


                return true;
            }
        });


    }

    private void dialog(final int i) {

        AlertDialog.Builder builder = new AlertDialog.Builder(WebList.this); //創建訊息方塊

        builder.setMessage("確定要刪除？");

        builder.setTitle("刪除");

        builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which) {

                String text = strGroup[i];
                Log.d("123", text);
                dbHelper.del(text);
                MyArrayAdapter.remove(text);
                MyArrayAdapter.notifyDataSetChanged();
                for(int x = 0 ; x<=listview.getAdapter().getCount()-1;x++)
                {
                    strGroup[x]=listview.getAdapter().getItem(x).toString();
                }


            }

        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }

        });

        builder.create().show();

    }


}
