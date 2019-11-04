package com.example.uas_72170128;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
                builder.setMessage("Apakah anda yakin untuk Log Out?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(AdminActivity.this, "Tidak jadi Log Out",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(AdminActivity.this,LoginActivity.class);
                                startActivity(i);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        this.setTitle("SI KRS - Hai {Nama Admin}");

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
