package com.example.uas_72170128;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView email = findViewById(R.id.txt_email);
        SharedPreferences prefs = LoginActivity.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
        String statusLogin = prefs.getString("isLogin",null);
        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(myBtnLoginClick);
    }

    private View.OnClickListener myBtnLoginClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences prefs = LoginActivity.this.getSharedPreferences("prefs_file",MODE_PRIVATE);

            String statusLogin = prefs.getString("isLogin",null);
            SharedPreferences.Editor edit = prefs.edit();

            TextView email = findViewById(R.id.txtEmail);

            if (email.getText().toString().contains("@si.ukdw.ac.id")){
                edit.putString("isLogin","Dosen");
                edit.commit();
                Intent i = new Intent(LoginActivity.this,DosenActivity.class);
                startActivity(i);        }
            else if(email.getText().toString().contains("@staff.ukdw.ac.id")){
                edit.putString("isLogin","Admin");
                edit.commit();
                Intent i = new Intent(LoginActivity.this,AdminActivity.class);
                startActivity(i);         }

        } };
}
