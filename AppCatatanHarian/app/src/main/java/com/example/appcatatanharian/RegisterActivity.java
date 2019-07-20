package com.example.appcatatanharian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edUsername, edPassword, edEmail, edNama, edAsal, edAlamat;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Registrasi");

        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        edEmail = findViewById(R.id.edEmail);
        edAlamat = findViewById(R.id.edAlamat);
        edAsal = findViewById(R.id.edAsal);
        edNama = findViewById(R.id.edFileNama);

        btnSimpan = findViewById(R.id.btnSimpan);

        btnSimpan.setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public void onClick(View view) {
        if (isValidation()) {
            simpanFileData();
        } else {
            Toast.makeText(this, "Lengkapi seluruh data!", Toast.LENGTH_LONG).show();
        }
    }

    private void simpanFileData() {
        String isiFile = edUsername.getText().toString()+";"+edPassword.getText().toString()
                +";"+edEmail.getText().toString()+";"+edNama.getText().toString()
                +";"+edAsal.getText().toString()+";"+edAlamat;
        File file = new File(getFilesDir(), edUsername.getText().toString());

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Register berhasil!", Toast.LENGTH_LONG).show();
        onBackPressed();
    }

    private boolean isValidation() {
        if (edUsername.getText().toString().trim().equals("") ||
                edPassword.getText().toString().trim().equals("") ||
                edEmail.getText().toString().trim().equals("") ||
                edNama.getText().toString().trim().equals("") ||
                edAsal.getText().toString().trim().equals("") ||
                edAlamat.getText().toString().trim().equals("")
        ) {
            return false;
        }

        return true;
    }
}
