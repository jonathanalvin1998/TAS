package com.example.uas_72170128;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ReadMahasiswaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_mahasiswa);

        ImageButton mhs1Btn = findViewById(R.id.mhs1);
        ImageButton mhs2Btn = findViewById(R.id.mhs2);

        mhs1Btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(ReadMahasiswaActivity.this,CreateMahasiswaActivity.class);
                startActivity(i);
            }
        });

        mhs2Btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(ReadMahasiswaActivity.this,CreateMahasiswaActivity.class);
                startActivity(i);
            }
        });
    }
}
