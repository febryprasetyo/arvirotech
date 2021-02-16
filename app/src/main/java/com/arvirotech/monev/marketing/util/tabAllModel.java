package com.arvirotech.monev.marketing.util;

public class tabAllModel {

    private String Pekerjaan;
    private String Lpse;
    private String Pagu;
    private String Persentase;

    public tabAllModel() {
    }

    public tabAllModel(String pekerjaan, String lpse, String pagu, String persentase) {
        Pekerjaan = pekerjaan;
        Lpse = lpse;
        Pagu = pagu;
        Persentase = persentase;
    }

    //Getter
    public String getPekerjaan() {
        return Pekerjaan;
    }

    public String getLpse() {
        return Lpse;
    }

    public String getPagu() {
        return Pagu;
    }

    public String getPersentase() {
        return Persentase;
    }

    //Setter
    public void setPekerjaan(String pekerjaan) {
        Pekerjaan = pekerjaan;
    }

    public void setLpse(String lpse) {
        Lpse = lpse;
    }

    public void setPagu(String pagu) {
        Pagu = pagu;
    }

    public void setPersentase(String persentase) {
        Persentase = persentase;
    }
}
