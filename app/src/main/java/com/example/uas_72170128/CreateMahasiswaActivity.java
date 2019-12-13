package com.example.uas_72170128;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.uas_72170128.Model.Dosen;
import com.example.uas_72170128.Model.Mahasiswa;
import com.example.uas_72170128.Network.GetDataService;
import com.example.uas_72170128.Network.RetrofitClientInstance;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class CreateMahasiswaActivity extends AppCompatActivity {
    private EditText nama_mahasiswa;
    private EditText alamat_mahasiswa;
    private EditText email_mahasiswa;
    private EditText nim ;
    private EditText foto;
    private ImageView img;
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
        setContentView(R.layout.activity_create_mahasiswa);
        this.setTitle("SI KRS - Hai {Nama MHS}");
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            },FILE_ACCESS_REQUEST_CODE );
        }

        Button simpanBtn = (Button)findViewById(R.id.btnSimpan);
        nama_mahasiswa = (EditText) findViewById(R.id.txtNama);
        nim = (EditText) findViewById(R.id.txtNim);
        alamat_mahasiswa = (EditText) findViewById(R.id.txtAlamat);
        email_mahasiswa = (EditText) findViewById(R.id.txtEmail);
        foto = (EditText) findViewById(R.id.txtFoto);
        cek_update();
        simpanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nama_mahasiswa.getText().toString().length()==0)
                    nama_mahasiswa.setError("Nama diperlukan!");
                else if(nim.getText().toString().length()==0)
                    nim.setError("Nim diperlukan!");
                else if(alamat_mahasiswa.getText().toString().length()==0)
                    alamat_mahasiswa.setError("Alamat diperlukan!");
                else if(email_mahasiswa.getText().toString().length()==0)
                    email_mahasiswa.setError("Email diperlukan!");
                else if(foto.getText().toString().length()==0)
                    foto.setError("Foto diperlukan!");
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(CreateMahasiswaActivity.this);
                    builder.setMessage("Apakah anda yakin untuk menyimpan data Dosen?")
                            .setNegativeButton("no", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(CreateMahasiswaActivity.this,"Tidak menyimpan",Toast.LENGTH_LONG).show();} })
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
        Call<Mahasiswa> call;
        if(update){
            call = service.update_mahasiswa(
                    id,
                    nama_mahasiswa.getText().toString(),
                    nim.getText().toString(),
                    alamat_mahasiswa.getText().toString(),
                    email_mahasiswa.getText().toString(),
                    stringImg,
                    "72170128" );
        }
        else{
            call = service.insert_mahasiswa(
                    id,
                    nama_mahasiswa.getText().toString(),
                    nim.getText().toString(),
                    alamat_mahasiswa.getText().toString(),
                    email_mahasiswa.getText().toString(),
                    stringImg,
                    "72170128"
            );}
        call.enqueue(new Callback<Mahasiswa>() {
            @Override
            public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                progressDialog.dismiss();
                Toast.makeText(CreateMahasiswaActivity.this, "Berhasil Save", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CreateMahasiswaActivity.this, ReadMahasiswaActivity.class);
                startActivity(intent);
            }
            @Override
            public void onFailure(Call<Mahasiswa> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CreateMahasiswaActivity.this,"Gagal Save,Coba Lagi",Toast.LENGTH_LONG);

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
        nama_mahasiswa.setText(extras.getString("nama_mahasiswa"));
        id = extras.getInt("id_mahasiswa");
        nim.setText(extras.getString("nidn"));
        alamat_mahasiswa.setText(extras.getString("alamat_mahasiswa"));
        email_mahasiswa.setText(extras.getString("email_mahasiswa"));
//        foto.setText(extras.getString("foto"));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == Activity.RESULT_OK)
            switch(requestCode){
                case GALLERY_REQUEST_CODE:
                    Uri selectedImage = data.getData();
                    img = findViewById(R.id.imgMhs);
                    img.setImageBitmap(bitmap);
                    img.setVisibility(View.VISIBLE);
                    img.setImageURI(selectedImage);

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
