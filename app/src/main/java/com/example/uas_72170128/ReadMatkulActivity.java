package com.example.uas_72170128;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.uas_72170128.Adapter.MatkulAdapter;
import com.example.uas_72170128.Adapter.MatkulAdapter;
import com.example.uas_72170128.Model.Matkul;

import java.util.ArrayList;

public class ReadMatkulActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MatkulAdapter mhsSIAdapter;
    private ArrayList<Matkul> mhsSIArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_matkul);
        this.setTitle("SI KRS - Hai {Nama Mhs}");

        addData();

        recyclerView = findViewById(R.id.rvMatkul);
        mhsSIAdapter = new MatkulAdapter(mhsSIArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ReadMatkulActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mhsSIAdapter);
    }
    private  void addData(){
        mhsSIArrayList =  new ArrayList<>();
        mhsSIArrayList.add(new Matkul("123","BI","Senin","3","3"));
        mhsSIArrayList.add(new Matkul("456","Manajemen Proyek","Selasa .","4","3"));
    }
}
