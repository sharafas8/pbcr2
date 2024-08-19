package com.pbcr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class mortality1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortality1);
//
        Button morback1 =(Button) findViewById(R.id.morback1);
        morback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mortality1.this, homepage.class);
                startActivity(intent);
                finish();
            }

        });
        Button mornext1 = (Button) findViewById(R.id.mornext1);
        mornext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View m) {
                Intent intent = new Intent(mortality1.this,mortality2.class);
                startActivity(intent);
                finish();
            }
        });

    }
}