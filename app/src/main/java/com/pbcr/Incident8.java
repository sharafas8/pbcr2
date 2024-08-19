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

public class incident8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident8);
        Button btnback7 =(Button) findViewById(R.id.btnback7);
        btnback7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(incident8.this, incident7.class);
                startActivity(intent);
                finish();
            }

        });
        Button btnnext7 = (Button) findViewById(R.id.btnnext7);
        btnnext7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View m) {
                Intent intent = new Intent(incident8.this,incident9.class);
                startActivity(intent);
                finish();
            }
        });
    }
}