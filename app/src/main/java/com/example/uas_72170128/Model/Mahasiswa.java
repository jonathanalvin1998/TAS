package com.example.uas_72170128.Model;

import android.widget.ImageButton;

public class Mahasiswa {
    private String nama;
    private String nim;
    private String alamat;
    private String email;
    private int img;

    public Mahasiswa(String nama, String nim, String alamat,String email, int img) {
        this.nama = nama;
        this.nim = nim;
        this.alamat = alamat;
        this.email = email;
        this.img = img;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getImg(){
        return img;
    }

    public void setImg(int img){
        this.img = img;
    }

    public String getEmail(){return email;}

    public void setEmail(String email) {this.email = email;}
}
