package com.example.appcatatanharian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.io.File;

public class SplashscreenActivity extends AppCompatActivity {

    public static final String FILENAME = "login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isLogin()) {
                    startActivity(new Intent(SplashscreenActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(SplashscreenActivity.this, LoginActivity.class));
                }

                finish();
            }
        }, 3000);
    }

    private boolean isLogin() {
        File adcard = getFilesDir();
        File file = new File(adcard, FILENAME);
        if (file.exists()) {
            return true;
        }

        return false;
    }
}
