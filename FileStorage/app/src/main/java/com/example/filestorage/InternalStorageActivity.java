package com.example.filestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class InternalStorageActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtBaca;
    Button btnCreate, btnEdit, btnRead, btnDelete;

    public static final String FILENAME = "filedtskominfo.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);

        txtBaca = findViewById(R.id.txtBaca);
        btnCreate = findViewById(R.id.btnCreate);
        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.btnEdit);
        btnRead = findViewById(R.id.btnRead);


        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnCreate.setOnClickListener(this);

        txtBaca.setMovementMethod(new ScrollingMovementMethod());

        readFile();
    }

    @Override
    public void onClick(View view) {
        execute(view.getId());
    }

    private void execute(int id) {
        switch (id) {
            case R.id.btnCreate:
                createFile();
                break;
            case R.id.btnRead:
                readFile();
                break;
            case R.id.btnEdit:
                editFile();
                break;
            case R.id.btnDelete:
                deleteFile();
                break;
            default: break;
        }
    }

    private void deleteFile() {
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()) {
            file.delete();
            readFile();
            Toast.makeText(InternalStorageActivity.this, "Sukses hapus text", Toast.LENGTH_LONG);
        }
    }

    private void editFile() {
        String isiFile = "Coba update data file text";
        File file = new File(getFilesDir(), FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
            Toast.makeText(InternalStorageActivity.this, "Sukses edit text", Toast.LENGTH_LONG);
            readFile();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readFile() {
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);

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

                Toast.makeText(InternalStorageActivity.this, "Sukses baca text", Toast.LENGTH_LONG);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            txtBaca.setText(text.toString());
        } else {
            txtBaca.setText("");
        }
    }

    private void createFile() {
        String isiFile = "Coba isi data file text";
        File file = new File(getFilesDir(), FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();

            Toast.makeText(InternalStorageActivity.this, "Sukses buat text", Toast.LENGTH_LONG);

            readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
