package com.example.myapplication1050403;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    TextView text1,text2 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        

        TextView text1 = (TextView) findViewById(R.id.text1);
        TextView text2 = (TextView) findViewById(R.id.text2);
        TextView text3 = (TextView) findViewById(R.id.text3);
        Button button1 = (Button) findViewById(R.id.button1);


        button1.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent();
                intent.setClass(MainActivity.this,ShowServerMessage.class);
                startActivity(intent);
                MainActivity.this.finish();
             /*點選button1進入顯示原始碼畫面*/
            }
        });


        text1.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent();
                intent.setClass(MainActivity.this, join.class);
                startActivity(intent);
                MainActivity.this.finish();
                /*點選加入會員進入加入會員*/

            }
        });

        text2.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent();
                intent.setClass(MainActivity.this, forget.class);
                startActivity(intent);
                MainActivity.this.finish();
              /*點選忘記密馬進入忘記密碼*/



            }

       });

        text3.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Version.class);
                startActivity(intent);
                MainActivity.this.finish();
              /*版型*/



            }

        });


    }





}
