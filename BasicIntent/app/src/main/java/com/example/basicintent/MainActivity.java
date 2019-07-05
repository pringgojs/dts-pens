package com.example.basicintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edNama, edInstitusi;
    Button btnTanpaData, btnDenganData;

    public static final String EXTRA_NAMA = "extra_nama";
    public static final String EXTRA_INSTITUSI = "extra_institusi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edNama = findViewById(R.id.edNama);
        edInstitusi = findViewById(R.id.edInstitusi);

        btnDenganData = findViewById(R.id.btnDenganData);
        btnTanpaData = findViewById(R.id.btnTanpaData);

        btnTanpaData.setOnClickListener(this);
        btnDenganData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDenganData:
                pindahActivityDenganData();
                break;
            case R.id.btnTanpaData:
                pindahActivityTanpaData();
                break;
            default:
                break;
        }
    }

    private void pindahActivityTanpaData() {
        Intent pindahIntent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(pindahIntent);
    }

    private void pindahActivityDenganData() {
        String nama = edNama.getText().toString().trim();
        String institusi = edInstitusi.getText().toString().trim();

        Intent pindahIntent = new Intent(MainActivity.this, SecondActivity.class);
        pindahIntent.putExtra(this.EXTRA_NAMA, nama);
        pindahIntent.putExtra(this.EXTRA_INSTITUSI, institusi);
        startActivity(pindahIntent);

    }

    public void pindahActivity3(View view) {
        Intent pindahIntent = new Intent(MainActivity.this, ThreeActivity.class);
        startActivity(pindahIntent);
    }
}
