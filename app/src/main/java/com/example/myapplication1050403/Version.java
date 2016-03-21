package com.example.myapplication1050403;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.view.View.OnTouchListener;
import android.widget.TableRow;



public class Version extends Activity {


    private ImageView img ,img2 ,img3,img4;
    private int a,b;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);
       img = (ImageView) findViewById(R.id.imageView);
        //img2 = (ImageView) findViewById(R.id.imageView2);
        /*img3 = (ImageView) findViewById(R.id.imageView3);
        img4 = (ImageView) findViewById(R.id.imageView4);*/










        img.setOnTouchListener(new OnTouchListener() {
            private float x, y;    // 原本圖片存在的X,Y軸位置
            private float ux,uy;
            private int mx, my; // 圖片被拖曳的X ,Y軸距離長度




            public boolean onTouch(View v, MotionEvent event) {
                // Log.e("View", v.toString());
                switch (event.getAction()) {          //判斷觸控的動作

                    case MotionEvent.ACTION_DOWN:// 按下圖片時
                        x = event.getX();                  //觸控的X軸位置
                        y = event.getY();                  //觸控的Y軸位置


                    case MotionEvent.ACTION_MOVE:// 移動圖片時

                        //getX()：是獲取當前控件(View)的座標

                        //getRawX()：是獲取相對顯示螢幕左上角的座標
                        mx = (int) (event.getRawX() - x);
                        my = (int) (event.getRawY() - y);
                        v.layout(mx, my, mx + v.getWidth(), my + v.getHeight());
                        break;

                    case MotionEvent.ACTION_UP:
                        ux = event.getX();
                        uy = event.getY();
                        Log.d("123",String.valueOf(ux)+"~~"+String.valueOf(uy));

                        int[] location = new int[2];
                        img.getLocationOnScreen(location);           //抓圖片在螢幕的座標
                        int a = location[0];
                        int b = location[1];

                        if (a>650  &&  b<1359) {

                            v.layout(1084,714,1084+ v.getWidth() , 714+ v.getHeight());
                           ;
                                                                                                //圖片在這個座標範圍內自動跳入固定座標

                        }
                        else {
                            v.layout(mx, my, mx + v.getWidth(), my + v.getHeight());         //在外面可以自由移動

                        }



                }


                return true;



            }
        });














       /* img2.setOnTouchListener(new OnTouchListener() {
            private float x, y;    // 原本圖片存在的X,Y軸位置
            private int mx, my; // 圖片被拖曳的X ,Y軸距離長度

            public boolean onTouch(View v, MotionEvent event) {
                // Log.e("View", v.toString());
                switch (event.getAction()) {          //判斷觸控的動作

                    case MotionEvent.ACTION_DOWN:// 按下圖片時
                        x = event.getX();                  //觸控的X軸位置
                        y = event.getY();                  //觸控的Y軸位置

                    case MotionEvent.ACTION_MOVE:// 移動圖片時

                        //getX()：是獲取當前控件(View)的座標

                        //getRawX()：是獲取相對顯示螢幕左上角的座標
                        mx = (int) (event.getRawX() - x);
                        my = (int) (event.getRawY() - y);
                        v.layout(mx, my, mx + v.getWidth(), my + v.getHeight());
                        break;
                }
                Log.e("address", String.valueOf(mx) + "~~" + String.valueOf(my)); // 記錄目前位置
                return true;
            }
        });*/


       /* img3.setOnTouchListener(new OnTouchListener() {
            private float x, y;    // 原本圖片存在的X,Y軸位置
            private int mx, my; // 圖片被拖曳的X ,Y軸距離長度

            public boolean onTouch(View v, MotionEvent event) {
                // Log.e("View", v.toString());
                switch (event.getAction()) {          //判斷觸控的動作

                    case MotionEvent.ACTION_DOWN:// 按下圖片時
                        x = event.getX();                  //觸控的X軸位置
                        y = event.getY();                  //觸控的Y軸位置

                    case MotionEvent.ACTION_MOVE:// 移動圖片時

                        //getX()：是獲取當前控件(View)的座標

                        //getRawX()：是獲取相對顯示螢幕左上角的座標
                        mx = (int) (event.getRawX() - x);
                        my = (int) (event.getRawY() - y);
                        v.layout(mx, my, mx + v.getWidth(), my + v.getHeight());
                        break;
                }
                Log.e("address", String.valueOf(mx) + "~~" + String.valueOf(my)); // 記錄目前位置
                return true;
            }
        });

        img4.setOnTouchListener(new OnTouchListener() {
            private float x, y;    // 原本圖片存在的X,Y軸位置
            private int mx, my; // 圖片被拖曳的X ,Y軸距離長度

            public boolean onTouch(View v, MotionEvent event) {
                // Log.e("View", v.toString());
                switch (event.getAction()) {          //判斷觸控的動作

                    case MotionEvent.ACTION_DOWN:// 按下圖片時
                        x = event.getX();                  //觸控的X軸位置
                        y = event.getY();                  //觸控的Y軸位置

                    case MotionEvent.ACTION_MOVE:// 移動圖片時

                        //getX()：是獲取當前控件(View)的座標

                        //getRawX()：是獲取相對顯示螢幕左上角的座標
                        mx = (int) (event.getRawX() - x);
                        my = (int) (event.getRawY() - y);
                        v.layout(mx, my, mx + v.getWidth(), my + v.getHeight());
                        break;
                }
                Log.e("address", String.valueOf(mx) + "~~" + String.valueOf(my)); // 記錄目前位置
                return true;
            }
        });*/






    }


            }



