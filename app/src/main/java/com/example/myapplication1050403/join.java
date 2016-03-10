package com.example.myapplication1050403;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class join extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //製作返回鍵


    }



    public boolean onOptionsItemSelected(MenuItem item) {    // 點擊返回鍵會跳回Main  用intent切換
        switch(item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent();
                intent.setClass(join.this, MainActivity.class);
                startActivity(intent);
                join.this.finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
