package com.example.pensjoss;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText edFullname, edEmail, edPassword, edRepassword, edPhone, edBirtday;
    RadioButton rdMale, rdFamale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edFullname = findViewById(R.id.edFullname);
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        edRepassword = findViewById(R.id.edRepassword);
        edPhone = findViewById(R.id.edPhone);
        edBirtday = findViewById(R.id.edBirthday);
        rdMale = findViewById(R.id.rdMale);
        rdFamale = findViewById(R.id.rdFamale);
    }

    public void signUp(View view) {
        String password = edPassword.getText().toString().trim();
        String rePassword = edRepassword.getText().toString().trim();

        if (TextUtils.isEmpty(edFullname.getText().toString().trim())) {
            edFullname.setError("Fullname can't be empty!");
        } else if (!isValidEmail(edEmail.getText().toString().trim())) {
            edEmail.setError("Email not valid!");
        } else if (TextUtils.isEmpty(password)) {
            edPassword.setError("Password can't be empty!");
        } else if (!rePassword.equals(password)) {
            edRepassword.setError("Password not same!");
        } else if (TextUtils.isEmpty(edPhone.getText().toString().trim())) {
            edPhone.setError("Phone can't be empty!");
        } else if (TextUtils.isEmpty(edBirtday.getText().toString().trim())) {
            edBirtday.setError("Birtday can't be empty!");
        } else if (rdMale.isChecked() || rdFamale.isChecked()) {
            Toast.makeText(view.getContext(), "Gender not selected", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(view.getContext(), "Register complate", Toast.LENGTH_LONG).show();
        }
    }

    public static boolean isValidEmail(CharSequence email) {
        return (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}
