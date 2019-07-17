package com.example.implementasimockup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void activityInputNama(View view) {
        startActivity(new Intent(MainActivity.this, InputNamaActivity.class));
        finish();
    }

    public void kalkulator(View view) {
        startActivity(new Intent(MainActivity.this, KalkulatorActivity.class));
        finish();
    }

    public void listNegara(View view) {
        startActivity(new Intent(MainActivity.this, ListNegaraActivity.class));
        finish();
    }
}
