package com.example.uas_72170128.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas_72170128.Model.Krs;
import com.example.uas_72170128.R;

import java.util.ArrayList;

public class KrsAdapter extends RecyclerView.Adapter<KrsAdapter.ViewHolder>{
    ArrayList<Krs> krsArrayList;

    public KrsAdapter(ArrayList<Krs> krsArrayList) {
        this.krsArrayList = krsArrayList;
    }

    @NonNull
    @Override
    public KrsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_krs,parent,false);
        return new KrsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KrsAdapter.ViewHolder holder, int position) {
        holder.txtHari.setText(krsArrayList.get(position).getHari());
        holder.txtJmlMhs.setText(krsArrayList.get(position).getJmlMhs());
        holder.txtDsn.setText(krsArrayList.get(position).getDosen());
        holder.txtSesi.setText(krsArrayList.get(position).getSesi());
        holder.txtJumlahsks.setText(krsArrayList.get(position).getJmlSks());
//        holder.ImgDsn.setImageResource(krsArrayList.get(position).getImgDsn());
    }

    @Override
    public int getItemCount() { //untuk menghitung jumlah data yang ada//
        return (krsArrayList != null) ?krsArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtHari, txtJmlMhs, txtDsn, txtSesi, txtJumlahsks;
//        ImageView ImgDsn;

        public ViewHolder(View view){
            super(view);

            txtHari = view.findViewById(R.id.txtHari);
            txtJmlMhs = view.findViewById(R.id.txtJmlMhs);
            txtDsn = view.findViewById(R.id.txtDsn);
            txtSesi = view.findViewById(R.id.txtSesi);
            txtJumlahsks = view.findViewById(R.id.txtJumlahsks);
//            ImgDsn = view.findViewById(R.id.ImgDsn);
        }
    }
}
