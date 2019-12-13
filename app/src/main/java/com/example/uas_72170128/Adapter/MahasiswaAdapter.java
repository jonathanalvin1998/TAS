package com.example.uas_72170128.Adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas_72170128.Model.Mahasiswa;
import com.example.uas_72170128.R;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder>{
    ArrayList<Mahasiswa> dataList;
    public Context context;

    public MahasiswaAdapter(ArrayList<Mahasiswa> dataList) {
        this.dataList = dataList;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_mhs,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder Mhs, int Position) {
        Mhs.txtNama.setText(dataList.get(Position).getNama());
        Mhs.txtNim.setText(dataList.get(Position).getNim());
        Mhs.txtEmail.setText(dataList.get(Position).getEmail());
        Mhs.txtAlamat.setText(dataList.get(Position).getAlamat());
        Mhs.img.getLayoutParams().width = 100;
        Mhs.img.getLayoutParams().height = 100;
    }

    @Override
    public int getItemCount() {

        return (dataList !=null) ? dataList.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnCreateContextMenuListener{
        private TextView txtNama,txtNim,txtEmail,txtAlamat;
        private ImageButton img;

        public ViewHolder(View view){
            super(view);
            txtNama = view.findViewById(R.id.txt_nama);
            txtNim = view.findViewById(R.id.txt_nim);
            txtEmail = view.findViewById(R.id.txt_email);
            txtAlamat = view.findViewById(R.id.txt_alamat);
            img = view.findViewById(R.id.img);
            view.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Pilih Aksi");
            menu.add(this.getAdapterPosition(),v.getId(),0,"Ubah Data Mahasiswa");
            menu.add(this.getAdapterPosition(),v.getId(),0,"Hapus Data Mahasiswa");
        }
    }
}
