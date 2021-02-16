package com.arvirotech.monev.marketing.util;

import android.widget.ImageView;

public class tabPersentaseModel {

    private String Pekerjaan;
    private String Lpse;
    private String Pagu;

    public tabPersentaseModel() {
    }

    public tabPersentaseModel(String pekerjaan, String lpse, String pagu) {
        Pekerjaan = pekerjaan;
        Lpse = lpse;
        Pagu = pagu;
    }

    public String getPekerjaan() {
        return Pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        Pekerjaan = pekerjaan;
    }

    public String getLpse() {
        return Lpse;
    }

    public void setLpse(String lpse) {
        Lpse = lpse;
    }

    public String getPagu() {
        return Pagu;
    }

    public void setPagu(String pagu) {
        Pagu = pagu;
    }


}
