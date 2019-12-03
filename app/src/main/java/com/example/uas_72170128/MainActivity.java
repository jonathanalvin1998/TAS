package com.example.uas_72170128;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView Splash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Splash = (TextView) findViewById(R.id.splash) ;
        this.getSupportActionBar().hide();

        SharedPreferences prefs = MainActivity.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
        String statusLogin = prefs.getString("isLogin",null);
        if(statusLogin != null){
            if(statusLogin.equals("Dosen"))
            {
                Intent i = new Intent(MainActivity.this,DosenActivity.class);
                startActivity(i);
            }
            else if(statusLogin.equals("Admin")){
                Intent i = new Intent(MainActivity.this,AdminActivity.class);
                startActivity(i);
            }}
        else{
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
               startActivity(new Intent(getApplicationContext(),LoginActivity.class));
               finish();
                }
            },900L);
        }
    }
}
