package com.example.uas_72170128;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReadMatkulActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_matkul);
        this.setTitle("SI KRS - Hai {Nama Mhs}");
        Button btnCreate = findViewById(R.id.btnCreate);
        Button btnEdit = findViewById(R.id.btnEdit);

        btnCreate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(ReadMatkulActivity.this,CreateMatkulActivity.class);
                startActivity(i);
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(ReadMatkulActivity.this,CreateMatkulActivity.class);
                startActivity(i);
            }
        });
    }
}
