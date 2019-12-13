package com.example.uas_72170128.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas_72170128.Model.Kelas;
import com.example.uas_72170128.R;

import java.util.ArrayList;

public class KelasAdapter extends RecyclerView.Adapter<KelasAdapter.ViewHolder> {
    ArrayList<Kelas> klsArrayList;

    public KelasAdapter(ArrayList<Kelas> klsArrayList) {
        this.klsArrayList = klsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_kelas,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtHari.setText(klsArrayList.get(position).getHari());
        holder.txtJumMhs.setText(klsArrayList.get(position).getJumMhs());
        holder.txtDsn.setText(klsArrayList.get(position).getDosen());
        holder.txtSesi.setText(klsArrayList.get(position).getSesi());
//        holder.ImgDsn.setImageResource(klsArrayList.get(position).getImgDsn());
    }

    @Override
    public int getItemCount() { //untuk menghitung jumlah data yang ada//
        return (klsArrayList != null) ?klsArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtHari, txtJumMhs, txtDsn, txtSesi;
        ImageButton ImgDsn;

        public ViewHolder(View view){
            super(view);

            txtHari = view.findViewById(R.id.txtHari);
            txtJumMhs = view.findViewById(R.id.txtJumMhs);
            txtDsn = view.findViewById(R.id.txtDsn);
            txtSesi = view.findViewById(R.id.txtSesi);
            ImgDsn = view.findViewById(R.id.ImgDsn);
        }
    }
}
