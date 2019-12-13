package com.example.uas_72170128;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.uas_72170128.Adapter.DosenAdapter;
import com.example.uas_72170128.Model.DefaultResult;
import com.example.uas_72170128.Model.Dosen;
import com.example.uas_72170128.Network.GetDataService;
import com.example.uas_72170128.Network.RetrofitClientInstance;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class CreateDosenActivity extends AppCompatActivity {
        private EditText nama_dosen;
        private EditText alamat_dosen;
        private EditText email_dosen;
        private EditText gelar_dosen;
        private EditText nidn_dosen ;
        private EditText foto;
        private ImageView imgDosen;
        ProgressDialog progressDialog;
        boolean update = false;
        int id;
        private static int RESULT_LOAD_IMG = 1;
        private static final int GALLERY_REQUEST_CODE = 58;
        private  static final int FILE_ACCESS_REQUEST_CODE = 58;
        Bitmap bitmap;
        private String stringImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dosen);
        this.setTitle("SI KRS - Hai {Nama Admin}");
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
                    },FILE_ACCESS_REQUEST_CODE );
        }

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
                if(nama_dosen.getText().toString().length()==0)
                    nama_dosen.setError("Nama diperlukan!");
                else if(nidn_dosen.getText().toString().length()==0)
                    nidn_dosen.setError("NIDN diperlukan!");
                else if(gelar_dosen.getText().toString().length()==0)
                    gelar_dosen.setError("Gelar diperlukan!");
                else if(alamat_dosen.getText().toString().length()==0)
                    alamat_dosen.setError("Alamat diperlukan!");
                else if(email_dosen.getText().toString().length()==0)
                    email_dosen.setError("Email diperlukan!");
                else if(foto.getText().toString().length()==0)
                    foto.setError("Foto diperlukan!");
                else{
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
                dialog.show();}
            }
        });

        final Button btnUpload = findViewById(R.id.btnFoto);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK);
                galleryIntent.setType("image/*");
                String[] mimeTypes = {"image/jpeg"};
                galleryIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
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
                    stringImg,
                    "72170128" );
        }
        else{
               call = service.insert_dosen(
                nama_dosen.getText().toString(),
                alamat_dosen.getText().toString(),
                nidn_dosen.getText().toString(),
                gelar_dosen.getText().toString(),
                email_dosen.getText().toString(),
                       stringImg,
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
//        foto.setText(extras.getString("foto"));
        gelar_dosen.setText(extras.getString("gelar"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == Activity.RESULT_OK)
            switch(requestCode){
                case GALLERY_REQUEST_CODE:
                Uri selectedImage = data.getData();
                imgDosen = findViewById(R.id.imgDosen);
                imgDosen.setImageBitmap(bitmap);
                imgDosen.setVisibility(View.VISIBLE);
                imgDosen.setImageURI(selectedImage);

                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null,null,null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgDecodableString = cursor.getString(columnIndex);
                foto.setText(imgDecodableString);
                cursor.close();

                Bitmap bm = BitmapFactory.decodeFile(imgDecodableString);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
                byte[] b = baos.toByteArray();

                stringImg = Base64.encodeToString(b, Base64.DEFAULT);
                break;

            }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case FILE_ACCESS_REQUEST_CODE:
                if(grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED){
                //Permiission Granted
            }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
