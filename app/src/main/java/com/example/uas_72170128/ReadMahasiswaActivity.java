package com.example.uas_72170128;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.uas_72170128.Adapter.MahasiswaAdapter;
import com.example.uas_72170128.Model.Mahasiswa;
import com.example.uas_72170128.Network.GetDataService;
import com.example.uas_72170128.Network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadMahasiswaActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MahasiswaAdapter mhsSIAdapter;
    private ArrayList<Mahasiswa> mhsSIArrayList;
    ProgressDialog progressDialog;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu3,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        SharedPreferences prefs = ReadMahasiswaActivity.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
        String statusLogin = prefs.getString("isLogin",null);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("isLogin",null);
        edit.commit();
        Intent i = new Intent(ReadMahasiswaActivity.this,CreateMahasiswaActivity.class);
        startActivity(i);
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_mahasiswa);
        this.setTitle("SI KRS - Hai {Nama Mahasiswa}");
            recyclerView = findViewById(R.id.rvMahasiswa);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ArrayList<Mahasiswa>> call = service.getMahasiswaAll("72170128");
        call.enqueue(new Callback<ArrayList<Mahasiswa>>() {
            @Override
            public void onResponse(Call<ArrayList<Mahasiswa>> call, Response<ArrayList<Mahasiswa>> response) {
                progressDialog.dismiss();
                mhsSIArrayList = response.body();
                mhsSIAdapter = new MahasiswaAdapter(response.body());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ReadMahasiswaActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(mhsSIAdapter);
            }
            @Override
            public void onFailure(Call<ArrayList<Mahasiswa>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ReadMahasiswaActivity.this,"Login gagal,coba lagi",Toast.LENGTH_LONG);

            }
        });
        registerForContextMenu(recyclerView);
        }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Mahasiswa mhs = mhsSIArrayList.get(item.getGroupId());
        if(item.getTitle() == "Ubah Data Mahasiswa"){
            Intent i = new Intent(ReadMahasiswaActivity.this, CreateMahasiswaActivity.class);
            i.putExtra("id_mahasiswa",mhs.getId());
            i.putExtra("nama_mahasiswa", mhs.getNama());
            i.putExtra("nim",mhs.getNim());
            i.putExtra("alamat_mahasiswa",mhs.getAlamat());
            i.putExtra("email_mahasiswa",mhs.getEmail());
            i.putExtra("foto",mhs.getImg());
            i.putExtra("is_update",true);
            startActivity(i);
        }
        else if(item.getTitle() == "Hapus Data Mahasiswa"){
            progressDialog = new ProgressDialog(ReadMahasiswaActivity.this);
            progressDialog.show();

            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<Mahasiswa> call = service.delete_mahasiswa(mhs.getId(),"72170128");
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    progressDialog.dismiss();
                    Toast.makeText(ReadMahasiswaActivity.this, "Berhasil Delete", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ReadMahasiswaActivity.this, ReadMahasiswaActivity.class);
                    startActivity(intent);
                }
                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(ReadMahasiswaActivity.this,"Gagal Delete,Coba Lagi",Toast.LENGTH_LONG);
                }
            });

        };
        return super.onContextItemSelected(item);
    }
    }

