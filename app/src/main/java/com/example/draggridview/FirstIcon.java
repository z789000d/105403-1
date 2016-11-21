package com.example.draggridview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class FirstIcon extends Activity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_icon);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(FirstIcon.this, MainView.class);
                startActivity(mainIntent);
                FirstIcon.this.finish();
                // 當跳到另一 Activity 時，讓 MainActivity 結束。
                // 這樣可以避免使用者按 back 後，又回到該 activity。
            }
        }, 2000);
    }
}
