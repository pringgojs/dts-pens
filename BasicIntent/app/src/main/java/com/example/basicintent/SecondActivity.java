package com.example.basicintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtNama, txtInstitusi;
    Button btnPindah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtNama = findViewById(R.id.txtNama);
        txtInstitusi = findViewById(R.id.txtInstitusi);

        btnPindah = findViewById(R.id.btnPindah);

        String nama= getIntent().getStringExtra(MainActivity.EXTRA_NAMA);
        String institusi = getIntent().getStringExtra(MainActivity.EXTRA_INSTITUSI);

        if (TextUtils.isEmpty(nama)) {
            txtNama.setText("Nama :");
        } else {
            txtNama.setText("Nama : "+nama);
        }

        if (TextUtils.isEmpty(institusi)) {
            txtInstitusi.setText("Institusi :");
        } else {
            txtInstitusi.setText("Institusi : " +institusi);
        }

        btnPindah.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Intent pindahIntent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(pindahIntent);
    }
}
