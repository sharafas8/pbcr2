package com.pbcr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class incident7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident7);
        Button btnback6 =(Button) findViewById(R.id.morback1);
        btnback6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(incident7.this, incident6.class);
                startActivity(intent);
                finish();
            }

        });
        Button btnnext6 = (Button) findViewById(R.id.mornext1);
        btnnext6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View m) {
                Intent intent = new Intent(incident7.this,incident8.class);
                startActivity(intent);
                finish();
            }
        });

    }
}