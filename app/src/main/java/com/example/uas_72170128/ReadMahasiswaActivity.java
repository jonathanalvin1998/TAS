package com.example.uas_72170128;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.uas_72170128.Adapter.MahasiswaAdapter;
import com.example.uas_72170128.Model.Mahasiswa;

import java.util.ArrayList;

public class ReadMahasiswaActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MahasiswaAdapter mhsSIAdapter;
    private ArrayList<Mahasiswa> mhsSIArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_mahasiswa);
        this.setTitle("SI KRS - Hai {Nama Mahasiswa}");
        addData();

            recyclerView = findViewById(R.id.rvMahasiswa);
            mhsSIAdapter = new MahasiswaAdapter(mhsSIArrayList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ReadMahasiswaActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(mhsSIAdapter);
        }
        private  void addData(){
            mhsSIArrayList =  new ArrayList<>();
            mhsSIArrayList.add(new Mahasiswa("Michael Gerardi Adji","72170100","Jalan Neraka","gerard@gmail.com",R.drawable.ic_launcher_background));
            mhsSIArrayList.add(new Mahasiswa("Jonathan Alvin","72170027","Jalan .","emma@gmail.com",R.drawable.ic_launcher_background));
        }
//        ImageButton mhs1Btn = findViewById(R.id.mhs1);
//        ImageButton mhs2Btn = findViewById(R.id.mhs2);
//
//        mhs1Btn.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View view){
//                Intent i = new Intent(ReadMahasiswaActivity.this,CreateMahasiswaActivity.class);
//                startActivity(i);
//            }
//        });
//
//        mhs2Btn.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View view){
//                Intent i = new Intent(ReadMahasiswaActivity.this,CreateMahasiswaActivity.class);
//                startActivity(i);
//            }
//        });
    }

