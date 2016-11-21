package com.example.draggridview;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

public class Forget extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_icon);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(Forget.this, MainView.class);
                startActivity(mainIntent);
                Forget.this.finish();
                // 當跳到另一 Activity 時，讓 MainActivity 結束。
                // 這樣可以避免使用者按 back 後，又回到該 activity。
            }
        }, 2000);
    }
}
