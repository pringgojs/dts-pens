package com.example.sqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sqlite.models.Barang;

import java.util.ArrayList;

public class DBSource {
    private SQLiteDatabase database;

    private DBHelper dbHelper;

    private String[] allColumns = {DBHelper.COLUMN_ID,
            DBHelper.COLUMN_NAME, DBHelper.COLUMN_MERK, DBHelper.COLUMN_HARGA};

    public DBSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    /** Open Database */
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    /** Close database  */
    public void close() {
        dbHelper.close();
    }
    
    /** Barang */
    public Barang createBarang(String nama, String merk, String harga) {

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME, nama);
        values.put(DBHelper.COLUMN_MERK, merk);
        values.put(DBHelper.COLUMN_HARGA, harga);

        long insertId = database.insert(DBHelper.TABLE_NAME, null,
                values);

        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, DBHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();


        Barang newBarang = cursorToBarang(cursor);
        cursor.close();

        return newBarang;
}

    private Barang cursorToBarang(Cursor cursor) {
        Barang barang = new Barang();
        // debug LOGCAT
        Log.v("info", "The getLONG " + cursor.getLong(0));
        Log.v("info", "The setLatLng " + cursor.getString(1) + "," + cursor.getString(2));


        barang.setId(cursor.getLong(0));
        barang.setNama_barang(cursor.getString(1));
        barang.setMerk_barang(cursor.getString(2));
        barang.setHarga_barang(cursor.getString(3));

        return barang;
    }

    public ArrayList<Barang> getAllBarang() {
        ArrayList<Barang> daftarBarang = new ArrayList<Barang>();

        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Barang barang = cursorToBarang(cursor);
            daftarBarang.add(barang);
            cursor.moveToNext();
        }
        cursor.close();
        return daftarBarang;
    }

    public void deleteBarang(long id) {
        String strFilter = "_id=" + id;

        database.delete(DBHelper.TABLE_NAME, strFilter, null);
    }

    public void updateBarang(Barang barang) {
        String strFilter = "_id=" + barang.getId();

        ContentValues args = new ContentValues();
        args.put(DBHelper.COLUMN_NAME, barang.getNama_barang());
        args.put(DBHelper.COLUMN_MERK, barang.getMerk_barang());
        args.put(DBHelper.COLUMN_HARGA, barang.getHarga_barang());


        database.update(DBHelper.TABLE_NAME, args, strFilter, null);
    }

    public Barang getBarang(long id) {
        Barang barang = new Barang();

        Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns,
                "_id =" + id, null, null, null, null);
        cursor.moveToFirst();
        barang = cursorToBarang(cursor);
        cursor.close();

        return barang;
    }
}
