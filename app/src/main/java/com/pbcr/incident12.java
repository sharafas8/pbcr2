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

public class incident12 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident12);

        Button btnback11 =(Button) findViewById(R.id.btnback11);
        btnback11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(incident12.this, incident11.class);
                startActivity(intent);
                finish();
            }

        });
        Button btnnext11 = (Button) findViewById(R.id.btnnext11);
        btnnext11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View m) {
                Intent intent = new Intent(incident12.this,incident13.class);
                startActivity(intent);
                finish();
            }
        });
    }
}