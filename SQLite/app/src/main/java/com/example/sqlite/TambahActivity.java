package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite.db.DBSource;
import com.example.sqlite.models.Barang;

public class TambahActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edNama, edMerek, edHarga;
    Button btnSubmit;
    private DBSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        edNama = findViewById(R.id.edNama);
        edMerek= findViewById(R.id.edMerek);
        edHarga = findViewById(R.id.edHarga);

        btnSubmit = findViewById(R.id.btnSubmit);
        dataSource = new DBSource(this);

        dataSource.open();

        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String nama = null;
        String merk = null;
        String harga = null;

        Barang barang = null;
        if (edNama.getText() != null && edMerek.getText() != null && edHarga.getText() != null) {
            nama = edNama.getText().toString();
            merk = edMerek.getText().toString();
            harga = edHarga.getText().toString();
        }

        switch (view.getId()) {
            case R.id.btnSubmit:
                barang = dataSource.createBarang(nama, merk, harga);

                Toast.makeText(this, "masuk Barang\n" +
                        "nama : " + barang.getNama_barang() +
                        " merk : " + barang.getMerk_barang() +
                        " harga : " + barang.getHarga_barang(), Toast.LENGTH_LONG).show();
                Log.d("tag", "Success");
                break;
        }

    }



}
