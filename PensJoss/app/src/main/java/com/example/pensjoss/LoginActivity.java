package com.example.pensjoss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.pensjoss.SignupActivity.isValidEmail;

public class LoginActivity extends AppCompatActivity {

    EditText edUsername, edPassword;
    Button btLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
    }

    public void login(View view) {
        if (TextUtils.isEmpty(edUsername.getText().toString().trim())
                && TextUtils.isEmpty(edPassword.getText().toString().trim())) {
            edUsername.setError("Email tidak boleh kosong!");
            edPassword.setError("Password tidak boleh kosong!");

        } else if (!isValidEmail(edUsername.getText().toString().trim())) {

            edUsername.setError("Email tidak valid!");

        } else if (TextUtils.isEmpty(edUsername.getText().toString().trim())) {

            edUsername.setError("Email tidak boleh kosong!");

        } else if (TextUtils.isEmpty(edPassword.getText().toString())) {
            edPassword.setError("Password tidak boleh kosong!");
        } else {
            Toast.makeText(view.getContext(), "Sukses Login", Toast.LENGTH_LONG).show();
        }
    }

    public void register(View view) {
        Intent i = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(i);
    }

    public static boolean isValidEmail(CharSequence email) {
        return (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}
