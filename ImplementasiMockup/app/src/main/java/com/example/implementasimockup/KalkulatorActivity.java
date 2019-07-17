package com.example.implementasimockup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class KalkulatorActivity extends AppCompatActivity {

    TextView txtHasil;
    EditText edA, edB;
    Button btnTambah, btnKurang, btnBagi, btnKali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator);

        edA = findViewById(R.id.edA);
        edB = findViewById(R.id.edB);
        txtHasil = findViewById(R.id.txtHasil);
    }

    public void tambah(View view) {
        this.execute("+");
    }

    public void kurang(View view) {
        this.execute("-");
    }

    public void kali(View view) {
        this.execute("*");
    }


    public void bagi(View view) {
        this.execute("/");
    }

    public void bersihkan(View view) {
        edA.setText("");
        edB.setText("");
        txtHasil.setText("");
    }

    public void execute(String operator) {

        String angka_pertama = edA.getText().toString();
        String angka_kedua= edB.getText().toString();
        if ((angka_kedua.length() > 0) && (angka_pertama.length() > 0)) {
            double angka_a = Double.parseDouble(angka_pertama);
            double angka_b = Double.parseDouble(angka_kedua);

            double result = 0;
            switch (operator) {
                case "+":
                    result = angka_a + angka_b;
                    break;
                case "-":
                    result = angka_a - angka_b;
                    break;
                case "*":
                    result = angka_a * angka_b;
                    break;
                case "/":
                    result = angka_a / angka_b;
                    break;
                default:
                    result = 0;
                    break;
            }

            txtHasil.setText(Double.toString(result));

        } else {
            Toast toast = Toast.makeText(KalkulatorActivity.this, "Mohon masukkan ke dua angka", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
