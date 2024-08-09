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

public class incident5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident5);
        Button btnback4 =(Button) findViewById(R.id.btnback4 );
        btnback4 .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(incident5.this, incident4.class);
                startActivity(intent);
                finish();
            }

        });
        Button btnnext4  = (Button) findViewById(R.id.btnnext4 );
        btnnext4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View m) {
                Intent intent = new Intent(incident5.this,incident6.class);
                startActivity(intent);
                finish();
            }
        });
    }
}