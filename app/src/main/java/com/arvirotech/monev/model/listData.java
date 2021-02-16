package com.arvirotech.monev.model;

import java.io.Serializable;

public class listData implements Serializable {
    private String namaProyek;
    private String satuanKerja;
    private String nilaiPagu;
    private String progress;
    private String alamatProyek;
    private String user;
    private String key;



    public listData() {
    }

    public listData(String namaProyek, String satuanKerja, String nilaiPagu, String progress, String alamatProyek, String user, String key) {
        this.namaProyek = namaProyek;
        this.satuanKerja = satuanKerja;
        this.nilaiPagu = nilaiPagu;
        this.progress = progress;
        this.alamatProyek = alamatProyek;
        this.user = user;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNamaProyek() {
        return namaProyek;
    }

    public void setNamaProyek(String namaProyek) {
        this.namaProyek = namaProyek;
    }

    public String getSatuanKerja() {
        return satuanKerja;
    }

    public void setSatuanKerja(String satuanKerja) {
        this.satuanKerja = satuanKerja;
    }

    public String getNilaiPagu() {
        return nilaiPagu;
    }

    public void setNilaiPagu(String nilaiPagu) {
        this.nilaiPagu = nilaiPagu;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getAlamatProyek() {
        return alamatProyek;
    }

    public void setAlamatProyek(String alamatProyek) {
        this.alamatProyek = alamatProyek;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
