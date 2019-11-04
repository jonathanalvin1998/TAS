package com.example.uas_72170128;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DosenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen);
        this.setTitle("SI KRS - Hai {Nama Dosen}");
        ImageButton dataKlsBtn = findViewById(R.id.btnLihatKelas);
        ImageButton krsBtn = findViewById(R.id.btnDaftarKrs);
        ImageButton dataDiriBtn = findViewById(R.id.btnDataDiri);

        dataKlsBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(DosenActivity.this,LihatKelasActivity.class);
                startActivity(i);
            }
        });
        krsBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(DosenActivity.this,LihatKelasActivity.class);
                startActivity(i);
            }
        });
        dataDiriBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(DosenActivity.this,DataDiriMhsActivity.class);
                startActivity(i);
            }
        });
    }
}
