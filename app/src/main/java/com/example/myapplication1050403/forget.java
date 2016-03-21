package com.example.myapplication1050403;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;


public class forget extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //製作返回鍵
    }

    public boolean onOptionsItemSelected(MenuItem item) {    // 點擊返回鍵會跳回Main  用intent切換
        switch(item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent();
                intent.setClass(forget.this, MainActivity.class);
                startActivity(intent);
                forget.this.finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();//取x軸
        int y = (int) event.getY();//取y軸
        int action = event.getAction();//取整數
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                setTitle("按下:" + x + "," + y);
                break;
            case MotionEvent.ACTION_MOVE:
                setTitle("平移:" + x + "," + y);
                break;
            case MotionEvent.ACTION_UP:
                setTitle("彈起:" + x + "," + y);
                break;
        }

        return true;// super.onTouchEvent(event);
    }


}

