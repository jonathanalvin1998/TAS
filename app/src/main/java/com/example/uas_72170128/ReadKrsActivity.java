package com.example.uas_72170128;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.uas_72170128.Adapter.KrsAdapter;
import com.example.uas_72170128.Model.Krs;

import java.util.ArrayList;

public class ReadKrsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private KrsAdapter mhsSIAdapter;
    private ArrayList<Krs> mhsSIArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_krs);
        this.setTitle("SI KRS - Hai {Nama Mahasiswa}");
        addData();

        recyclerView = findViewById(R.id.rvKrs);
        mhsSIAdapter = new KrsAdapter(mhsSIArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ReadKrsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mhsSIAdapter);
    }
    private  void addData(){
        mhsSIArrayList =  new ArrayList<>();
        mhsSIArrayList.add(new Krs("123","BI","Senin","3","3","Yetli Oslan","20"));
        mhsSIArrayList.add(new Krs("456","Manajemen Proyek","Selasa .","4","3","Argo Wibowo","30"));
    }
}
