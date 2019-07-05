package com.example.basicintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThreeActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCamera, btnContact, btnBrowser, btnGallery, btnDialpad, btnPindah;
    EditText etNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        etNo = findViewById(R.id.etNo);

        btnCamera = findViewById(R.id.btnCamera);
        btnContact = findViewById(R.id.btnContact);
        btnBrowser= findViewById(R.id.btnBrowser);
        btnGallery = findViewById(R.id.btnGallery);
        btnDialpad = findViewById(R.id.btnDialpad);
        btnPindah = findViewById(R.id.btnPindah);

        btnCamera.setOnClickListener(this);
        btnContact.setOnClickListener(this);
        btnBrowser.setOnClickListener(this);
        btnGallery.setOnClickListener(this);
        btnDialpad.setOnClickListener(this);
        btnPindah.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCamera:
                Intent i = new Intent();
                i.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(i);
                break;
            case R.id.btnContact:
                Intent iContact = new Intent();
                iContact.setAction(Intent.ACTION_VIEW);
                iContact.setData(Uri.parse("content://contacts/people/"));
                startActivity(iContact);
                break;
            case R.id.btnBrowser:
                Intent iBrowser = new Intent();
                iBrowser.setAction(Intent.ACTION_VIEW);
                iBrowser.setData(Uri.parse("http://www.google.com/"));
                startActivity(Intent.createChooser(iBrowser, "Title"));
                break;
            case R.id.btnGallery:
                Intent iGallery = new Intent();
                iGallery.setAction(Intent.ACTION_VIEW);
                iGallery.setData(Uri.parse("content://media/external/images/media/"));
                startActivity(iGallery);
                break;
            case R.id.btnDialpad:
                Intent iDial = new Intent();
                iDial.setAction(Intent.ACTION_DIAL);
                iDial.setData(Uri.parse("tel:" + etNo.getText()));
                startActivity(iDial);
                break;


        }
    }
}
