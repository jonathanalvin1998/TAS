package com.example.uas_72170128.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas_72170128.Model.Matkul;
import com.example.uas_72170128.R;

import java.util.ArrayList;

public class MatkulAdapter extends RecyclerView.Adapter<MatkulAdapter.ViewHolder>{
    ArrayList<Matkul> matkulArrayList;

    public MatkulAdapter(ArrayList<Matkul> matkulArrayList) {
        this.matkulArrayList = matkulArrayList;
    }

    @NonNull
    @Override
    public MatkulAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_matkul,parent,false);
        return new MatkulAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatkulAdapter.ViewHolder holder, int position) {
        holder.txtHari.setText(matkulArrayList.get(position).getHari());
        holder.txtKode.setText(matkulArrayList.get(position).getKode());
        holder.txtJumlahSks.setText(matkulArrayList.get(position).getJumsks());
        holder.txtSesi.setText(matkulArrayList.get(position).getSesi());
        holder.txtMatkul.setText(matkulArrayList.get(position).getMatkul());
//        holder.ImgDsn.setImageResource(matkulArrayList.get(position).getImgDsn());
    }

    @Override
    public int getItemCount() { //untuk menghitung jumlah data yang ada//
        return (matkulArrayList != null) ?matkulArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtHari, txtKode, txtJumlahSks, txtSesi, txtMatkul;
//        ImageView ImgDsn;

        public ViewHolder(View view){
            super(view);

            txtHari = view.findViewById(R.id.txtHari);
            txtKode = view.findViewById(R.id.txtKode);
            txtJumlahSks = view.findViewById(R.id.txtJumlahsks);
            txtSesi = view.findViewById(R.id.txtSesi);
            txtMatkul = view.findViewById(R.id.txtMatkul);
//            ImgDsn = view.findViewById(R.id.ImgDsn);
        }
    }
}
