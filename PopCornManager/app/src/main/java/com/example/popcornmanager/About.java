package com.example.popcornmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class About extends AppCompatActivity {
    Button btnRollBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        btnRollBack= findViewById(R.id.btnRollBackToCrud);


        btnRollBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infoIntent = new Intent(About.this,MainActivity.class);
                startActivity(infoIntent);
            }
        });

    }


}