package com.arvirotech.monev.marketing.konstruksi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arvirotech.monev.R;
import com.arvirotech.monev.model.listData;

public class DetailFragment extends Fragment {


    String namaPekerjaan, satker, alamat, nilaiPagu, progress, user;

    public DetailFragment() {
    }

    public DetailFragment(String namaPekerjaan, String satker, String alamat, String nilaiPagu, String progress, String user) {
        this.namaPekerjaan = namaPekerjaan;
        this.satker = satker;
        this.alamat = alamat;
        this.nilaiPagu = nilaiPagu;
        this.progress = progress;
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_detail, container, false);

        TextView namaholder = view.findViewById(R.id.tvPekerjaan);
        TextView lpseholder = view.findViewById(R.id.tvLpse);
        TextView paguholder = view.findViewById(R.id.tvPagu);

        namaholder.setText(namaPekerjaan);
        lpseholder.setText(satker);
        paguholder.setText(nilaiPagu);

        return view;
    }

//    public void  onBackPressed(){
//        AppCompatActivity activity = (AppCompatActivity).getContext();
//        activity.getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.wrapper, new KonstruksiMarketing())
//                .addToBackStack(null)
//                .commit();
//    }
}