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

public class incident6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident6);
        Button btnback5 =(Button) findViewById(R.id.btnback5);
        btnback5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(incident6.this, incident5.class);
                startActivity(intent);
                finish();
            }

        });
        Button btnnext5 = (Button) findViewById(R.id.btnnext5);
        btnnext5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View m) {
                Intent intent = new Intent(incident6.this,incident7.class);
                startActivity(intent);
                finish();
            }
        });
    }
}