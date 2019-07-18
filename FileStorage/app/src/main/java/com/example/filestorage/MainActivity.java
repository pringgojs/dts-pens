package com.example.filestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnInternal, btnExternal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnExternal = findViewById(R.id.btnExternal);
        btnInternal = findViewById(R.id.btnInternal);

        btnInternal.setOnClickListener(this);
        btnExternal.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnExternal) {
            Intent i = new Intent(MainActivity.this, ExternalStorageActivity.class);
            startActivity(i);
        } else {
            Intent i = new Intent(MainActivity.this, InternalStorageActivity.class);
            startActivity(i);
        }
    }
}
