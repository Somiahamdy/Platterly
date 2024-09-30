package com.example.platterly;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class Splash_Activity extends AppCompatActivity {
    private static final int SPLASH_SCREEN_TIMEOUT = 5000; // 5 seconds

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        LottieAnimationView animationView = findViewById(R.id.animationView);

        // Set the images folder (assuming your images are in the assets folder inside "images" subfolder)
        animationView.setImageAssetsFolder("raw/");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Navigate to MainActivity
                Intent intent = new Intent(Splash_Activity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close SplashActivity
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }
}
