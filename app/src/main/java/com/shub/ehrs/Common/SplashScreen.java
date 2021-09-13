package com.shub.ehrs.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.shub.ehrs.Common.LoginSignup.Login;
import com.shub.ehrs.R;

public class SplashScreen extends AppCompatActivity {

    private  static  int SPLASH_TIMER = 3000;

    //variables
    ImageView backround_image;
    TextView backroundText;

    //Animation
    Animation sideAnim, bottomAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        //Hooks
        backround_image = findViewById(R.id.splash_icon);
        backroundText= findViewById(R.id.ehrs);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIMER);

    }
}