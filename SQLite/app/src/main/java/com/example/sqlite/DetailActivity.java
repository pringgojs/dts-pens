package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView tvNama, tvMerk, tvHarga;
    Button buttonOK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvNama = findViewById(R.id.tv_nama_barang);
        tvMerk = findViewById(R.id.tv_merk_barang);
        tvHarga = findViewById(R.id.tv_harga_barang);

        Log.d("det", getIntent().getExtras().getString("nama"));

        System.out.println("APPINVENT " + getIntent().getExtras().getString("nama"));
        tvNama.setText("Barang " + getIntent().getExtras().getString("nama"));
        tvMerk.setText("Merk " + getIntent().getExtras().getString("merk"));
        tvHarga.setText("Harga " + getIntent().getExtras().getString("harga"));

        buttonOK = findViewById(R.id.bt_ok);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
