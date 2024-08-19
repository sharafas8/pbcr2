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

public class incident10 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incident10);

        Button btnback9 =(Button) findViewById(R.id.btnback9);
        btnback9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(incident10.this, incident9.class);
                startActivity(intent);
                finish();
            }

        });
        Button btnnext9 = (Button) findViewById(R.id.btnnext9);
        btnnext9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View m) {
                Intent intent = new Intent(incident10.this,incident11.class);
                startActivity(intent);
                finish();
            }
        });

    }
}