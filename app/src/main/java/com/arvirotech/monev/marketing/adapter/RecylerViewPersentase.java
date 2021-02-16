package com.arvirotech.monev.marketing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arvirotech.monev.R;
import com.arvirotech.monev.marketing.util.tabAllModel;

import java.util.List;

public class RecylerViewPersentase extends RecyclerView.Adapter<RecylerViewPersentase.MyViewHolder> {

    Context mContext;
    List<tabAllModel> mData;

    public RecylerViewPersentase(Context mContext, List<tabAllModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecylerViewPersentase.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_list,parent,false);
        RecylerViewPersentase.MyViewHolder vHolder = new RecylerViewPersentase.MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecylerViewPersentase.MyViewHolder holder, int position) {

        holder.Pekerjaan.setText(mData.get(position).getPekerjaan());
        holder.Lpse.setText(mData.get(position).getLpse());
        holder.Pagu.setText(mData.get(position).getPagu());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView Pekerjaan;
        private TextView Lpse;
        private TextView Pagu;
        private ImageButton more;

        public MyViewHolder(View itemView) {
            super(itemView);

            Pekerjaan = (TextView) itemView.findViewById(R.id.tvPekerjaan);
            Lpse = (TextView) itemView.findViewById(R.id.tvLpse);
            Pagu = (TextView) itemView.findViewById(R.id.tvPagu);
        }
    }
}
