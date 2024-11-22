package com.pbcr;

import android.content.Intent;
import android.os.Bundle;
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
        Button btnBack3 = findViewById(R.id.btnbn);
        btnBack3.setOnClickListener(v -> {
            Intent intent = new Intent(mortality1.this, homepage.class);
            startActivity(intent);
            finish();
        });


    }
}