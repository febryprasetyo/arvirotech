package com.arvirotech.monev.marketing.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arvirotech.monev.R;
import com.arvirotech.monev.marketing.compon.DetailKons;
import com.arvirotech.monev.marketing.compon.DetailMar;
import com.arvirotech.monev.model.listData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

public class KonMarTabAdapter extends RecyclerView.Adapter<KonMarTabAdapter.myviewholder> {

    private Context context;
    private List<listData> dataList;

    private String mAlamat;
    private double _totalPagu;

    public KonMarTabAdapter() {
    }

    public KonMarTabAdapter(Context context) {
        this.context = context;
    }

    public KonMarTabAdapter(List<listData> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        myviewholder vHolder = new myviewholder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        DecimalFormat formatRupiah = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        formatRupiah.setDecimalFormatSymbols(formatRp);

        listData dl = dataList.get(position);
        Double _totalPagu = Double.valueOf(dl.getNilaiPagu());
        holder.Pekerjaan.setText(dl.getNamaProyek());
        holder.Lpse.setText(dl.getSatuanKerja());
        holder.Pagu.setText(formatRupiah.format(_totalPagu));

//        holder.Presentase.setText(dl.getProgress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
                DatabaseReference myref = mDatabase.getReference("Marketing").child("Konstruksi");
                DatabaseReference key = myref.child("key");
                key.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()){
                            String keyID = snapshot.getValue().toString();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                listData selected = dataList.get(position);
                Intent detailIntent = new Intent(v.getContext(), DetailKons.class);
                detailIntent.putExtra("Pekerjaan", dl.getNamaProyek());
                detailIntent.putExtra("satuanKerja", dl.getSatuanKerja());
                detailIntent.putExtra("alamatProyek", dl.getAlamatProyek());
                detailIntent.putExtra("nilaiPagu", dl.getNilaiPagu());
                detailIntent.putExtra("user", dl.getUser());
                detailIntent.putExtra("progress", dl.getProgress());
                detailIntent.putExtra("key", dl.getKey());
                v.getContext().startActivity(detailIntent);

            }

        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {

        private LinearLayout itemPekerjaan;
        private TextView Pekerjaan;
        private TextView Lpse;
        private TextView Pagu;
        private TextView Presentase;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
                itemPekerjaan = (LinearLayout) itemView.findViewById(R.id.clik_pekerjaan);
                Pekerjaan = (TextView) itemView.findViewById(R.id.tvPekerjaan);
                Lpse = (TextView) itemView.findViewById(R.id.tvLpse);
                Pagu = (TextView) itemView.findViewById(R.id.tvPagu);
//                Presentase = (TextView) itemView.findViewById(R.id.tvPersentase);
            String id;

            }
    }
}
