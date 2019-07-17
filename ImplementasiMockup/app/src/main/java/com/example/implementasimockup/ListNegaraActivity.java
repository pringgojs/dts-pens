package com.example.implementasimockup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListNegaraActivity extends AppCompatActivity {

    private ListView lview;
    private String[] daftar_negara =  new String[] {
        "Indonesia", "Singapura", "Malaysia", "Mesir", "Italia",
        "Inggris", "Belanda", "Argentina", "Chile", "Uganda"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_negara);

        lview = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListNegaraActivity.this, android.R.layout.simple_list_item_1,
                android.R.id.text1, daftar_negara);

        lview.setAdapter(adapter);

        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListNegaraActivity.this, "Memilih "+daftar_negara[i], Toast.LENGTH_LONG).show();
            }
        });

    }
}
