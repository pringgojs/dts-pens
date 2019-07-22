package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnTambah, btnLihat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLihat = findViewById(R.id.btnLihat);
        btnTambah = findViewById(R.id.btnTambah);

        btnTambah.setOnClickListener(this);
        btnLihat.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTambah:
                Intent i = new Intent(this, TambahActivity.class);
                startActivity(i);
                break;
            case R.id.btnLihat:
                Intent intent = new Intent(this, LihatActivity.class);
                startActivity(intent);
                break;
        }
    }
}
