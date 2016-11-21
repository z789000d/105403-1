package com.example.draggridview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainView extends Activity {
    Button b01, b02,b03,b04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        final RelativeLayout background = (RelativeLayout) findViewById(R.id.back);
        background.setBackgroundColor(Color.rgb(188,195,48));

        b01 = (Button) findViewById(R.id.NewLayout);
        b02 = (Button) findViewById(R.id.MyLayout);
        b03 = (Button) findViewById(R.id.qustion);
        b04= (Button) findViewById(R.id.use);

        b01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainView.this,NewUrl.class);
                startActivity(intent);
            }
        });

        b02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(MainView.this,WebList.class);
                startActivity(intent);

            }
        });
        b03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri= Uri.parse("https://goo.gl/forms/KXdcYCnHAxOzJovZ2");
                Intent i=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);

            }
        });
        b04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri= Uri.parse("https://goo.gl/forms/9Pj4ctWYovSPjgvC2");
                Intent i=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);

            }
        });


    }
}
