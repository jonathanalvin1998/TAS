package com.example.uas_72170128;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uas_72170128.Adapter.DosenAdapter;
import com.example.uas_72170128.Model.DefaultResult;
import com.example.uas_72170128.Model.Dosen;
import com.example.uas_72170128.Network.GetDataService;
import com.example.uas_72170128.Network.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class CreateDosenActivity extends AppCompatActivity {
        private EditText nama_dosen;
        private EditText alamat_dosen;
        private EditText email_dosen;
        private EditText gelar_dosen;
        private EditText nidn_dosen ;
        private EditText foto;
        ProgressDialog progressDialog;
        boolean update = false;
        int id;
        private static int RESULT_LOAD_IMG = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dosen);
        this.setTitle("SI KRS - Hai {Nama Admin}");
        Button simpanBtn = (Button)findViewById(R.id.btnSimpan);
        nama_dosen = (EditText) findViewById(R.id.txtNama);
        nidn_dosen = (EditText) findViewById(R.id.txtNidn);
        gelar_dosen = (EditText) findViewById(R.id.txtGelar);
        alamat_dosen = (EditText) findViewById(R.id.txtAlamat);
        email_dosen = (EditText) findViewById(R.id.txtEmail);
        foto = (EditText) findViewById(R.id.txtFoto);
        cek_update();
        simpanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateDosenActivity.this);
                builder.setMessage("Apakah anda yakin untuk menyimpan data Dosen?")
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(CreateDosenActivity.this,"Tidak menyimpan",Toast.LENGTH_LONG).show();} })
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                insert_data();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    private  void insert_data(){
        progressDialog = new  ProgressDialog(this);
        progressDialog.setMessage("loading...");
        progressDialog.show();


        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Dosen> call;
        if(update){
            call = service.update_dosen(
                    id,
                    nama_dosen.getText().toString(),
                    alamat_dosen.getText().toString(),
                    nidn_dosen.getText().toString(),
                    gelar_dosen.getText().toString(),
                    email_dosen.getText().toString(),
                    foto.getText().toString(),
                    "72170128" );
        }
        else{
               call = service.insert_dosen(
                nama_dosen.getText().toString(),
                alamat_dosen.getText().toString(),
                nidn_dosen.getText().toString(),
                gelar_dosen.getText().toString(),
                email_dosen.getText().toString(),
                       foto.getText().toString(),
                "72170128"
        );}
        call.enqueue(new Callback<Dosen>() {
            @Override
            public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                progressDialog.dismiss();
                Toast.makeText(CreateDosenActivity.this, "Berhasil Save", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CreateDosenActivity.this, ReadDosenActivity.class);
                startActivity(intent);
            }
            @Override
            public void onFailure(Call<Dosen> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CreateDosenActivity.this,"Gagal Save,Coba Lagi",Toast.LENGTH_LONG);

            }
        });
    }
    void cek_update()
    {
        Bundle extras = getIntent().getExtras();
        if(extras == null){
            return;
        }
        update = extras.getBoolean("is_update");
        nama_dosen.setText(extras.getString("nama_dosen"));
        id = extras.getInt("id_dosen");
        nidn_dosen.setText(extras.getString("nidn"));
        alamat_dosen.setText(extras.getString("alamat_dosen"));
        email_dosen.setText(extras.getString("email_dosen"));
        foto.setText(extras.getString("foto"));
        gelar_dosen.setText(extras.getString("gelar"));
    }
    public void loadImagefromGallery(View view) {
        // buat intentnya
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

}
