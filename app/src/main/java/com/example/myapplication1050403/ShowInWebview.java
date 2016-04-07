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

    WebView w01,w02;
    EditText ed01;
    Button  b01;
    Thread th;
    String  geturl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_in_webview);
        final DisplayMetrics metrics = new DisplayMetrics();
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

    w01.setOnTouchListener(new OnTouchListener() {
        private float x, y;    // 原本圖片存在的X,Y軸位置
        private float ux, uy;
        private int mx, my; // 圖片被拖曳的X ,Y軸距離長度


        @Override
        public boolean onTouch(View v, MotionEvent event) {
            long time = event.getEventTime() - event.getDownTime();
            // Log.e("View", v.toString());
            if (event.getAction() == MotionEvent.ACTION_DOWN) {          //判斷觸控的動作

                x = event.getX();                  //觸控的X軸位置
                y = event.getY();                  //觸控的Y軸位置
//                        int[] location = new int[2];
//                        w01.getLocationOnScreen(location);           //抓圖片在螢幕的座標img
//                        int a = location[0];
//                        int b = location[1];
                Log.d("觸空的位置", String.valueOf(x) + "~~" + String.valueOf(y));


            }
            if (event.getAction() == MotionEvent.ACTION_MOVE && time > 500) {// 移動圖片時

                //getX()：是獲取當前控件(View)的座標
                //getRawX()：是獲取相對顯示螢幕左上角的座標
                mx = (int) (event.getRawX() - x);
                my = (int) (event.getRawY() - y - (metrics.heightPixels / 4.784));
                v.layout(mx, my, mx + v.getWidth(), my + v.getHeight());
                Log.d("移動的距離", String.valueOf(mx) + "~~" + String.valueOf(my));


            }


            if (event.getAction() == MotionEvent.ACTION_UP) {
                ux = event.getX();
                uy = event.getY();
                Log.d("3333", String.valueOf(time));

            }


            if (time < 100) {
                return false;
            } else return true;


        }
    });

        w02.setOnTouchListener(new OnTouchListener() {
            private float x, y;    // 原本圖片存在的X,Y軸位置
            private float ux, uy;
            private int mx, my; // 圖片被拖曳的X ,Y軸距離長度








            @Override
            public boolean onTouch(View v, MotionEvent event) {
                long time = event.getEventTime()-event.getDownTime();
                // Log.e("View", v.toString());
                if  (event.getAction()==MotionEvent.ACTION_DOWN ) {          //判斷觸控的動作

                    x = event.getX();                  //觸控的X軸位置
                    y = event.getY();                  //觸控的Y軸位置
//                        int[] location = new int[2];
//                        w01.getLocationOnScreen(location);           //抓圖片在螢幕的座標img
//                        int a = location[0];
//                        int b = location[1];
                    Log.d("觸空的位置", String.valueOf(x) + "~~" + String.valueOf(y));


                }
                if (event.getAction()==MotionEvent.ACTION_MOVE && time > 500 ) {// 移動圖片時

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