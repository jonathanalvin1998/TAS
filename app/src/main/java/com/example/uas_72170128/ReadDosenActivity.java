package com.example.uas_72170128;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ReadDosenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_dosen);
        this.setTitle("SI KRS - Hai {Nama Admin}");
        ImageButton dosen1Btn = findViewById(R.id.dsn1);
        ImageButton dosen2Btn = findViewById(R.id.dsn2);

        dosen1Btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(ReadDosenActivity.this,CreateDosenActivity.class);
                startActivity(i);
            }
        });

        dosen2Btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(ReadDosenActivity.this,CreateDosenActivity.class);
                startActivity(i);
            }
        });
    }
}
