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

public class incident4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident4);
        Button btnback3 =(Button) findViewById(R.id.btnback3);
        btnback3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(incident4.this, incident3.class);
                startActivity(intent);
                finish();
            }
//
        });
        Button btnnext3 = (Button) findViewById(R.id.btnnext3);
        btnnext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View m) {
                Intent intent = new Intent(incident4.this,incident5.class);
                startActivity(intent);
                finish();
            }
        });


    }
}