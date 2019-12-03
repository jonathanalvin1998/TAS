package com.example.uas_72170128.Model;

import android.provider.ContactsContract;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dosen {
    @SerializedName("nidn")
    @Expose
    private String nidn;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gelar")
    @Expose
    private String gelar;
    public Dosen(String nidn, String nama, String email, String alamat, String imgDsn, int id,  String gelar) {
        this.nidn = nidn;
        this.nama = nama;
        this.email = email;
        this.alamat = alamat;
        ImgDsn = imgDsn;
        Id = id;
        this.gelar = gelar;
    }

    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("foto")
    @Expose
    private String ImgDsn;

    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getImgDsn() {
        return ImgDsn;
    }

    public void setImgDsn(String imgDsn) {
        ImgDsn = imgDsn;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
    public String getGelar() {
        return gelar;
    }

    public void setGelar(String gelar) {
        this.gelar = gelar;
    }
    @SerializedName("id")
    @Expose
    private int Id;


}
