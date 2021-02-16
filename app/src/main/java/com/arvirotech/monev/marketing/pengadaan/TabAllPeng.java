package com.arvirotech.monev.marketing.pengadaan;

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
import com.arvirotech.monev.marketing.adapter.AdaMarTabAllAdapter;
import com.arvirotech.monev.marketing.adapter.PrcMarTabAllAdapter;
import com.arvirotech.monev.model.listData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class TabAllPeng extends Fragment {

    private List<listData> allListData;
    private RecyclerView rvList;
    private AdaMarTabAllAdapter myAdapter;
    private View allView;
    TextView pagu;

    ArrayList<String> tkey = new ArrayList<String>();

    private LinearLayoutManager mManager;

    public TabAllPeng() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        allView = inflater.inflate(R.layout.fragment_tab_all_peng, container, false);
        rvList = (RecyclerView) allView.findViewById(R.id.rViewAda);
        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        pagu = (TextView) allView.findViewById(R.id.tvTotalPaguAllAda);
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
                    myAdapter = new AdaMarTabAllAdapter(allListData);
                    rvList.setAdapter(myAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return allView;
    }
}