package com.arvirotech.monev.model;

public class Konstruksi {
    private String namaProyek;
    private String satuanKerja;
    private String nilaiPagu;
    private String progress;

    public Konstruksi() {
    }

    public Konstruksi(String namaProyek, String satuanKerja, String nilaiPagu, String progress) {
        this.namaProyek = namaProyek;
        this.satuanKerja = satuanKerja;
        this.nilaiPagu = nilaiPagu;
        this.progress = progress;
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
}
