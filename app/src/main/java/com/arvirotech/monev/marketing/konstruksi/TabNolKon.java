package com.arvirotech.monev.marketing.konstruksi;

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
import com.arvirotech.monev.marketing.adapter.PrcMarTabAllAdapter;
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


public class TabNolKon extends Fragment {

    private List<listData> allListData;
    private RecyclerView rvList;
    private PrcMarTabAllAdapter myAdapter;
    private View view0;
    TextView pagu;
    Double _totalPagu;

    private LinearLayoutManager mManager;
    public  TabNolKon(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view0 = inflater.inflate(R.layout.fragment_tab_nol_kon, container, false);

        rvList = (RecyclerView) view0.findViewById(R.id.rView0kon);
        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        pagu = (TextView) view0.findViewById(R.id.tvTotalPagu0);
        allListData = new ArrayList<>();

        SumOfPagu();

        RetriveDatabase();

        return view0;
    }

    private void SumOfPagu() {
        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference("Marketing").child("Konstruksi");
        Query query = nm.orderByChild("progress")
                .equalTo("0%");

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

    private void RetriveDatabase() {

        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference("Marketing").child("Konstruksi");
        Query query = nm.orderByChild("progress")
                .equalTo("0%");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        listData l=npsnapshot.getValue(listData.class);
                        allListData.add(l);
                    }
                    myAdapter = new PrcMarTabAllAdapter(allListData);
                    rvList.setAdapter(myAdapter);
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