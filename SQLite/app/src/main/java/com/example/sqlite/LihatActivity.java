package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.sqlite.db.DBSource;
import com.example.sqlite.models.Barang;

import java.util.ArrayList;

public class LihatActivity extends AppCompatActivity {


    ListView listView;

    private DBSource dataSource;

    private ArrayList<Barang> values;


    private Button editButton;
    private Button delButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat);

        listView = findViewById(R.id.listview);

        dataSource = new DBSource(this);

        dataSource.open();

        // ambil semua data barang
        values = dataSource.getAllBarang();


        // masukkan data barang ke array adapter
        ArrayAdapter<Barang> adapter = new ArrayAdapter<Barang>(this,
                android.R.layout.simple_list_item_1, values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Barang barang = (Barang) parent.getAdapter().getItem(position);
                switchToGetData(barang.getId());
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {

                final Dialog dialog = new Dialog(LihatActivity.this);
                dialog.setContentView(R.layout.dialog_view);
                dialog.setTitle("Pilih Aksi");
                dialog.show();
                final Barang b = (Barang) parent.getAdapter().getItem(position);
                editButton = (Button) dialog.findViewById(R.id.btnEdit);
                delButton = (Button) dialog.findViewById(R.id.btnHapus);

                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switchToEdit(b.getId());
                        dialog.dismiss();
                    }
                });

                delButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Delete barang
                                dataSource.deleteBarang(b.getId());
                                dialog.dismiss();
                                finish();
                                startActivity(getIntent());
                            }
                        }
                );

                return true;
            }
        });

    }

    private void switchToEdit(long id) {
        Barang b = dataSource.getBarang(id);
        Intent i = new Intent(this, EditActivity.class);
        Bundle bun = new Bundle();
        bun.putLong("id", b.getId());
        bun.putString("nama", b.getNama_barang());
        bun.putString("merk", b.getMerk_barang());
        bun.putString("harga", b.getHarga_barang());
        i.putExtras(bun);
        finale();
        startActivity(i);
    }

    private void finale() {
        LihatActivity.this.finish();
        dataSource.close();
    }

    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }

    private void switchToGetData(long id) {
        Barang b = dataSource.getBarang(id);
        Intent i = new Intent(this, DetailActivity.class);
        Bundle bun = new Bundle();
        bun.putLong("id", b.getId());
        bun.putString("nama", b.getNama_barang());
        bun.putString("merk", b.getMerk_barang());
        bun.putString("harga", b.getHarga_barang());
        i.putExtras(bun);
        dataSource.close();
        startActivity(i);
    }

}
