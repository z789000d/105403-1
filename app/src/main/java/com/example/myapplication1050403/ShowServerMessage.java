package com.example.myapplication1050403;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ShowServerMessage extends ActionBarActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_server_message);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //製作返回鍵

        textView = (TextView)findViewById(R.id.text_view);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance()) ; /* 增加滾動條*/


        Thread thread = new Thread(){
            public void run(){

                if(isNetworkAvailable()){
                    // method 1: HttpURLConnection

                    try {
                        URL url = new URL("http://www.ntub.edu.tw/bin/home.php");
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        readStream(con.getInputStream());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // method 2: HttpClient
                   /* GetServerMessage message = new GetServerMessage();
                    String msg = message.stringQuery("https://zh.wikipedia.org/zh-tw/Wikipedia");
                    textView.setText("Server message is "+msg);
                } else {
                    textView.setText("Network is not available!"); */
                }
            }


        };
        thread.start();

    }

    public boolean onOptionsItemSelected(MenuItem item) {       // 點擊返回鍵會跳回Main 用intent切換
        switch(item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent();
                intent.setClass(ShowServerMessage.this, MainActivity.class);
                startActivity(intent);
                ShowServerMessage.this.finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_show_server_message, menu);
        return true;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    private void readStream(InputStream in) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            StringBuffer sb = new StringBuffer("");
            String NL = System.getProperty("line.separator");

            while ((line = reader.readLine()) != null) {
                sb.append(line + NL);
            }

            if (reader != null) {
                String page = sb.toString();
                textView.setText("http reaponse is: "+page);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
