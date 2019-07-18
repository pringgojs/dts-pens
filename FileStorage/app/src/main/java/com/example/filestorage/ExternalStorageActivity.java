package com.example.filestorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.sax.EndElementListener;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
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

public class ExternalStorageActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtBaca;
    Button btnCreate, btnEdit, btnRead, btnDelete;

    public static final String FILENAME = "file-external-storage.txt";
    public static String TAG = BuildConfig.APPLICATION_ID;
    public static final int REQUEST_CODE_STORAGE = 100;
    public int selectEvent = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);

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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    execute(selectEvent);
                }
        }
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
        File file = new File(Environment.getExternalStorageDirectory(), FILENAME);
        if (file.exists()) {
            file.delete();
            readFile();
            Toast.makeText(ExternalStorageActivity.this, "Sukses hapus text", Toast.LENGTH_LONG);
        }
    }

    private void editFile() {
        String isiFile = "Coba update data file text";
        String state = Environment.getExternalStorageState();

        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            return;
        }
        Log.d("ExternalActivity", "Location :" + Environment.getExternalStorageDirectory());

        File file = new File(Environment.getExternalStorageDirectory(), FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
            Toast.makeText(ExternalStorageActivity.this, "Sukses edit text", Toast.LENGTH_LONG);
            readFile();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readFile() {
        File sdcard = Environment.getExternalStorageDirectory();
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

                Toast.makeText(ExternalStorageActivity.this, "Sukses baca text", Toast.LENGTH_LONG);

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
        String state = Environment.getExternalStorageState();

        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            return;
        }

        File file = new File(Environment.getExternalStorageDirectory(), FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();

            Toast.makeText(ExternalStorageActivity.this, "Sukses buat text", Toast.LENGTH_LONG);

            readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean periksaIzinPenyimpanan()
    {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE
                );

                return false;
            }
        }

        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCreate:
            case R.id.btnRead:
            case R.id.btnEdit:
            case R.id.btnDelete:
                if (periksaIzinPenyimpanan()) {
                    selectEvent = view.getId();
                    execute(view.getId());
                }
        }
    }
}
