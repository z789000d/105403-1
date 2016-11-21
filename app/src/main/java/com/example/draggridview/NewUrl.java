package com.example.draggridview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static java.security.AccessController.getContext;

public class NewUrl extends Activity {
    EditText EditUrl;
    Button Button1,Button2;
    TextView t01;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_url);

        final RelativeLayout background = (RelativeLayout) findViewById(R.id.back);
        background.setBackgroundColor(Color.rgb(188,195,48));

        toast = Toast.makeText(this, "請輸入內容", Toast.LENGTH_SHORT);
        t01 = (TextView) findViewById(R.id.t01);
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/wt040.ttf");
        t01.setTypeface(face);
        EditUrl = (EditText) findViewById(R.id.EditUrl);
        Button1 = (Button) findViewById(R.id.Button1);
        Button2 = (Button) findViewById(R.id.b02);



        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(EditUrl.getText().toString().equals("") || EditUrl.getText().toString().equals(null)){
                    toast.show();
            }
                else
                {
                    String Url = EditUrl.getText().toString();
                    Intent intent = new Intent();
                    intent.putExtra("Url",Url);
                    intent.setClass(NewUrl.this,ListViewCheckboxesActivity.class);
                    startActivity(intent);
                }
            }
        });
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditUrl.setText("");
            }
        });
    }
}
