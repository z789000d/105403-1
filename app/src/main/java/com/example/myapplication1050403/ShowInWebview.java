package com.example.myapplication1050403;

import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnTouchListener;

public class ShowInWebview extends ActionBarActivity {

    URL url;

    WebView w01,w02,w03,w04,w05,w06,w07,w08,w09,w010,w011;
    EditText ed01;
    Button  b01;
    Thread th;
    String  geturl;
    DisplayMetrics metrics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_in_webview);
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics); // 抓螢幕大小



        w01= (WebView) findViewById(R.id.w01);
        w01.getSettings().setJavaScriptEnabled(true); //可解讀javascript
        w01.getSettings().setSupportZoom(true);    //可放大縮小
//        w01.getSettings().setBuiltInZoomControls(true); //可progress

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
       // w02.getSettings().setBuiltInZoomControls(true); //可progress

        w03 = (WebView) findViewById(R.id.w03);
        w03.getSettings().setJavaScriptEnabled(true); //可解讀javascript
        w03.getSettings().setSupportZoom(true);    //可放大縮小


        w04 = (WebView) findViewById(R.id.w04);
        w04.getSettings().setJavaScriptEnabled(true); //可解讀javascript
        w04.getSettings().setSupportZoom(true);    //可放大縮小

        w05 = (WebView) findViewById(R.id.w05);
        w05.getSettings().setJavaScriptEnabled(true); //可解讀javascript
        w05.getSettings().setSupportZoom(true);    //可放大縮小

        w06 = (WebView) findViewById(R.id.w06);
        w06.getSettings().setJavaScriptEnabled(true); //可解讀javascript
        w06.getSettings().setSupportZoom(true);    //可放大縮小


        w07 = (WebView) findViewById(R.id.w07);
        w07.getSettings().setJavaScriptEnabled(true); //可解讀javascript
        w07.getSettings().setSupportZoom(true);    //可放大縮小

        w08 = (WebView) findViewById(R.id.w08);
        w08.getSettings().setJavaScriptEnabled(true); //可解讀javascript
        w08.getSettings().setSupportZoom(true);    //可放大縮小

        w09 = (WebView) findViewById(R.id.w09);
        w09.getSettings().setJavaScriptEnabled(true); //可解讀javascript
        w09.getSettings().setSupportZoom(true);    //可放大縮小

        w010 = (WebView) findViewById(R.id.w010);
        w010.getSettings().setJavaScriptEnabled(true); //可解讀javascript
        w010.getSettings().setSupportZoom(true);    //可放大縮小

        w011 = (WebView) findViewById(R.id.w011);
        w011.getSettings().setJavaScriptEnabled(true); //可解讀javascript
        w011.getSettings().setSupportZoom(true);    //可放大縮小

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



        w01.setOnTouchListener(new Touch());
        w02.setOnTouchListener(new Touch());
        w03.setOnTouchListener(new Touch());
        w04.setOnTouchListener(new Touch());
        w05.setOnTouchListener(new Touch());
        w06.setOnTouchListener(new Touch());
        w07.setOnTouchListener(new Touch());
        w08.setOnTouchListener(new Touch());
        w09.setOnTouchListener(new Touch());
        w010.setOnTouchListener(new Touch());
        w011.setOnTouchListener(new Touch());








    }

    private class  Touch implements OnTouchListener { //設定全部的touch事件
        private float x, y;    // 原本圖片存在的X,Y軸位置
        private float ux, uy;
        private int mx, my; // 圖片被拖曳的X ,Y軸距離長度

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            long time = event.getEventTime()-event.getDownTime();

            if  (event.getAction()==MotionEvent.ACTION_DOWN ) {          //判斷觸控的動作

                x = event.getX();                  //觸控的X軸位置
                y = event.getY();                  //觸控的Y軸位置
//                        int[] location = new int[2];
//                        w01.getLocationOnScreen(location);           //抓圖片在螢幕的座標img
//                        int a = location[0];
//                        int b = location[1];
                Log.d("觸空的位置", String.valueOf(x) + "~~" + String.valueOf(y));


            }
            if (event.getAction() == MotionEvent.ACTION_MOVE && time > 100) {// 移動圖片時

                //getX()：是獲取當前控件(View)的座標
                //getRawX()：是獲取相對顯示螢幕左上角的座標
                mx = (int) (event.getRawX() - x);
                my = (int) (event.getRawY() - y-(metrics.heightPixels/4.784));
                v.layout(mx, my, mx + v.getWidth(), my + v.getHeight());
                Log.d("移動的距離", String.valueOf(mx) + "~~" + String.valueOf(my));


            }


            if (event.getAction()==MotionEvent.ACTION_UP){
                ux = event.getX();
                uy = event.getY();
                Log.d("3333",String.valueOf(time));

            }

            if (time < 100 ){
                return  false;
            }
            else return true;



        }
    };
    private Runnable r0=new Runnable(){
        Element title[] = new Element[100];
        String te[] = new String[100];
        String te00;

        public void run(){
            try {
                url=new URL( geturl);
                Document doc =  Jsoup.parse(url, 3000);        //連結該網址

                for (int x=1;x<12 ;x++) {  //設定一個for迴圈裡面放陣列動態去抓每一段的a

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
                            w01.loadDataWithBaseURL("", te[1], "text/html", "UTF-8", "");
                            Log.e("123", te[1]);
                            w02.loadDataWithBaseURL("", te[2], "text/html", "UTF-8", "");
                           Log.e("123", te[2]);
                            w03.loadDataWithBaseURL("", te[3], "text/html", "UTF-8", "");
                            Log.e("123", te[3]);
                            w04.loadDataWithBaseURL("", te[4], "text/html", "UTF-8", "");
                            Log.e("123", te[4]);
                            w05.loadDataWithBaseURL("", te[5], "text/html", "UTF-8", "");
                            Log.e("123", te[5]);
                            w06.loadDataWithBaseURL("", te[6], "text/html", "UTF-8", "");
                            Log.e("123", te[6]);
                            w07.loadDataWithBaseURL("", te[7], "text/html", "UTF-8", "");
                            Log.e("123", te[7]);
                            w08.loadDataWithBaseURL("", te[8], "text/html", "UTF-8", "");
                            Log.e("123", te[8]);
                            w09.loadDataWithBaseURL("", te[9], "text/html", "UTF-8", "");
                            Log.e("123", te[9]);
                            w010.loadDataWithBaseURL("", te[10], "text/html", "UTF-8", "");
                            Log.e("123", te[10]);
                            w011.loadDataWithBaseURL("", te[11], "text/html", "UTF-8", "");
                            Log.e("123", te[11]);

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