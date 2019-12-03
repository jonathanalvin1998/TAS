package com.example.uas_72170128;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.uas_72170128.Model.DefaultResult;
import com.example.uas_72170128.Network.GetDataService;
import com.example.uas_72170128.Network.RetrofitClientInstance;
import com.example.uas_72170128.Adapter.DosenAdapter;
import com.example.uas_72170128.Model.Dosen;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ReadDosenActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DosenAdapter dsnAdapter;
    private ArrayList<Dosen> dsnArrayList;
    private List<Dosen> dsn;
    ProgressDialog progressDialog;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        SharedPreferences prefs = ReadDosenActivity.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
        String statusLogin = prefs.getString("isLogin",null);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("isLogin",null);
        edit.commit();
        Intent i = new Intent(ReadDosenActivity.this,CreateDosenActivity.class);
        startActivity(i);
        return super.onOptionsItemSelected(item);
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_dosen);
        this.setTitle("SI KRS - Hai {Nama Admin}");
        recyclerView = findViewById(R.id.rvDosen);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ArrayList<Dosen>> call = service.getDosenAll("72170128");
        call.enqueue(new Callback<ArrayList<Dosen>>() {
            @Override
            public void onResponse(Call<ArrayList<Dosen>> call, Response<ArrayList<Dosen>> response) {
                progressDialog.dismiss();
                dsnArrayList = response.body();
                dsnAdapter = new DosenAdapter(response.body());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ReadDosenActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(dsnAdapter);
            }
            @Override
            public void onFailure(Call<ArrayList<Dosen>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ReadDosenActivity.this,"Login gagal,coba lagi",Toast.LENGTH_LONG);

            }
        });
        registerForContextMenu(recyclerView);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Dosen dosen = dsnArrayList.get(item.getGroupId());
        if(item.getTitle() == "Ubah Data Dosen"){
            Intent i = new Intent(ReadDosenActivity.this, CreateDosenActivity.class);
            i.putExtra("id_dosen",dosen.getId());
            i.putExtra("nama_dosen", dosen.getNama());
            i.putExtra("nidn",dosen.getNidn());
            i.putExtra("alamat_dosen",dosen.getAlamat());
            i.putExtra("email_dosen",dosen.getEmail());
            i.putExtra("foto",dosen.getImgDsn());
            i.putExtra("gelar", dosen.getGelar());
            i.putExtra("is_update",true);
            startActivity(i);
        }
        else if(item.getTitle() == "Hapus Data Dosen"){
            progressDialog = new ProgressDialog(ReadDosenActivity.this);
            progressDialog.show();

            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<Dosen> call = service.delete_dosen(dosen.getId(),"72170128");
            call.enqueue(new Callback<Dosen>() {
                @Override
                public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                    progressDialog.dismiss();
                    Toast.makeText(ReadDosenActivity.this, "Berhasil Delete", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ReadDosenActivity.this, ReadDosenActivity.class);
                    startActivity(intent);
                }
                @Override
                public void onFailure(Call<Dosen> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(ReadDosenActivity.this,"Gagal Delete,Coba Lagi",Toast.LENGTH_LONG);
                }
            });

        };
        return super.onContextItemSelected(item);
    }
    }