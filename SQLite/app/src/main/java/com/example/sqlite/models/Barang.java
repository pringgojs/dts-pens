package com.example.sqlite.models;

public class Barang {
    private long id;
    private String nama_barang;
    private String merk_barang;
    private String harga_barang;

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getMerk_barang() {
        return merk_barang;
    }

    public void setMerk_barang(String merk_barang) {
        this.merk_barang = merk_barang;
    }

    public String getHarga_barang() {
        return harga_barang;
    }

    public void setHarga_barang(String harga_barang) {
        this.harga_barang = harga_barang;
    }
    public Barang() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Barang : " + '\'' +
                "id=" + id + '\'' +
                ", nama_barang='" + nama_barang + '\'' +
                ", merk_barang='" + merk_barang + '\'' +
                ", harga_barang='" + harga_barang;
    }
}
