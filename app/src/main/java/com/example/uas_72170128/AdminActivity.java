package com.example.uas_72170128;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ImageButton dosenBtn = findViewById(R.id.btnDosen);
        ImageButton dataBtn = findViewById(R.id.btnDataDiri);
        ImageButton matkulBtn = findViewById(R.id.btnMatkul);
        ImageButton krsBtn = findViewById(R.id.btnKrs);
        ImageButton mhsBtn = findViewById(R.id.btnMhs);

        dosenBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(AdminActivity.this,ReadDosenActivity.class);
                startActivity(i);
            }
        });
        matkulBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(AdminActivity.this,ReadMatkulActivity.class);
                startActivity(i);
            }
        });
        mhsBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(AdminActivity.this,ReadMahasiswaActivity.class);
                startActivity(i);
            }
        });
        krsBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(AdminActivity.this,ReadKrsActivity.class);
                startActivity(i);
            }
        });
        dataBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(AdminActivity.this,DataDiriDsnActivity.class);
                startActivity(i);
            }
        });
    }
}
