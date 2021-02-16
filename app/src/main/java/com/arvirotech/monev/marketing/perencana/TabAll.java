package com.arvirotech.monev.marketing.perencana;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TabAll  extends Fragment {

    private List<listData> allListData;
    private RecyclerView rvList;
    private MarTabAllAdapter myAdapter;
    private View allView;
    TextView pagu;

    ArrayList<String> tkey = new ArrayList<String>();

    private LinearLayoutManager mManager;

    public TabAll(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        allView = inflater.inflate(R.layout.taball_mar, container, false);

        rvList=(RecyclerView) allView.findViewById(R.id.rView);
        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        pagu = (TextView) allView.findViewById(R.id.tvTotalPaguAll);
        allListData = new ArrayList<>();

        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference("Marketing").child("Perencana");

        Query query = nm.orderByChild("progress");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        listData l=npsnapshot.getValue(listData.class);
                        tkey.add(dataSnapshot.getKey());
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

//       Sum of Pagu
        nm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sum=0;

                for (DataSnapshot ds : dataSnapshot.getChildren()){

                    Map<String, String> map = (Map<String,String>) ds.getValue();
                    Object totalPagu = map.get("nilaiPagu");
                    int pValue = Integer.parseInt(String.valueOf(totalPagu));
                    sum += pValue;

                    Double _totalPagu = Double.valueOf(sum);

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


        return allView;
    }


}
