package com.example.implementasimockup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InputNamaActivity extends AppCompatActivity {

    Button btnTampilkan;
    TextView txtLabel;
    EditText edNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_nama);

        txtLabel = findViewById(R.id.txtLabel);
        edNama = findViewById(R.id.edNama);
        btnTampilkan = findViewById(R.id.btnTampilkan);
    }

    public void tampilkan(View view) {
        String nama = edNama.getText().toString().trim();

        if (TextUtils.isEmpty(nama)) {
            edNama.setError("Nama tidak boleh kosong!");

            return;
        }

        txtLabel.setText("Masukkan Nama : " +nama);
    }
}
