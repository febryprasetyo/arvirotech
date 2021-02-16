package com.arvirotech.monev.model;

public class AddMarketing {

    private String key;
    private String namaProyek;
    private String satuanKerja;
    private String alamatProyek;
    private String nilaiPagu;
    private String progress;
    private String date;
    private String user;

    public AddMarketing() {
    }

    public AddMarketing( String key,String namaProyek, String satuanKerja, String alamatProyek, String nilaiPagu, String progress, String date, String user) {
        this.key = key;
        this.namaProyek = namaProyek;
        this.satuanKerja = satuanKerja;
        this.alamatProyek = alamatProyek;
        this.nilaiPagu = nilaiPagu;
        this.progress = progress;
        this.date = date;
        this.user = user;
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

    public String getAlamatProyek() {
        return alamatProyek;
    }

    public void setAlamatProyek(String alamatProyek) {
        this.alamatProyek = alamatProyek;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

