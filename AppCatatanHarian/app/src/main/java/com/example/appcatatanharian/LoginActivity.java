package com.example.appcatatanharian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String FILENAME = "login";

    EditText edUsername, edPassword;
    Button btnLogin, btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogin) {
            login();
        } else {
            Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(i);
        }
    }

    private boolean isValidation() {
        if (edUsername.getText().toString().trim().equals("") ||
                edPassword.getText().toString().trim().equals("")
        ) {
            return false;
        }

        return true;
    }

    private void login() {
        if (!isValidation()) {
            Toast.makeText(this, "Semua inputan harus terisi!", Toast.LENGTH_LONG).show();

            return;
        }

        File adcard = getFilesDir();
        File file = new File(adcard, edUsername.getText().toString());


        if (file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();

                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String data = text.toString();
            String[] dataUser = data.split(";");
            Log.d("Login", "exist");

            if (dataUser[1].equals(edPassword.getText().toString())) {
                Log.d("Login", "match");

                simpanFileLogin();
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            } else {
                Log.d("Login", "password salah");

                Toast.makeText(this, "Password tidak sesuai", Toast.LENGTH_LONG).show();
            }
        } else {
            Log.d("Login", "user tidak ada");

            Toast.makeText(this, "User tidak ditemukan", Toast.LENGTH_LONG).show();
        }

    }

    private void simpanFileLogin() {
        String isiFile = edUsername.getText().toString()+";"+edPassword.getText().toString();
        File file = new File(getFilesDir(), FILENAME);

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
        Toast.makeText(this, "Login berhasil!", Toast.LENGTH_LONG).show();
        onBackPressed();
    }
}
