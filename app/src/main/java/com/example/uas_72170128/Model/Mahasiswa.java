package com.example.uas_72170128.Model;

import android.widget.ImageButton;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mahasiswa {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("nim")
    @Expose
    private String nim;

    @SerializedName("alamat")
    @Expose
    private String alamat;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("img")
    @Expose
    private String img;

    public Mahasiswa(String nama, String nim, String alamat,String email, String img) {
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

    public String  getImg(){
        return img;
    }

    public void setImg(String img){
        this.img = img;
    }

    public String getEmail(){return email;}

    public void setEmail(String email) {this.email = email;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
