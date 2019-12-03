package com.example.uas_72170128.Adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.webkit.URLUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas_72170128.Model.Dosen;
import com.example.uas_72170128.R;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

import static java.lang.System.load;

public class DosenAdapter extends RecyclerView.Adapter<DosenAdapter.ViewHolder>{
    ArrayList<Dosen> dsnArrayList;
    public Context context;

    public DosenAdapter(ArrayList<Dosen> dsnArrayList) {
        this.dsnArrayList = dsnArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_dosen,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtNidn.setText(dsnArrayList.get(position).getNidn());
        holder.txtNama.setText(dsnArrayList.get(position).getNama());
        holder.txtEmail.setText(dsnArrayList.get(position).getEmail());
        holder.txtAlamat.setText(dsnArrayList.get(position).getAlamat());
        holder.ImgDsn.getLayoutParams().width = 100;
        holder.ImgDsn.getLayoutParams().height = 100;
        if (dsnArrayList.get(position).getImgDsn() != null){
//            Picasso.with(this.context)
//                .load(dsnArrayList.get(position).getImgDsn())
//                .into(holder.ImgDsn);
        }
    }

    @Override
    public int getItemCount() { //untuk menghitung jumlah data yang ada//
        return (dsnArrayList != null) ?dsnArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    implements View.OnCreateContextMenuListener{
        private TextView txtNidn, txtNama, txtEmail, txtAlamat;
        ImageButton ImgDsn;

        public ViewHolder(View view){
            super(view);
            txtNidn = view.findViewById(R.id.txtNidn);
            txtNama = view.findViewById(R.id.txtNama);
            txtEmail = view.findViewById(R.id.txtEmail);
            txtAlamat = view.findViewById(R.id.txtAlamat);
            ImgDsn = view.findViewById(R.id.ImgDsn);
            view.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo menuInfo) {
            contextMenu.setHeaderTitle("Pilih Aksi");
            contextMenu.add(this.getAdapterPosition(),view.getId(),0,"Ubah Data Dosen");
            contextMenu.add(this.getAdapterPosition(),view.getId(),0,"Hapus Data Dosen");
        }
    }
}
