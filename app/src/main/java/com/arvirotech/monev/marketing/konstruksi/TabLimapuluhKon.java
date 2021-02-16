package com.arvirotech.monev.marketing.konstruksi;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arvirotech.monev.R;
import com.arvirotech.monev.marketing.adapter.MarTabAllAdapter;
import com.arvirotech.monev.model.listData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class TabLimapuluhKon extends Fragment {

    private List<listData> allListData;
    private RecyclerView rvList;
    private MarTabAllAdapter myAdapter;
    private View nulView;
    TextView pagu;
    Double _totalPagu;


    public TabLimapuluhKon() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        nulView = inflater.inflate(R.layout.fragment_tab_limapuluh_kon, container, false);

        // Inflate the layout for this fragment
        rvList = (RecyclerView) nulView.findViewById(R.id.rView50Kon);
        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        pagu = (TextView) nulView.findViewById(R.id.tvTotalPagu0);
        allListData = new ArrayList<>();

        RetriveDatabase();

        SumOfPagu();

        return nulView;
    }

    private void RetriveDatabase() {
        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference("Marketing").child("Konstruksi");
        Query query = nm.orderByChild("Konstruksi")
                .equalTo("50%");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        listData l=npsnapshot.getValue(listData.class);
                        allListData.add(l);
                    }
                    myAdapter = new MarTabAllAdapter(allListData);
                    rvList.setAdapter(myAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void SumOfPagu() {
        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference("Marketing").child("Konstruksi");
        Query query = nm.orderByChild("progress")
                .equalTo("50%");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sum=0;

                for (DataSnapshot ds : dataSnapshot.getChildren()){

                    Map<String, Object> map = (Map<String,Object>) ds.getValue();
                    Object totalPagu = map.get("nilaiPagu");
                    int pValue = Integer.parseInt(String.valueOf(totalPagu));
                    sum += pValue;
                    _totalPagu = Double.valueOf(sum);

                    DecimalFormat formatRupiah = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                    DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                    formatRp.setCurrencySymbol("Rp. ");
                    formatRp.setMonetaryDecimalSeparator(',');
                    formatRp.setGroupingSeparator('.');
                    formatRupiah.setDecimalFormatSymbols(formatRp);



                    pagu.setText(formatRupiah.format(_totalPagu));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }

}