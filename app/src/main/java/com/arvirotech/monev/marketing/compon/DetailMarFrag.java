package com.arvirotech.monev.marketing.compon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.arvirotech.monev.R;


public class DetailMarFrag extends Fragment {

    private View DetailView;
    private EditText namaPekerjaan, satuanKerja, Alamat, nilaiPagu;

    public DetailMarFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        DetailView = inflater.inflate(R.layout.fragment_detail_mar, container, false);

        namaPekerjaan = (EditText) DetailView.findViewById(R.id.edNamaProyek);
        satuanKerja = (EditText) DetailView.findViewById(R.id.edSatuanKerja);
        Alamat = (EditText) DetailView.findViewById(R.id.edAlamatProyek);
        nilaiPagu = (EditText) DetailView.findViewById(R.id.edNilaiPagu);


        return DetailView;
    }
}