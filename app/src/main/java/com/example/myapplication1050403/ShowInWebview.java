package com.example.myapplication1050403;

import java.net.URL;
import java.net.URLDecoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShowInWebview extends ActionBarActivity {

    URL url;

    WebView w01,w02;
    EditText ed01;
    Button  b01;
    Thread th;
    String  geturl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_in_webview);
        w01= (WebView) findViewById(R.id.w01);
        w01.getSettings().setJavaScriptEnabled(true); //可解讀javascript
        w01.getSettings().setSupportZoom(true);    //可放大縮小
        w01.getSettings().setBuiltInZoomControls(true); //可progress

        w01.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {

                setTitle("Loading..." + progress + "%");
                setProgress(progress * 100);

                if (progress == 100) {
                    setTitle(R.string.app_name);
                    Toast toast = Toast.makeText(ShowInWebview.this, "網頁載入完成", Toast.LENGTH_SHORT);

                    toast.show();

                }

            }


        });
        w02 = (WebView) findViewById(R.id.w02);
        w02.getSettings().setJavaScriptEnabled(true); //可解讀javascript
        w02.getSettings().setSupportZoom(true);    //可放大縮小
        w02.getSettings().setBuiltInZoomControls(true); //可progress


        ed01 = (EditText) findViewById(R.id.ed01);
        b01 = (Button) findViewById(R.id.b01);

        b01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                geturl = ed01.getText().toString();
                ed01.setText(null); //重設內容
                th = new Thread(r0);                //執行緒
                th.start();                    //讓執行緒開始工作
            }
        });






    }
    private Runnable r0=new Runnable(){
        Element title[] = new Element[10];
        String te[] = new String[10];
        String te00;

        public void run(){
            try {
                url=new URL( geturl);
                Document doc =  Jsoup.parse(url, 3000);        //連結該網址

                for (int x=5;x<7;x++) {  //設定一個for迴圈裡面放陣列動態去抓每一段的li

                    title[x] = doc.select("a").get(x);//抓取為tr且有class屬性的所有Tag get動態抓第幾段li
                    te[x] = title[x].toString();


//                for(int i=0;i<title.size();i++){            //用FOR個別抓取選定的Tag內容
//                    Elements title_select=title.get(i).select("title");//選擇第i個後選取所有為td的Tag
//                    te01=title_select.get(0).text();        //只抓取第 0,2,3 Tag的文字
//                    te02=title_select.get(2).text();
//                    te03=title_select.get(3).text();
                }
                    runOnUiThread(new Runnable() {             //將內容交給UI執行緒做顯示
                        public void run() {
                            w01.loadDataWithBaseURL("", te[5], "text/html", "UTF-8", "");
                            Log.e("123", te[5]);
                            w02.loadDataWithBaseURL("", te[6], "text/html", "UTF-8", "");
                           Log.e("123", te[6]);
                        }
                    });
                    Thread.sleep(100);    //避免執行緒跑太快而UI執行續顯示太慢,覆蓋掉te01~03內容所以設個延遲,也可以使用AsyncTask-異步任務

//             }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };
}